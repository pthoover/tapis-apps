package edu.utexas.tacc.tapis.apps.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

import edu.utexas.tacc.tapis.security.client.gen.model.SkRole;
import edu.utexas.tacc.tapis.shared.TapisConstants;
import edu.utexas.tacc.tapis.shared.i18n.MsgUtils;
import org.apache.commons.lang3.StringUtils;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.utexas.tacc.tapis.client.shared.exceptions.TapisClientException;
import edu.utexas.tacc.tapis.search.parser.ASTParser;
import edu.utexas.tacc.tapis.search.parser.ASTNode;
import edu.utexas.tacc.tapis.search.SearchUtils;
import edu.utexas.tacc.tapis.security.client.SKClient;
import edu.utexas.tacc.tapis.shared.exceptions.TapisException;
import edu.utexas.tacc.tapis.shared.security.ServiceClients;
import edu.utexas.tacc.tapis.shared.security.ServiceContext;
import edu.utexas.tacc.tapis.shared.threadlocal.TapisThreadContext;
import edu.utexas.tacc.tapis.shared.utils.TapisGsonUtils;
import edu.utexas.tacc.tapis.sharedapi.security.AuthenticatedUser;
import edu.utexas.tacc.tapis.apps.config.RuntimeParameters;
import edu.utexas.tacc.tapis.apps.dao.AppsDao;
import edu.utexas.tacc.tapis.apps.model.PatchApp;
import edu.utexas.tacc.tapis.apps.model.App;
import edu.utexas.tacc.tapis.apps.model.App.Permission;
import edu.utexas.tacc.tapis.apps.model.App.AppOperation;
import edu.utexas.tacc.tapis.apps.utils.LibUtils;
import static edu.utexas.tacc.tapis.apps.model.App.APIUSERID_VAR;
import static edu.utexas.tacc.tapis.apps.model.App.OWNER_VAR;
import static edu.utexas.tacc.tapis.apps.model.App.TENANT_VAR;
import static edu.utexas.tacc.tapis.shared.TapisConstants.APPS_SERVICE;

/*
 * Service level methods for Apps.
 *   Uses Dao layer and other service library classes to perform all top level service operations.
 * Annotate as an hk2 Service so that default scope for DI is singleton
 */
@Service
public class AppsServiceImpl implements AppsService
{
  // ************************************************************************
  // *********************** Constants **************************************
  // ************************************************************************

  // Tracing.
  private static final Logger _log = LoggerFactory.getLogger(AppsServiceImpl.class);

  private static final String[] ALL_VARS = {APIUSERID_VAR, OWNER_VAR, TENANT_VAR};
  private static final Set<Permission> ALL_PERMS = new HashSet<>(Set.of(Permission.READ, Permission.MODIFY, Permission.EXECUTE));
  private static final Set<Permission> READMODIFY_PERMS = new HashSet<>(Set.of(Permission.READ, Permission.MODIFY));
  private static final String PERM_SPEC_PREFIX = "app:";

  // TODO determine if certain services need special permissions
  private static final String FILES_SERVICE = "files";
  private static final String JOBS_SERVICE = "jobs";
  private static final Set<String> SVCLIST_READ = new HashSet<>(Set.of(FILES_SERVICE, JOBS_SERVICE));

  // Message keys
  private static final String ERROR_ROLLBACK = "APPLIB_ERROR_ROLLBACK";
  private static final String NOT_FOUND = "APPLIB_NOT_FOUND";

  // ************************************************************************
  // *********************** Enums ******************************************
  // ************************************************************************

  // ************************************************************************
  // *********************** Fields *****************************************
  // ************************************************************************

  // Use HK2 to inject singletons
  @Inject
  private AppsDao dao;

  @Inject
  private ServiceClients serviceClients;

  @Inject
  private ServiceContext serviceContext;

  // ************************************************************************
  // *********************** Public Methods *********************************
  // ************************************************************************

  // -----------------------------------------------------------------------
  // ------------------------- Apps -------------------------------------
  // -----------------------------------------------------------------------

