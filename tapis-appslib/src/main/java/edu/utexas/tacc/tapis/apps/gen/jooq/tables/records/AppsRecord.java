/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables.records;


import com.google.gson.JsonElement;

import edu.utexas.tacc.tapis.apps.gen.jooq.tables.Apps;
import edu.utexas.tacc.tapis.apps.model.App.AppType;
import edu.utexas.tacc.tapis.apps.model.App.Runtime;

import java.time.LocalDateTime;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppsRecord extends UpdatableRecordImpl<AppsRecord> {

    private static final long serialVersionUID = 1934423116;

    /**
     * Setter for <code>tapis_app.apps.seq_id</code>. Application sequence id
     */
    public void setSeqId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tapis_app.apps.seq_id</code>. Application sequence id
     */
    public Integer getSeqId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tapis_app.apps.tenant</code>. Tenant name associated with the application
     */
    public void setTenant(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>tapis_app.apps.tenant</code>. Tenant name associated with the application
     */
    public String getTenant() {
        return (String) get(1);
    }

    /**
     * Setter for <code>tapis_app.apps.id</code>. Unique name for the application
     */
    public void setId(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tapis_app.apps.id</code>. Unique name for the application
     */
    public String getId() {
        return (String) get(2);
    }

    /**
     * Setter for <code>tapis_app.apps.version</code>. Application version
     */
    public void setVersion(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>tapis_app.apps.version</code>. Application version
     */
    public String getVersion() {
        return (String) get(3);
    }

    /**
     * Setter for <code>tapis_app.apps.description</code>. Application description
     */
    public void setDescription(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>tapis_app.apps.description</code>. Application description
     */
    public String getDescription() {
        return (String) get(4);
    }

    /**
     * Setter for <code>tapis_app.apps.app_type</code>. Type of application
     */
    public void setAppType(AppType value) {
        set(5, value);
    }

    /**
     * Getter for <code>tapis_app.apps.app_type</code>. Type of application
     */
    public AppType getAppType() {
        return (AppType) get(5);
    }

    /**
     * Setter for <code>tapis_app.apps.owner</code>. User name of application owner
     */
    public void setOwner(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>tapis_app.apps.owner</code>. User name of application owner
     */
    public String getOwner() {
        return (String) get(6);
    }

    /**
     * Setter for <code>tapis_app.apps.enabled</code>. Indicates if application is currently active and available for use
     */
    public void setEnabled(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>tapis_app.apps.enabled</code>. Indicates if application is currently active and available for use
     */
    public Boolean getEnabled() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>tapis_app.apps.runtime</code>.
     */
    public void setRuntime(Runtime value) {
        set(8, value);
    }

    /**
     * Getter for <code>tapis_app.apps.runtime</code>.
     */
    public Runtime getRuntime() {
        return (Runtime) get(8);
    }

    /**
     * Setter for <code>tapis_app.apps.runtime_version</code>.
     */
    public void setRuntimeVersion(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>tapis_app.apps.runtime_version</code>.
     */
    public String getRuntimeVersion() {
        return (String) get(9);
    }

    /**
     * Setter for <code>tapis_app.apps.container_image</code>.
     */
    public void setContainerImage(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>tapis_app.apps.container_image</code>.
     */
    public String getContainerImage() {
        return (String) get(10);
    }

    /**
     * Setter for <code>tapis_app.apps.max_jobs</code>.
     */
    public void setMaxJobs(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>tapis_app.apps.max_jobs</code>.
     */
    public Integer getMaxJobs() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>tapis_app.apps.max_jobs_per_user</code>.
     */
    public void setMaxJobsPerUser(Integer value) {
        set(12, value);
    }

    /**
     * Getter for <code>tapis_app.apps.max_jobs_per_user</code>.
     */
    public Integer getMaxJobsPerUser() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>tapis_app.apps.job_description</code>.
     */
    public void setJobDescription(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>tapis_app.apps.job_description</code>.
     */
    public String getJobDescription() {
        return (String) get(13);
    }

    /**
     * Setter for <code>tapis_app.apps.dynamic_exec_system</code>.
     */
    public void setDynamicExecSystem(Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>tapis_app.apps.dynamic_exec_system</code>.
     */
    public Boolean getDynamicExecSystem() {
        return (Boolean) get(14);
    }

    /**
     * Setter for <code>tapis_app.apps.exec_system_constraints</code>.
     */
    public void setExecSystemConstraints(String[] value) {
        set(15, value);
    }

    /**
     * Getter for <code>tapis_app.apps.exec_system_constraints</code>.
     */
    public String[] getExecSystemConstraints() {
        return (String[]) get(15);
    }

    /**
     * Setter for <code>tapis_app.apps.exec_system_id</code>.
     */
    public void setExecSystemId(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>tapis_app.apps.exec_system_id</code>.
     */
    public String getExecSystemId() {
        return (String) get(16);
    }

    /**
     * Setter for <code>tapis_app.apps.exec_system_exec_dir</code>.
     */
    public void setExecSystemExecDir(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>tapis_app.apps.exec_system_exec_dir</code>.
     */
    public String getExecSystemExecDir() {
        return (String) get(17);
    }

    /**
     * Setter for <code>tapis_app.apps.exec_system_input_dir</code>.
     */
    public void setExecSystemInputDir(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>tapis_app.apps.exec_system_input_dir</code>.
     */
    public String getExecSystemInputDir() {
        return (String) get(18);
    }

    /**
     * Setter for <code>tapis_app.apps.exec_system_output_dir</code>.
     */
    public void setExecSystemOutputDir(String value) {
        set(19, value);
    }

    /**
     * Getter for <code>tapis_app.apps.exec_system_output_dir</code>.
     */
    public String getExecSystemOutputDir() {
        return (String) get(19);
    }

    /**
     * Setter for <code>tapis_app.apps.exec_system_logical_queue</code>.
     */
    public void setExecSystemLogicalQueue(String value) {
        set(20, value);
    }

    /**
     * Getter for <code>tapis_app.apps.exec_system_logical_queue</code>.
     */
    public String getExecSystemLogicalQueue() {
        return (String) get(20);
    }

    /**
     * Setter for <code>tapis_app.apps.archive_system_id</code>.
     */
    public void setArchiveSystemId(String value) {
        set(21, value);
    }

    /**
     * Getter for <code>tapis_app.apps.archive_system_id</code>.
     */
    public String getArchiveSystemId() {
        return (String) get(21);
    }

    /**
     * Setter for <code>tapis_app.apps.archive_system_dir</code>.
     */
    public void setArchiveSystemDir(String value) {
        set(22, value);
    }

    /**
     * Getter for <code>tapis_app.apps.archive_system_dir</code>.
     */
    public String getArchiveSystemDir() {
        return (String) get(22);
    }

    /**
     * Setter for <code>tapis_app.apps.archive_on_app_error</code>.
     */
    public void setArchiveOnAppError(Boolean value) {
        set(23, value);
    }

    /**
     * Getter for <code>tapis_app.apps.archive_on_app_error</code>.
     */
    public Boolean getArchiveOnAppError() {
        return (Boolean) get(23);
    }

    /**
     * Setter for <code>tapis_app.apps.env_variables</code>.
     */
    public void setEnvVariables(String[] value) {
        set(24, value);
    }

    /**
     * Getter for <code>tapis_app.apps.env_variables</code>.
     */
    public String[] getEnvVariables() {
        return (String[]) get(24);
    }

    /**
     * Setter for <code>tapis_app.apps.archive_includes</code>.
     */
    public void setArchiveIncludes(String[] value) {
        set(25, value);
    }

    /**
     * Getter for <code>tapis_app.apps.archive_includes</code>.
     */
    public String[] getArchiveIncludes() {
        return (String[]) get(25);
    }

    /**
     * Setter for <code>tapis_app.apps.archive_excludes</code>.
     */
    public void setArchiveExcludes(String[] value) {
        set(26, value);
    }

    /**
     * Getter for <code>tapis_app.apps.archive_excludes</code>.
     */
    public String[] getArchiveExcludes() {
        return (String[]) get(26);
    }

    /**
     * Setter for <code>tapis_app.apps.node_count</code>.
     */
    public void setNodeCount(Integer value) {
        set(27, value);
    }

    /**
     * Getter for <code>tapis_app.apps.node_count</code>.
     */
    public Integer getNodeCount() {
        return (Integer) get(27);
    }

    /**
     * Setter for <code>tapis_app.apps.cores_per_node</code>.
     */
    public void setCoresPerNode(Integer value) {
        set(28, value);
    }

    /**
     * Getter for <code>tapis_app.apps.cores_per_node</code>.
     */
    public Integer getCoresPerNode() {
        return (Integer) get(28);
    }

    /**
     * Setter for <code>tapis_app.apps.memory_mb</code>.
     */
    public void setMemoryMb(Integer value) {
        set(29, value);
    }

    /**
     * Getter for <code>tapis_app.apps.memory_mb</code>.
     */
    public Integer getMemoryMb() {
        return (Integer) get(29);
    }

    /**
     * Setter for <code>tapis_app.apps.max_minutes</code>.
     */
    public void setMaxMinutes(Integer value) {
        set(30, value);
    }

    /**
     * Getter for <code>tapis_app.apps.max_minutes</code>.
     */
    public Integer getMaxMinutes() {
        return (Integer) get(30);
    }

    /**
     * Setter for <code>tapis_app.apps.job_tags</code>.
     */
    public void setJobTags(String[] value) {
        set(31, value);
    }

    /**
     * Getter for <code>tapis_app.apps.job_tags</code>.
     */
    public String[] getJobTags() {
        return (String[]) get(31);
    }

    /**
     * Setter for <code>tapis_app.apps.tags</code>. Tags for user supplied key:value pairs
     */
    public void setTags(String[] value) {
        set(32, value);
    }

    /**
     * Getter for <code>tapis_app.apps.tags</code>. Tags for user supplied key:value pairs
     */
    public String[] getTags() {
        return (String[]) get(32);
    }

    /**
     * Setter for <code>tapis_app.apps.notes</code>. Notes for general information stored as JSON
     */
    public void setNotes(JsonElement value) {
        set(33, value);
    }

    /**
     * Getter for <code>tapis_app.apps.notes</code>. Notes for general information stored as JSON
     */
    public JsonElement getNotes() {
        return (JsonElement) get(33);
    }

    /**
     * Setter for <code>tapis_app.apps.import_ref_id</code>. Optional reference ID for applications created via import
     */
    public void setImportRefId(String value) {
        set(34, value);
    }

    /**
     * Getter for <code>tapis_app.apps.import_ref_id</code>. Optional reference ID for applications created via import
     */
    public String getImportRefId() {
        return (String) get(34);
    }

    /**
     * Setter for <code>tapis_app.apps.deleted</code>. Indicates if application has been soft deleted
     */
    public void setDeleted(Boolean value) {
        set(35, value);
    }

    /**
     * Getter for <code>tapis_app.apps.deleted</code>. Indicates if application has been soft deleted
     */
    public Boolean getDeleted() {
        return (Boolean) get(35);
    }

    /**
     * Setter for <code>tapis_app.apps.created</code>. UTC time for when record was created
     */
    public void setCreated(LocalDateTime value) {
        set(36, value);
    }

    /**
     * Getter for <code>tapis_app.apps.created</code>. UTC time for when record was created
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(36);
    }

    /**
     * Setter for <code>tapis_app.apps.updated</code>. UTC time for when record was last updated
     */
    public void setUpdated(LocalDateTime value) {
        set(37, value);
    }

    /**
     * Getter for <code>tapis_app.apps.updated</code>. UTC time for when record was last updated
     */
    public LocalDateTime getUpdated() {
        return (LocalDateTime) get(37);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AppsRecord
     */
    public AppsRecord() {
        super(Apps.APPS);
    }

    /**
     * Create a detached, initialised AppsRecord
     */
    public AppsRecord(Integer seqId, String tenant, String id, String version, String description, AppType appType, String owner, Boolean enabled, Runtime runtime, String runtimeVersion, String containerImage, Integer maxJobs, Integer maxJobsPerUser, String jobDescription, Boolean dynamicExecSystem, String[] execSystemConstraints, String execSystemId, String execSystemExecDir, String execSystemInputDir, String execSystemOutputDir, String execSystemLogicalQueue, String archiveSystemId, String archiveSystemDir, Boolean archiveOnAppError, String[] envVariables, String[] archiveIncludes, String[] archiveExcludes, Integer nodeCount, Integer coresPerNode, Integer memoryMb, Integer maxMinutes, String[] jobTags, String[] tags, JsonElement notes, String importRefId, Boolean deleted, LocalDateTime created, LocalDateTime updated) {
        super(Apps.APPS);

        set(0, seqId);
        set(1, tenant);
        set(2, id);
        set(3, version);
        set(4, description);
        set(5, appType);
        set(6, owner);
        set(7, enabled);
        set(8, runtime);
        set(9, runtimeVersion);
        set(10, containerImage);
        set(11, maxJobs);
        set(12, maxJobsPerUser);
        set(13, jobDescription);
        set(14, dynamicExecSystem);
        set(15, execSystemConstraints);
        set(16, execSystemId);
        set(17, execSystemExecDir);
        set(18, execSystemInputDir);
        set(19, execSystemOutputDir);
        set(20, execSystemLogicalQueue);
        set(21, archiveSystemId);
        set(22, archiveSystemDir);
        set(23, archiveOnAppError);
        set(24, envVariables);
        set(25, archiveIncludes);
        set(26, archiveExcludes);
        set(27, nodeCount);
        set(28, coresPerNode);
        set(29, memoryMb);
        set(30, maxMinutes);
        set(31, jobTags);
        set(32, tags);
        set(33, notes);
        set(34, importRefId);
        set(35, deleted);
        set(36, created);
        set(37, updated);
    }
}