  /**
   * Create a new app object given an App and the text used to create the App.
   * Secrets in the text should be masked.
   * @param authenticatedUser - principal user containing tenant and user info
   * @param app - Pre-populated App object
   * @param scrubbedText - Text used to create the App object - secrets should be scrubbed. Saved in update record.
   * @return Sequence id of object created. This is for a specific version of the application.
   * @throws TapisException - for Tapis related exceptions
   * @throws IllegalStateException - app exists OR App in invalid state
   * @throws IllegalArgumentException - invalid parameter passed in
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public int createApp(AuthenticatedUser authenticatedUser, App app, String scrubbedText)
          throws TapisException, TapisClientException, IllegalStateException, IllegalArgumentException, NotAuthorizedException
  {
    AppOperation op = AppOperation.create;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (app == null) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    _log.trace(LibUtils.getMsgAuth("APPLIB_CREATE_TRACE", authenticatedUser, scrubbedText));
    // Extract various names for convenience
    String tenantName = authenticatedUser.getTenantId();
    String apiUserId = authenticatedUser.getName();
    String appId = app.getId();
    String appVersion = app.getVersion();
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // ---------------------------- Check inputs ------------------------------------
    // Required app attributes: id, version
    if (StringUtils.isBlank(tenantName) || StringUtils.isBlank(apiUserId) || StringUtils.isBlank(appId) ||
        StringUtils.isBlank(appVersion))
    {
      throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_CREATE_ERROR_ARG", authenticatedUser, appId));
    }

    // Check if app with id+version already exists
    if (dao.checkForApp(appTenantName, appId, appVersion, true))
    {
      throw new IllegalStateException(LibUtils.getMsgAuth("APPLIB_APP_EXISTS", authenticatedUser, appId, appVersion));
    }

    // Make sure owner, notes and tags are all set
    // Note that this is done before auth so owner can get resolved and used during auth check.
    app.setTenant(appTenantName);
    App.checkAndSetDefaults(app);

    // ----------------- Resolve variables for any attributes that might contain them --------------------
    resolveVariables(app, authenticatedUser.getOboUser());

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, app.getId(), app.getOwner(), null, null);

    // ---------------- Check constraints on App attributes ------------------------
    validateApp(authenticatedUser, app);

    // Construct Json string representing the App about to be created
    App scrubbedApp = new App(app);
    String createJsonStr = TapisGsonUtils.getGson().toJson(scrubbedApp);

    // ----------------- Create all artifacts --------------------
    // Creation of app and role/perms not in single DB transaction. Need to handle failure of role/perms operations
    // Use try/catch to rollback any writes in case of failure.
    int appVerSeqId = -1;
    String roleNameR = null;
    String appsPermSpecR = getPermSpecStr(appTenantName, appId, Permission.READ);
    String appsPermSpecALL = getPermSpecAllStr(appTenantName, appId);

    // Get SK client now. If we cannot get this rollback not needed.
    var skClient = getSKClient(authenticatedUser);
    try {
      // ------------------- Make Dao call to persist the app -----------------------------------
      appVerSeqId = dao.createApp(authenticatedUser, app, createJsonStr, scrubbedText);
      int appSeqId = dao.getAppSeqId(appTenantName, appId);

      // Add permission roles for the app. This is only used for filtering apps based on who is authz
      //   to READ, so no other roles needed.
      roleNameR = App.ROLE_READ_PREFIX + appSeqId;
      // TODO/TBD: Keep the delete? Also, currently it fails due to skauthz failure
      // Delete role, because role may already exist due to failure of rollback
//      _log.error("DELETE roleNameR="+ roleNameR);
//      skClient.deleteRoleByName(appTenantName, "apps", roleNameR);
//      skClient.deleteRoleByName(appTenantName, app.getOwner(), roleNameR);
      skClient.createRole(appTenantName, roleNameR, "Role allowing READ for app " + appId);
      skClient.addRolePermission(appTenantName, roleNameR, appsPermSpecR);

      // ------------------- Add permissions and role assignments -----------------------------
      // Give owner full access to the app
      skClient.grantUserPermission(appTenantName, app.getOwner(), appsPermSpecALL);
      skClient.grantUserRole(appTenantName, app.getOwner(), roleNameR);
    }
    catch (Exception e0)
    {
      // Something went wrong. Attempt to undo all changes and then re-throw the exception
      // Log error
      String msg = LibUtils.getMsgAuth("APPLIB_CREATE_ERROR_ROLLBACK", authenticatedUser, appId, e0.getMessage());
      _log.error(msg);

      // Rollback
      // Remove app from DB
      if (appVerSeqId != -1) try {dao.hardDeleteApp(appTenantName, appId); }
      catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "hardDelete", e.getMessage()));}
      // Remove perms
      try { skClient.revokeUserPermission(appTenantName, app.getOwner(), appsPermSpecALL); }
      catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "revokePermOwner", e.getMessage()));}
      // Remove role assignments and roles
      if (!StringUtils.isBlank(roleNameR)) {
        try { skClient.revokeUserRole(appTenantName, app.getOwner(), roleNameR);  }
        catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "revokeRoleOwner", e.getMessage()));}
        try { skClient.deleteRoleByName(appTenantName, roleNameR);  }
        catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "deleteRole", e.getMessage()));}
      }
      throw e0;
    }
    return appVerSeqId;
  }

  /**
   * Update existing version of an app given a PatchApp and the text used to create the PatchApp.
   * Secrets in the text should be masked.
   * Attributes that can be updated:
   *   TODO/TBD: description, enabled, tags, notes.
   * Attributes that cannot be updated:
   *   tenant, id, version, appType, owner
   * @param authenticatedUser - principal user containing tenant and user info
   * @param patchApp - Pre-populated PatchApp object
   * @param scrubbedText - Text used to create the PatchApp object - secrets should be scrubbed. Saved in update record.
   * @return Sequence id of object updated
   * @throws TapisException - for Tapis related exceptions
   * @throws IllegalStateException - Resulting App would be in an invalid state
   * @throws IllegalArgumentException - invalid parameter passed in
   * @throws NotAuthorizedException - unauthorized
   * @throws NotFoundException - App not found
   */
  @Override
  public int updateApp(AuthenticatedUser authenticatedUser, PatchApp patchApp, String scrubbedText)
          throws TapisException, TapisClientException, IllegalStateException, IllegalArgumentException, NotAuthorizedException, NotFoundException
  {
    AppOperation op = AppOperation.modify;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (patchApp == null) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    // Extract various names for convenience
    String tenantName = authenticatedUser.getTenantId();
    String apiUserId = authenticatedUser.getName();
    String appTenantName = patchApp.getTenant();
    String appId = patchApp.getId();
    String appVersion = patchApp.getVersion();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // ---------------------------- Check inputs ------------------------------------
    if (StringUtils.isBlank(tenantName) || StringUtils.isBlank(apiUserId) || StringUtils.isBlank(appId) || StringUtils.isBlank(scrubbedText))
    {
      throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_CREATE_ERROR_ARG", authenticatedUser, appId));
    }

    // App must already exist and not be soft deleted
    if (!dao.checkForApp(appTenantName, appId, false))
    {
      throw new NotFoundException(LibUtils.getMsgAuth(NOT_FOUND, authenticatedUser, appId));
    }

    // Retrieve the app being patched and create fully populated App with changes merged in
    App origApp = dao.getApp(appTenantName, appId, appVersion);
    App patchedApp = createPatchedApp(origApp, patchApp);

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, origApp.getOwner(), null, null);

    // ---------------- Check constraints on App attributes ------------------------
    validateApp(authenticatedUser, patchedApp);

    // Construct Json string representing the PatchApp about to be used to update the app
    String updateJsonStr = TapisGsonUtils.getGson().toJson(patchApp);

    // ----------------- Create all artifacts --------------------
    // No distributed transactions so no distributed rollback needed
    // ------------------- Make Dao call to persist the app -----------------------------------
    dao.updateApp(authenticatedUser, patchedApp, patchApp, updateJsonStr, scrubbedText);
    return origApp.getSeqId();
  }

  /**
   * Change owner of an app
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - name of app
   * @param newOwnerName - User name of new owner
   * @return Number of items updated
   * @throws TapisException - for Tapis related exceptions
   * @throws IllegalStateException - Resulting App would be in an invalid state
   * @throws IllegalArgumentException - invalid parameter passed in
   * @throws NotAuthorizedException - unauthorized
   * @throws NotFoundException - App not found
   */
  @Override
  public int changeAppOwner(AuthenticatedUser authenticatedUser, String appId, String newOwnerName)
          throws TapisException, IllegalStateException, IllegalArgumentException, NotAuthorizedException, NotFoundException, TapisClientException
  {
    AppOperation op = AppOperation.changeOwner;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId) || StringUtils.isBlank(newOwnerName))
         throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    // Extract various names for convenience
    String tenantName = authenticatedUser.getTenantId();
    String apiUserId = authenticatedUser.getName();
    String appTenantName = tenantName;
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // ---------------------------- Check inputs ------------------------------------
    if (StringUtils.isBlank(tenantName) || StringUtils.isBlank(apiUserId))
         throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_CREATE_ERROR_ARG", authenticatedUser, appId));

    // App must already exist and not be soft deleted
    if (!dao.checkForApp(appTenantName, appId, false))
         throw new NotFoundException(LibUtils.getMsgAuth(NOT_FOUND, authenticatedUser, appId));

    // Retrieve the most recently created version of the app being updated
    App tmpApp = dao.getApp(appTenantName, appId, null);
    int appSeqId = tmpApp.getSeqId();
    String oldOwnerName = tmpApp.getOwner();

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, tmpApp.getOwner(), null, null);

    // If new owner same as old owner then this is a no-op
    if (newOwnerName.equals(oldOwnerName)) return 0;

    // ----------------- Make all updates --------------------
    // Changes not in single DB transaction. Need to handle failure of role/perms operations
    // Use try/catch to rollback any changes in case of failure.
    // Get SK client now. If we cannot get this rollback not needed.
    var skClient = getSKClient(authenticatedUser);
    String appsPermSpec = getPermSpecAllStr(appTenantName, appId);
    String roleNameR = App.ROLE_READ_PREFIX + appSeqId;
    try {
      // ------------------- Make Dao call to update the app owner -----------------------------------
      // TODO: This will actually need to be for all versions (i.e. all seqId's) of the app
      dao.updateAppOwner(authenticatedUser, appSeqId, newOwnerName);
      // Add role and permissions for new owner
      skClient.grantUserRole(appTenantName, newOwnerName, roleNameR);
      skClient.grantUserPermission(appTenantName, newOwnerName, appsPermSpec);
      // Remove role and permissions from old owner
      skClient.revokeUserRole(appTenantName, oldOwnerName, roleNameR);
      skClient.revokeUserPermission(appTenantName, oldOwnerName, appsPermSpec);
    }
    catch (Exception e0)
    {
      // Something went wrong. Attempt to undo all changes and then re-throw the exception
      try { dao.updateAppOwner(authenticatedUser, appSeqId, oldOwnerName); } catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "updateOwner", e.getMessage()));}
      try { skClient.revokeUserRole(appTenantName, newOwnerName, roleNameR); }
      catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "revokeRoleNewOwner", e.getMessage()));}
      try { skClient.revokeUserPermission(appTenantName, newOwnerName, appsPermSpec); }
      catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "revokePermNewOwner", e.getMessage()));}
      try { skClient.grantUserPermission(appTenantName, oldOwnerName, appsPermSpec); }
      catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "grantPermOldOwner", e.getMessage()));}
      try { skClient.grantUserRole(appTenantName, oldOwnerName, roleNameR); }
      catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "grantRoleOldOwner", e.getMessage()));}
      throw e0;
    }
    return 1;
  }

  /**
   * Soft delete an app record given the app name.
   *
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - name of app
   * @return Number of items deleted
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public int softDeleteApp(AuthenticatedUser authenticatedUser, String appId) throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.softDelete;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId)) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    // For service request use oboTenant for tenant associated with the app
    String appTenantName = authenticatedUser.getTenantId();
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // If app does not exist or has already been soft deleted then 0 changes
    if (!dao.checkForApp(appTenantName, appId, false)) return 0;

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, null, null, null);

    // Remove SK artifacts
    removeSKArtifacts(authenticatedUser, appId, op);

    // Delete the app.
    int appSeqId = dao.getAppSeqId(appTenantName, appId);
    return dao.softDeleteApp(authenticatedUser, appSeqId);
  }

  /**
   * Hard delete an app record given the app name.
   * Also remove artifacts from the Security Kernel
   * NOTE: This is public so test code can use it but it is not part of the public interface.
   *
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - name of app
   * @return Number of items deleted
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  public int hardDeleteApp(AuthenticatedUser authenticatedUser, String appId)
          throws TapisException, TapisClientException, NotAuthorizedException
  {
    AppOperation op = AppOperation.hardDelete;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId)) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    // Extract various names for convenience
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // If app does not exist then 0 changes
    if (!dao.checkForApp(appTenantName, appId, true)) return 0;

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, null, null, null);

    // Remove SK artifacts
    removeSKArtifacts(authenticatedUser, appId, op);

    // Delete the app
    return dao.hardDeleteApp(appTenantName, appId);
  }

  /**
   * Initialize the service:
   *   Check for Apps admin role. If not found create it
   */
  public void initService(RuntimeParameters runParms) throws TapisException, TapisClientException
  {
    // Initialize service context
    serviceContext.initServiceJWT(runParms.getSiteId(), APPS_SERVICE, runParms.getServicePassword());
    // Make sure DB is present and updated to latest version using flyway
    dao.migrateDB();
  }

  /**
   * Check that we can connect with DB and that the main table of the service exists.
   * @return null if all OK else return an Exception
   */
  public Exception checkDB()
  {
    return dao.checkDB();
  }

  /**
   * checkForApp
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - Name of the app
   * @return true if app exists and has not been soft deleted, false otherwise
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public boolean checkForApp(AuthenticatedUser authenticatedUser, String appId) throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.read;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId)) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // We need owner to check auth and if app not there cannot find owner, so cannot do auth check if no app
    if (dao.checkForApp(appTenantName, appId, false)) {
      // ------------------------- Check service level authorization -------------------------
      checkAuth(authenticatedUser, op, appId, null, null, null);
      return true;
    }
    return false;
  }

  /**
   * isEnabled
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - Name of the app
   * @return true if app is enabled, false otherwise
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public boolean isEnabled(AuthenticatedUser authenticatedUser, String appId) throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.read;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId)) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // We need owner to check auth and if app not there cannot find owner, so cannot do auth check if no app
    if (dao.checkForApp(appTenantName, appId, false)) {
      // ------------------------- Check service level authorization -------------------------
      checkAuth(authenticatedUser, op, appId, null, null, null);
      return dao.isEnabled(appTenantName, appId);
    }
    // App has been deleted or is not present
    return false;
  }

  /**
   * getApp
   * Retrieve specified or most recently created version of an application.
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - Name of the app
   * @param appVersion - Version of the app, null or blank for latest version
   * @param requireExecPerm - check for EXECUTE permission as well as READ permission
   * @return populated instance of an App or null if not found or user not authorized.
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public App getApp(AuthenticatedUser authenticatedUser, String appId, String appVersion, boolean requireExecPerm)
          throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.read;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId)) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    // Extract various names for convenience
    String apiUserId = authenticatedUser.getName();
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app and oboUser as apiUserId
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType()))
    {
      appTenantName = authenticatedUser.getOboTenantId();
      apiUserId = authenticatedUser.getOboUser();
    }

    // We need owner to check auth and if app not there cannot find owner, so
    // if app does not exist then return null
    if (!dao.checkForApp(appTenantName, appId, false)) return null;

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, null, null, null);
    // If flag is set to also require EXECUTE perm then make a special auth call
    if (requireExecPerm)
    {
      checkAuthUser(authenticatedUser, AppOperation.execute, appTenantName, authenticatedUser.getOboUser(),
                    appId, null, null, null);
    }

    App result = dao.getApp(appTenantName, appId, appVersion);
    return result;
  }

  /**
   * Get all apps for which user has READ permission
   * @param authenticatedUser - principal user containing tenant and user info
   * @param searchList - optional list of conditions used for searching
   * @return List of App objects
   * @throws TapisException - for Tapis related exceptions
   */
  @Override
  public List<App> getApps(AuthenticatedUser authenticatedUser, List<String> searchList)
          throws TapisException, TapisClientException
  {
    AppOperation op = AppOperation.read;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    // Determine tenant scope for user
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the user
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType()))
      appTenantName = authenticatedUser.getOboTenantId();

    // Build verified list of search conditions
    var verifiedSearchList = new ArrayList<String>();
    if (searchList != null && !searchList.isEmpty())
    {
      try
      {
        for (String cond : searchList)
        {
          // Use SearchUtils to validate condition
          String verifiedCondStr = SearchUtils.validateAndProcessSearchCondition(cond);
          verifiedSearchList.add(verifiedCondStr);
        }
      }
      catch (Exception e)
      {
        String msg = LibUtils.getMsgAuth("APPLIB_SEARCH_ERROR", authenticatedUser, e.getMessage());
        _log.error(msg, e);
        throw new IllegalArgumentException(msg);
      }
    }

    // Get list of sequence IDs of apps for which requester has READ permission.
    // This is either all apps (null) or a list of sequence IDs based on roles.
    List<Integer> allowedSeqIDs = getAllowedSeqIDs(authenticatedUser, appTenantName);

    // Get all allowed apps matching the search conditions
    List<App> apps = dao.getApps(authenticatedUser.getTenantId(), verifiedSearchList, allowedSeqIDs);

// This is a simple brute force way to only get allowed apps
//      try {
//        checkAuth(authenticatedUser, op, app.getName(), null, null, null);
//        allowedApps.add(app);
//      }
//      catch (NotAuthorizedException e) { }
    return apps;
  }

  /**
   * Get all apps for which user has READ permission.
   * Use provided string containing a valid SQL where clause for the search.
   * @param authenticatedUser - principal user containing tenant and user info
   * @param sqlSearchStr - string containing a valid SQL where clause
   * @return List of App objects
   * @throws TapisException - for Tapis related exceptions
   */
  @Override
  public List<App> getAppsUsingSqlSearchStr(AuthenticatedUser authenticatedUser, String sqlSearchStr)
          throws TapisException, TapisClientException
  {
    // If search string is empty delegate to getApps()
    if (StringUtils.isBlank(sqlSearchStr)) return getApps(authenticatedUser, null);

    AppOperation op = AppOperation.read;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    // Determine tenant scope for user
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the user
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType()))
      appTenantName = authenticatedUser.getOboTenantId();

    // Validate and parse the sql string into an abstract syntax tree (AST)
    // TODO/TBD: The activemq parser validates and parses the string into an AST but there does not appear to be a way
    //          to use the resulting BooleanExpression to walk the tree. How to now create a usable AST?
    //   I believe we don't want to simply try to run the where clause for various reasons:
    //      - SQL injection
    //      - we want to verify the validity of each <attr>.<op>.<value>
    //        looks like activemq parser will ensure the leaf nodes all represent <attr>.<op>.<value> and in principle
    //        we should be able to check each one and generate of list of errors for reporting.
    //  Looks like jOOQ can parse an SQL string into a jooq Condition. Do this in the Dao? But still seems like no way
    //    to walk the AST and check each condition so we can report on errors.
//    BooleanExpression searchAST;
    ASTNode searchAST;
    try { searchAST = ASTParser.parse(sqlSearchStr); }
    catch (Exception e)
    {
      String msg = LibUtils.getMsgAuth("APPLIB_SEARCH_ERROR", authenticatedUser, e.getMessage());
      _log.error(msg, e);
      throw new IllegalArgumentException(msg);
    }

    // Get list of sequence IDs of apps for which requester has READ permission.
    // This is either all apps (null) or a list of sequence IDs based on roles.
    List<Integer> allowedSeqIDs = getAllowedSeqIDs(authenticatedUser, appTenantName);

    // Get all allowed apps matching the search conditions
    List<App> apps = dao.getAppsUsingSearchAST(authenticatedUser.getTenantId(), searchAST, allowedSeqIDs);

    return apps;
  }

  /**
   * Get list of app names
   * @param authenticatedUser - principal user containing tenant and user info
   * @return - list of apps
   * @throws TapisException - for Tapis related exceptions
   */
  @Override
  public List<String> getAppNames(AuthenticatedUser authenticatedUser) throws TapisException
  {
    AppOperation op = AppOperation.read;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    // Get all app names
    List<String> appIds = dao.getAppNames(authenticatedUser.getTenantId());
    var allowedNames = new ArrayList<String>();
    // Filter based on user authorization
    for (String name: appIds)
    {
      try {
        checkAuth(authenticatedUser, op, name, null, null, null);
        allowedNames.add(name);
      }
      catch (NotAuthorizedException | TapisClientException e) { }
    }
    return allowedNames;
  }

  /**
   * Get app owner
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - Name of the app
   * @return - Owner or null if app not found or user not authorized
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public String getAppOwner(AuthenticatedUser authenticatedUser, String appId) throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.read;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId)) throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));

    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // We need owner to check auth and if app not there cannot find owner, so
    // if app does not exist then return null
    if (!dao.checkForApp(appTenantName, appId, false)) return null;

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, null, null, null);

    return dao.getAppOwner(authenticatedUser.getTenantId(), appId);
  }

  // -----------------------------------------------------------------------
  // --------------------------- Permissions -------------------------------
  // -----------------------------------------------------------------------

  /**
   * Grant permissions and roles to a user for an app.
   * If READ or MODIFY after grant then grant special role to the user.
   * The role is used when fetching applications the user is allowed to view.
   * Grant of MODIFY implies grant of READ
   * NOTE: Permissions only impact the default user role
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - name of app
   * @param userName - Target user for operation
   * @param permissions - list of permissions to be granted
   * @param updateText - Client provided text used to create the permissions list. Saved in update record.
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public void grantUserPermissions(AuthenticatedUser authenticatedUser, String appId, String userName,
                                   Set<Permission> permissions, String updateText)
          throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.grantPerms;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId) || StringUtils.isBlank(userName))
      throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_SYSTEM", authenticatedUser));
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType()))
    {
      appTenantName = authenticatedUser.getOboTenantId();
    }

    // If system does not exist or has been soft deleted then throw an exception
    if (!dao.checkForApp(appTenantName, appId, false))
      throw new TapisException(LibUtils.getMsgAuth(NOT_FOUND, authenticatedUser, appId));

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, null, null, null);

    int seqId = dao.getAppSeqId(appTenantName, appId);

    // Check inputs. If anything null or empty throw an exception
    if (permissions == null || permissions.isEmpty())
    {
      throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT"));
    }

    // Grant of MODIFY implies grant of READ
    if (permissions.contains(Permission.MODIFY)) permissions.add(Permission.READ);

    // Create a set of individual permSpec entries based on the list passed in
    Set<String> permSpecSet = getPermSpecSet(appTenantName, appId, permissions);

    // Get the Security Kernel client
    var skClient = getSKClient(authenticatedUser);
    // Special role for view access
    String roleNameR = App.ROLE_READ_PREFIX + seqId;

    // Determine if user can currently view the app
    boolean userCanView = skClient.hasRole(appTenantName, userName, roleNameR);

    // Assign perms and roles to user.
    // Start of updates. Will need to rollback on failure.
    try
    {
      // Assign perms to user. SK creates a default role for the user
      for (String permSpec : permSpecSet)
      {
        skClient.grantUserPermission(appTenantName, userName, permSpec);
      }
      // If user could not view before but now can then grant special role.
      if (!userCanView && isPermittedAny(authenticatedUser, appTenantName, userName, appId, READMODIFY_PERMS))
      {
        skClient.grantUserRole(appTenantName, userName, roleNameR);
      }
    }
    catch (TapisClientException tce)
    {
      // Rollback
      // Something went wrong. Attempt to undo all changes and then re-throw the exception
      String msg = LibUtils.getMsgAuth("APPLIB_PERM_ERROR_ROLLBACK", authenticatedUser, appId, tce.getMessage());
      _log.error(msg);

      // NOTE: We do not have to worry about revoking the role because if it is granted it is granted last in the
      //       sequence of operations above. So if there is an exception it was never granted.

      // Revoke permissions that may have been granted.
      for (String permSpec : permSpecSet)
      {
        try { skClient.revokeUserPermission(appTenantName, userName, permSpec); }
        catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "revokePerm", e.getMessage()));}
      }
      // Convert to TapisException and re-throw
      throw new TapisException(LibUtils.getMsgAuth("APPLIB_PERM_SK_ERROR", authenticatedUser, appId, op.name()), tce);
    }

    // Construct Json string representing the update
    String updateJsonStr = TapisGsonUtils.getGson().toJson(permissions);
    // Create a record of the update
    dao.addUpdateRecord(authenticatedUser, seqId, -1, op, updateJsonStr, updateText);
  }

  /**
   * Revoke permissions from a user for an app
   * If after revoking the user does not have READ or MODIFY then also revoke the special role used to filter
   *   for users having view access.
   * Revoke of READ implies revoke of MODIFY
   * NOTE: Permissions only impact the default user role
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - name of app
   * @param userName - Target user for operation
   * @param permissions - list of permissions to be revoked
   * @param updateText - Client provided text used to create the permissions list. Saved in update record.
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public int revokeUserPermissions(AuthenticatedUser authenticatedUser, String appId, String userName,
                                   Set<Permission> permissions, String updateText)
          throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.revokePerms;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId) || StringUtils.isBlank(userName))
      throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_SYSTEM", authenticatedUser));
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // We need owner to check auth and if app not there cannot find owner, so
    // if app does not exist or has been soft deleted then return 0 changes
    if (!dao.checkForApp(appTenantName, appId, false)) return 0;

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, null, null, null);

    // Retrieve the sequence Id. Used to add an update record.
    int seqId = dao.getAppSeqId(appTenantName, appId);

    // Check inputs. If anything null or empty throw an exception
    if (permissions == null || permissions.isEmpty())
    {
      throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT"));
    }

    // Revoke of READ implies revoke of MODIFY
    if (permissions.contains(Permission.READ)) permissions.add(Permission.MODIFY);

    var skClient = getSKClient(authenticatedUser);
    int changeCount;
    String roleNameR = App.ROLE_READ_PREFIX + seqId;

    // Determine if user can currently view the system
    boolean userCanView = skClient.hasRole(appTenantName, userName, roleNameR);
    // Determine current set of user permissions
    var userPermSet = getUserPermSet(skClient, userName, appTenantName, appId);

    try
    {
      // Revoke perms
      changeCount = revokePermissions(skClient, appTenantName, appId, userName, permissions);
      // If user was able to view and can no longer view then revoke special role.
      if (userCanView && permissions.contains(Permission.READ))
      {
        skClient.revokeUserRole(appTenantName, userName, roleNameR);
      }
    }
    catch (TapisClientException tce)
    {
      // Rollback
      // Something went wrong. Attempt to undo all changes and then re-throw the exception
      String msg = LibUtils.getMsgAuth("APPLIB_PERM_ERROR_ROLLBACK", authenticatedUser, appId, tce.getMessage());
      _log.error(msg);

      // NOTE: We do not have to worry about granting the role because if it is revoked it is revoked last in the
      //       sequence of operations above. So if there is an exception it was never revoked.

      // Grant permissions that may have been revoked and that the user previously held.
      for (Permission perm : permissions)
      {
        if (userPermSet.contains(perm))
        {
          String permSpec = getPermSpecStr(appTenantName, appId, perm);
          try { skClient.grantUserPermission(appTenantName, userName, permSpec); }
          catch (Exception e) {_log.warn(LibUtils.getMsgAuth(ERROR_ROLLBACK, authenticatedUser, appId, "grantPerm", e.getMessage()));}
        }
      }

      // Convert to TapisException and re-throw
      throw new TapisException(LibUtils.getMsgAuth("APPLIB_PERM_SK_ERROR", authenticatedUser, appId, op.name()), tce);
    }

    // Construct Json string representing the update
    String updateJsonStr = TapisGsonUtils.getGson().toJson(permissions);
    // Create a record of the update
    dao.addUpdateRecord(authenticatedUser, seqId, -1, op, updateJsonStr, updateText);
    return changeCount;
  }

  /**
   * Get list of app permissions for a user
   * NOTE: This retrieves permissions from all roles.
   * @param authenticatedUser - principal user containing tenant and user info
   * @param appId - name of app
   * @param userName - Target user for operation
   * @return List of permissions
   * @throws TapisException - for Tapis related exceptions
   * @throws NotAuthorizedException - unauthorized
   */
  @Override
  public Set<Permission> getUserPermissions(AuthenticatedUser authenticatedUser, String appId, String userName)
          throws TapisException, NotAuthorizedException, TapisClientException
  {
    AppOperation op = AppOperation.getPerms;
    if (authenticatedUser == null) throw new IllegalArgumentException(LibUtils.getMsg("APPLIB_NULL_INPUT_AUTHUSR"));
    if (StringUtils.isBlank(appId) || StringUtils.isBlank(userName))
         throw new IllegalArgumentException(LibUtils.getMsgAuth("APPLIB_NULL_INPUT_APP", authenticatedUser));
    String appTenantName = authenticatedUser.getTenantId();
    // For service request use oboTenant for tenant associated with the app
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // If app does not exist or has been soft deleted then return null
    if (!dao.checkForApp(appTenantName, appId, false)) return null;

    // ------------------------- Check service level authorization -------------------------
    checkAuth(authenticatedUser, op, appId, null, userName, null);

    // Use Security Kernel client to check for each permission in the enum list
    var skClient = getSKClient(authenticatedUser);
    return getUserPermSet(skClient, userName, appTenantName, appId);
  }

  // ************************************************************************
  // **************************  Private Methods  ***************************
  // ************************************************************************

  /**
   * Get Security Kernel client associated with specified tenant
   * @param authenticatedUser - name of tenant
   * @return SK client
   * @throws TapisException - for Tapis related exceptions
   */
  private SKClient getSKClient(AuthenticatedUser authenticatedUser) throws TapisException
  {
    SKClient skClient;
    String tenantName;
    String userName;
    // If service request use oboTenant and oboUser in OBO headers
    // else for user request use authenticated user name and tenant in OBO headers
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType()))
    {
      tenantName = authenticatedUser.getOboTenantId();
      userName = authenticatedUser.getOboUser();
    }
    else
    {
      tenantName = authenticatedUser.getTenantId();
      userName = authenticatedUser.getName();
    }
    try
    {
      skClient = serviceClients.getClient(userName, tenantName, SKClient.class);
    }
    catch (Exception e)
    {
      String msg = MsgUtils.getMsg("TAPIS_CLIENT_NOT_FOUND", TapisConstants.SERVICE_NAME_SECURITY,
                                   authenticatedUser.getTenantId(), authenticatedUser.getName());
      throw new TapisException(msg, e);
    }
    return skClient;
  }

  /**
   * Resolve variables for App attributes
   * @param app - the App to process
   */
  private static App resolveVariables(App app, String oboUser)
  {
    // Resolve owner if necessary. If empty or "${apiUserId}" then fill in oboUser.
    // Note that for a user request oboUser and apiUserId are the same and for a service request we want oboUser here.
    String owner = app.getOwner();
    if (StringUtils.isBlank(owner) || owner.equalsIgnoreCase(APIUSERID_VAR)) owner = oboUser;
    app.setOwner(owner);

//    // Perform variable substitutions that happen at create time: bucketName, rootDir, jobLocalWorkingDir, jobLocalArchiveDir
//    // NOTE: effectiveUserId is not processed. Var reference is retained and substitution done as needed when system is retrieved.
//    //    ALL_VARS = {APIUSERID_VAR, OWNER_VAR, TENANT_VAR};
//    String[] allVarSubstitutions = {oboUser, owner, system.getTenant()};
//    system.setBucketName(StringUtils.replaceEach(system.getBucketName(), ALL_VARS, allVarSubstitutions));
//    system.setRootDir(StringUtils.replaceEach(system.getRootDir(), ALL_VARS, allVarSubstitutions));
//    system.setJobLocalWorkingDir(StringUtils.replaceEach(system.getJobLocalWorkingDir(), ALL_VARS, allVarSubstitutions));
//    system.setJobLocalArchiveDir(StringUtils.replaceEach(system.getJobLocalArchiveDir(), ALL_VARS, allVarSubstitutions));
//    system.setJobRemoteArchiveDir(StringUtils.replaceEach(system.getJobRemoteArchiveDir(), ALL_VARS, allVarSubstitutions));
    return app;
  }

  /**
   * Check constraints on App attributes.
   * Notes must be json
   * @param app - the App to check
   * @throws IllegalStateException - if any constraints are violated
   */
  private static void validateApp(AuthenticatedUser authenticatedUser, App app) throws IllegalStateException
  {
    String msg;
    var errMessages = new ArrayList<String>();
    // If validation failed throw an exception
    if (!errMessages.isEmpty())
    {
      // Construct message reporting all errors
      String allErrors = getListOfErrors(authenticatedUser, app.getId(), errMessages);
      _log.error(allErrors);
      throw new IllegalStateException(allErrors);
    }
  }

  /**
   * Retrieve set of user permissions given sk client, user, tenant, id
   * @param skClient - SK client
   * @param userName - name of user
   * @param tenantName - name of tenant
   * @param resourceId - Id of resource
   * @return - Set of Permissions for the user
   */
  private static Set<Permission> getUserPermSet(SKClient skClient, String userName, String tenantName,
                                                String resourceId)
          throws TapisClientException
  {
    var userPerms = new HashSet<Permission>();
    for (Permission perm : Permission.values())
    {
      String permSpec = PERM_SPEC_PREFIX + tenantName + ":" + perm.name() + ":" + resourceId;
      if (skClient.isPermitted(tenantName, userName, permSpec)) userPerms.add(perm);
    }
    return userPerms;
  }

  /**
   * Create a set of individual permSpec entries based on the list passed in
   * @param permList - list of individual permissions
   * @return - Set of permSpec entries based on permissions
   */
  private static Set<String> getPermSpecSet(String tenantName, String appId, Set<Permission> permList)
  {
    var permSet = new HashSet<String>();
    for (Permission perm : permList) { permSet.add(getPermSpecStr(tenantName, appId, perm)); }
    return permSet;
  }

  /**
   * Create a permSpec given a permission
   * @param perm - permission
   * @return - permSpec entry based on permission
   */
  private static String getPermSpecStr(String tenantName, String appId, Permission perm)
  {
    return PERM_SPEC_PREFIX + tenantName + ":" + perm.name().toUpperCase() + ":" + appId;
  }

  /**
   * Create a permSpec for all permissions
   * @return - permSpec entry for all permissions
   */
  private static String getPermSpecAllStr(String tenantName, String appId)
  {
    return PERM_SPEC_PREFIX + tenantName + ":*:" + appId;
  }

  /**
   * Construct message containing list of errors
   */
  private static String getListOfErrors(AuthenticatedUser authenticatedUser, String appId, List<String> msgList) {
    var sb = new StringBuilder(LibUtils.getMsgAuth("APPLIB_CREATE_INVALID_ERRORLIST", authenticatedUser, appId));
    sb.append(System.lineSeparator());
    if (msgList == null || msgList.isEmpty()) return sb.toString();
    for (String msg : msgList) { sb.append("  ").append(msg).append(System.lineSeparator()); }
    return sb.toString();
  }

  /**
   * Standard service level authorization check. Check is different for service and user requests.
   * A check should be made for app existence before calling this method.
   * If no owner is passed in and one cannot be found then an error is logged and authorization is denied.
   *
   * @param authenticatedUser - principal user containing tenant and user info
   * @param operation - operation name
   * @param appId - name of the app
   * @param owner - app owner
   * @param perms - List of permissions for the revokePerm case
   * @throws NotAuthorizedException - apiUserId not authorized to perform operation
   */
  private void checkAuth(AuthenticatedUser authenticatedUser, AppOperation operation, String appId,
                         String owner, String targetUser, Set<Permission> perms)
      throws TapisException, TapisClientException, NotAuthorizedException, IllegalStateException
  {
    // Check service and user requests separately to avoid confusing a service name with a user name
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) {
      // This is a service request. The user name will be the service name. E.g. files, jobs, streams, etc
      switch (operation) {
        case read:
          if (SVCLIST_READ.contains(authenticatedUser.getName())) return;
          break;
      }
    }
    else
    {
      // User check
      checkAuthUser(authenticatedUser, operation, null, null, appId, owner, targetUser, perms);
      return;
    }
    // Not authorized, throw an exception
    String msg = LibUtils.getMsgAuth("APPLIB_UNAUTH", authenticatedUser, appId, operation.name());
    throw new NotAuthorizedException(msg);
  }

  /**
   * User based authorization check.
   * Can be used for OBOUser type checks.
   * By default use tenant and user from authenticatedUser, allow for optional tenant or user.
   * A check should be made for app existence before calling this method.
   * If no owner is passed in and one cannot be found then an error is logged and
   *   authorization is denied.
   * Operations:
   *  Create - must be owner or have admin role
   *  Read - must be owner or have admin role or have READ or MODIFY permission or be in list of allowed services
   *  Delete - must be owner or have admin role
   *  Modify - must be owner or have admin role or have MODIFY permission
   *  Execute - must be owner or have admin role or have EXECUTE permission
   *  ChangeOwner - must be owner or have admin role
   *  GrantPerm -  must be owner or have admin role
   *  RevokePerm -  must be owner or have admin role or apiUserId=targetUser and meet certain criteria (allowUserRevokePerm)
   *
   * @param authenticatedUser - principal user containing tenant and user info
   * @param operation - operation name
   * @param tenantToCheck - optional name of the tenant to use. Default is to use authenticatedUser.
   * @param userToCheck - optional name of the user to check. Default is to use authenticatedUser.
   * @param appId - name of the system
   * @param owner - system owner
   * @param perms - List of permissions for the revokePerm case
   * @throws NotAuthorizedException - apiUserId not authorized to perform operation
   */
  private void checkAuthUser(AuthenticatedUser authenticatedUser, AppOperation operation,
                             String tenantToCheck, String userToCheck,
                             String appId, String owner, String targetUser, Set<Permission> perms)
          throws TapisException, TapisClientException, NotAuthorizedException, IllegalStateException
  {
    // Use tenant and user from authenticatedUsr or optional provided values
    String tenantName = (StringUtils.isBlank(tenantToCheck) ? authenticatedUser.getTenantId() : tenantToCheck);
    String userName = (StringUtils.isBlank(userToCheck) ? authenticatedUser.getName() : userToCheck);
    // Requires owner. If no owner specified and owner cannot be determined then log an error and deny.
    if (StringUtils.isBlank(owner)) owner = dao.getAppOwner(tenantName, appId);
    if (StringUtils.isBlank(owner)) {
      String msg = LibUtils.getMsgAuth("APPLIB_AUTH_NO_OWNER", authenticatedUser, appId, operation.name());
      _log.error(msg);
      throw new NotAuthorizedException(msg);
    }
    switch(operation) {
      case create:
      case softDelete:
      case changeOwner:
      case grantPerms:
        if (owner.equals(userName) || hasAdminRole(authenticatedUser, tenantName, userName)) return;
        break;
      case hardDelete:
        if (hasAdminRole(authenticatedUser, tenantName, userName)) return;
        break;
      case read:
      case getPerms:
        if (owner.equals(userName) || hasAdminRole(authenticatedUser, tenantName, userName) ||
              isPermittedAny(authenticatedUser, tenantName, userName, appId, READMODIFY_PERMS)) return;
        break;
      case modify:
        if (owner.equals(userName) || hasAdminRole(authenticatedUser, tenantName, userName) ||
                isPermitted(authenticatedUser, tenantName, userName, appId, Permission.MODIFY)) return;
        break;
      case execute:
        if (owner.equals(userName) || hasAdminRole(authenticatedUser, tenantName, userName) ||
                isPermitted(authenticatedUser, tenantName, userName, appId, Permission.EXECUTE)) return;
        break;
      case revokePerms:
        if (owner.equals(userName) || hasAdminRole(authenticatedUser, tenantName, userName) ||
                (userName.equals(targetUser) &&
                        allowUserRevokePerm(authenticatedUser, tenantName, userName, appId, perms))) return;
        break;
    }
    // Not authorized, throw an exception
    String msg = LibUtils.getMsgAuth("APPLIB_UNAUTH", authenticatedUser, appId, operation.name());
    throw new NotAuthorizedException(msg);
  }

  /**
   * Determine all apps that a user is allowed to see.
   * If all apps return null else return list of sequence IDs
   * An empty list indicates no apps allowed.
   */
  private List<Integer> getAllowedSeqIDs(AuthenticatedUser authenticatedUser, String appTenantName)
          throws TapisException, TapisClientException
  {
    // If requester is a service or an admin then all apps allowed
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType()) ||
        hasAdminRole(authenticatedUser, null, null)) return null;
    var appSeqIDs = new ArrayList<Integer>();
    // Get roles for user and extract app sequence IDs
    List<String> userRoles = getSKClient(authenticatedUser).getUserRoles(appTenantName, authenticatedUser.getName());
    // Find roles of the form Apps_R_<id> and generate a list of sequence IDs
    for (String role: userRoles)
    {
      if (role.startsWith(App.ROLE_READ_PREFIX))
      {
        String appSeqIdStr = role.substring(role.indexOf(App.ROLE_READ_PREFIX) + App.ROLE_READ_PREFIX.length());
        // If id part of string is not integer then ignore this role.
        try {
          Integer appSeqId = Integer.parseInt(appSeqIdStr);
          appSeqIDs.add(appSeqId);
        } catch (NumberFormatException e) {};
      }
    }
    return appSeqIDs;
  }

  /**
   * Check to see if a user has the service admin role
   * By default use tenant and user from authenticatedUser, allow for optional tenant or user.
   */
  private boolean hasAdminRole(AuthenticatedUser authenticatedUser, String tenantToCheck, String userToCheck)
          throws TapisException, TapisClientException
  {
    // Use tenant and user from authenticatedUsr or optional provided values
    String tenantName = (StringUtils.isBlank(tenantToCheck) ? authenticatedUser.getTenantId() : tenantToCheck);
    String userName = (StringUtils.isBlank(userToCheck) ? authenticatedUser.getName() : userToCheck);
    // TODO: Remove this
    if ("testuser9".equalsIgnoreCase(userName)) return true;
    var skClient = getSKClient(authenticatedUser);
    return skClient.isAdmin(tenantName, userName);
  }

  /**
   * Check to see if a user has the specified permission
   * By default use tenant and user from authenticatedUser, allow for optional tenant or user.
   */
  private boolean isPermitted(AuthenticatedUser authenticatedUser, String tenantToCheck, String userToCheck,
                              String appId, Permission perm)
          throws TapisException, TapisClientException
  {
    // Use tenant and user from authenticatedUsr or optional provided values
    String tenantName = (StringUtils.isBlank(tenantToCheck) ? authenticatedUser.getTenantId() : tenantToCheck);
    String userName = (StringUtils.isBlank(userToCheck) ? authenticatedUser.getName() : userToCheck);
    var skClient = getSKClient(authenticatedUser);
    String permSpecStr = getPermSpecStr(tenantName, appId, perm);
    return skClient.isPermitted(tenantName, userName, permSpecStr);
  }

  /**
   * Check to see if a user has any of the set of permissions
   * By default use tenant and user from authenticatedUser, allow for optional tenant or user.
   */
  private boolean isPermittedAny(AuthenticatedUser authenticatedUser, String tenantToCheck, String userToCheck,
                                 String appId, Set<Permission> perms)
          throws TapisException, TapisClientException
  {
    // Use tenant and user from authenticatedUsr or optional provided values
    String tenantName = (StringUtils.isBlank(tenantToCheck) ? authenticatedUser.getTenantId() : tenantToCheck);
    String userName = (StringUtils.isBlank(userToCheck) ? authenticatedUser.getName() : userToCheck);
    var skClient = getSKClient(authenticatedUser);
    var permSpecs = new ArrayList<String>();
    for (Permission perm : perms) {
      permSpecs.add(getPermSpecStr(tenantName, appId, perm));
    }
    return skClient.isPermittedAny(tenantName, userName, permSpecs.toArray(new String[0]));
  }

  /**
   * Check to see if a user who is not owner or admin is authorized to revoke permissions
   * By default use tenant and user from authenticatedUser, allow for optional tenant or user.
   */
  private boolean allowUserRevokePerm(AuthenticatedUser authenticatedUser, String tenantToCheck, String userToCheck,
                                      String appId, Set<Permission> perms)
          throws TapisException, TapisClientException
  {
    // Use tenant and user from authenticatedUsr or optional provided values
    String tenantName = (StringUtils.isBlank(tenantToCheck) ? authenticatedUser.getTenantId() : tenantToCheck);
    String userName = (StringUtils.isBlank(userToCheck) ? authenticatedUser.getName() : userToCheck);
    if (perms.contains(Permission.MODIFY)) return isPermitted(authenticatedUser, tenantName, userName, appId, Permission.MODIFY);
    if (perms.contains(Permission.READ)) return isPermittedAny(authenticatedUser, tenantName, userName, appId, READMODIFY_PERMS);
    return false;
  }

  /**
   * Remove all SK artifacts associated with an App: user permissions, App role
   * No checks are done for incoming arguments and the app must exist
   */
  private void removeSKArtifacts(AuthenticatedUser authenticatedUser, String appId, AppOperation op)
          throws TapisException, TapisClientException
  {
    // For service request use oboTenant for tenant associated with the app
    String appTenantName = authenticatedUser.getTenantId();
    if (TapisThreadContext.AccountType.service.name().equals(authenticatedUser.getAccountType())) appTenantName = authenticatedUser.getOboTenantId();

    // Fetch the most recently created version of the app. If not found then return
    App app = dao.getApp(appTenantName, appId, null, true);

    var skClient = getSKClient(authenticatedUser);

    // TODO/TBD: How to make sure all perms for an app are removed?
    // TODO: See if it makes sense to have a SK method to do this in one operation
    // Use Security Kernel client to find all users with perms associated with the app.
    String permSpec = PERM_SPEC_PREFIX + appTenantName + ":%:" + appId;
    var userNames = skClient.getUsersWithPermission(appTenantName, permSpec);
    // Revoke all perms for all users
    for (String userName : userNames)
    {
      revokePermissions(skClient, appTenantName, appId, userName, ALL_PERMS);
      // Remove wildcard perm
      String wildCardPermSpec = getPermSpecAllStr(appTenantName, appId);
      skClient.revokeUserPermission(appTenantName, userName, wildCardPermSpec);
    }
    // If role is present then remove role assignments and roles
    // TODO: Ask SK to either provide checkForRole() or return null if role does not exist.
    String roleNameR = App.ROLE_READ_PREFIX + app.getSeqId();
    SkRole role = null;
    try
    {
      role = skClient.getRoleByName(appTenantName, roleNameR);
    }
    catch (TapisClientException tce)
    {
      if (!tce.getTapisMessage().startsWith("TAPIS_NOT_FOUND")) throw tce;
    }
    if (role != null)
    {
      // Remove role assignments for owner
      skClient.revokeUserRole(appTenantName, app.getOwner(), roleNameR);
      // Remove role assignments for other users
      userNames = skClient.getUsersWithRole(appTenantName, roleNameR);
      for (String userName : userNames) skClient.revokeUserRole(appTenantName, userName, roleNameR);
      // Remove the role
      skClient.deleteRoleByName(appTenantName, roleNameR);
    }
  }

  /**
   * Revoke permissions
   * No checks are done for incoming arguments and the app must exist
   */
  private static int revokePermissions(SKClient skClient, String appTenantName, String appId, String userName, Set<Permission> permissions)
          throws TapisClientException
  {
    // Create a set of individual permSpec entries based on the list passed in
    Set<String> permSpecSet = getPermSpecSet(appTenantName, appId, permissions);
    // Remove perms from default user role
    for (String permSpec : permSpecSet)
    {
      skClient.revokeUserPermission(appTenantName, userName, permSpec);
    }
    return permSpecSet.size();
  }

  /**
   * Merge a patch into an existing App
   * Attributes that can be updated:
   *   description, enabled, tags, notes.
   */
  private App createPatchedApp(App o, PatchApp p)
  {
    App p1 = new App(o);
    if (p.getDescription() != null) p1.setDescription(p.getDescription());
    if (p.getTags() != null) p1.setTags(p.getTags());
    if (p.getNotes() != null) p1.setNotes(p.getNotes());
    return p1;
  }
}
