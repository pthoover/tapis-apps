/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables;


import com.google.gson.JsonElement;

import edu.utexas.tacc.tapis.apps.dao.JSONBToJsonElementBinding;
import edu.utexas.tacc.tapis.apps.gen.jooq.Indexes;
import edu.utexas.tacc.tapis.apps.gen.jooq.Keys;
import edu.utexas.tacc.tapis.apps.gen.jooq.TapisApp;
import edu.utexas.tacc.tapis.apps.gen.jooq.tables.records.AppsRecord;
import edu.utexas.tacc.tapis.apps.model.App.AppType;
import edu.utexas.tacc.tapis.apps.model.App.ContainerRuntime;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Apps extends TableImpl<AppsRecord> {

    private static final long serialVersionUID = 1113043058;

    /**
     * The reference instance of <code>tapis_app.apps</code>
     */
    public static final Apps APPS = new Apps();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AppsRecord> getRecordType() {
        return AppsRecord.class;
    }

    /**
     * The column <code>tapis_app.apps.seq_id</code>. Application sequence id
     */
    public final TableField<AppsRecord, Integer> SEQ_ID = createField(DSL.name("seq_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('apps_seq_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "Application sequence id");

    /**
     * The column <code>tapis_app.apps.tenant</code>. Tenant name associated with the application
     */
    public final TableField<AppsRecord, String> TENANT = createField(DSL.name("tenant"), org.jooq.impl.SQLDataType.VARCHAR(24).nullable(false), this, "Tenant name associated with the application");

    /**
     * The column <code>tapis_app.apps.id</code>. Unique name for the application
     */
    public final TableField<AppsRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.VARCHAR(80).nullable(false), this, "Unique name for the application");

    /**
     * The column <code>tapis_app.apps.version</code>. Application version
     */
    public final TableField<AppsRecord, String> VERSION = createField(DSL.name("version"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "Application version");

    /**
     * The column <code>tapis_app.apps.description</code>. Application description
     */
    public final TableField<AppsRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(2048), this, "Application description");

    /**
     * The column <code>tapis_app.apps.app_type</code>. Type of application
     */
    public final TableField<AppsRecord, AppType> APP_TYPE = createField(DSL.name("app_type"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false).asEnumDataType(edu.utexas.tacc.tapis.apps.gen.jooq.enums.AppTypeType.class), this, "Type of application", new org.jooq.impl.EnumConverter<edu.utexas.tacc.tapis.apps.gen.jooq.enums.AppTypeType, edu.utexas.tacc.tapis.apps.model.App.AppType>(edu.utexas.tacc.tapis.apps.gen.jooq.enums.AppTypeType.class, edu.utexas.tacc.tapis.apps.model.App.AppType.class));

    /**
     * The column <code>tapis_app.apps.owner</code>. User name of application owner
     */
    public final TableField<AppsRecord, String> OWNER = createField(DSL.name("owner"), org.jooq.impl.SQLDataType.VARCHAR(60).nullable(false), this, "User name of application owner");

    /**
     * The column <code>tapis_app.apps.enabled</code>. Indicates if application is currently active and available for use
     */
    public final TableField<AppsRecord, Boolean> ENABLED = createField(DSL.name("enabled"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("true", org.jooq.impl.SQLDataType.BOOLEAN)), this, "Indicates if application is currently active and available for use");

    /**
     * The column <code>tapis_app.apps.containerized</code>.
     */
    public final TableField<AppsRecord, Boolean> CONTAINERIZED = createField(DSL.name("containerized"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("true", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps.container_runtime</code>.
     */
    public final TableField<AppsRecord, ContainerRuntime> CONTAINER_RUNTIME = createField(DSL.name("container_runtime"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false).asEnumDataType(edu.utexas.tacc.tapis.apps.gen.jooq.enums.ContainerRuntimeType.class), this, "", new org.jooq.impl.EnumConverter<edu.utexas.tacc.tapis.apps.gen.jooq.enums.ContainerRuntimeType, edu.utexas.tacc.tapis.apps.model.App.ContainerRuntime>(edu.utexas.tacc.tapis.apps.gen.jooq.enums.ContainerRuntimeType.class, edu.utexas.tacc.tapis.apps.model.App.ContainerRuntime.class));

    /**
     * The column <code>tapis_app.apps.container_image</code>.
     */
    public final TableField<AppsRecord, String> CONTAINER_IMAGE = createField(DSL.name("container_image"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>tapis_app.apps.command</code>.
     */
    public final TableField<AppsRecord, String> COMMAND = createField(DSL.name("command"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>tapis_app.apps.exec_codes</code>.
     */
    public final TableField<AppsRecord, String[]> EXEC_CODES = createField(DSL.name("exec_codes"), org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.apps.dynamic_exec_system</code>.
     */
    public final TableField<AppsRecord, Boolean> DYNAMIC_EXEC_SYSTEM = createField(DSL.name("dynamic_exec_system"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps.exec_system_constraints</code>.
     */
    public final TableField<AppsRecord, String[]> EXEC_SYSTEM_CONSTRAINTS = createField(DSL.name("exec_system_constraints"), org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.apps.exec_system_id</code>.
     */
    public final TableField<AppsRecord, String> EXEC_SYSTEM_ID = createField(DSL.name("exec_system_id"), org.jooq.impl.SQLDataType.VARCHAR(80).nullable(false), this, "");

    /**
     * The column <code>tapis_app.apps.exec_system_exec_dir</code>.
     */
    public final TableField<AppsRecord, String> EXEC_SYSTEM_EXEC_DIR = createField(DSL.name("exec_system_exec_dir"), org.jooq.impl.SQLDataType.VARCHAR(4096), this, "");

    /**
     * The column <code>tapis_app.apps.exec_system_input_dir</code>.
     */
    public final TableField<AppsRecord, String> EXEC_SYSTEM_INPUT_DIR = createField(DSL.name("exec_system_input_dir"), org.jooq.impl.SQLDataType.VARCHAR(4096), this, "");

    /**
     * The column <code>tapis_app.apps.exec_system_output_dir</code>.
     */
    public final TableField<AppsRecord, String> EXEC_SYSTEM_OUTPUT_DIR = createField(DSL.name("exec_system_output_dir"), org.jooq.impl.SQLDataType.VARCHAR(4096), this, "");

    /**
     * The column <code>tapis_app.apps.exec_system_logical_queue</code>.
     */
    public final TableField<AppsRecord, String> EXEC_SYSTEM_LOGICAL_QUEUE = createField(DSL.name("exec_system_logical_queue"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>tapis_app.apps.archive_system_id</code>.
     */
    public final TableField<AppsRecord, String> ARCHIVE_SYSTEM_ID = createField(DSL.name("archive_system_id"), org.jooq.impl.SQLDataType.VARCHAR(80), this, "");

    /**
     * The column <code>tapis_app.apps.archive_system_dir</code>.
     */
    public final TableField<AppsRecord, String> ARCHIVE_SYSTEM_DIR = createField(DSL.name("archive_system_dir"), org.jooq.impl.SQLDataType.VARCHAR(4096), this, "");

    /**
     * The column <code>tapis_app.apps.archive_on_app_error</code>.
     */
    public final TableField<AppsRecord, Boolean> ARCHIVE_ON_APP_ERROR = createField(DSL.name("archive_on_app_error"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("true", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps.use_dtn_if_defined</code>.
     */
    public final TableField<AppsRecord, Boolean> USE_DTN_IF_DEFINED = createField(DSL.name("use_dtn_if_defined"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("true", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps.job_description</code>.
     */
    public final TableField<AppsRecord, String> JOB_DESCRIPTION = createField(DSL.name("job_description"), org.jooq.impl.SQLDataType.VARCHAR(2048), this, "");

    /**
     * The column <code>tapis_app.apps.max_jobs</code>.
     */
    public final TableField<AppsRecord, Integer> MAX_JOBS = createField(DSL.name("max_jobs"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("'-1'::integer", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps.max_jobs_per_user</code>.
     */
    public final TableField<AppsRecord, Integer> MAX_JOBS_PER_USER = createField(DSL.name("max_jobs_per_user"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("'-1'::integer", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps.node_count</code>.
     */
    public final TableField<AppsRecord, Integer> NODE_COUNT = createField(DSL.name("node_count"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("'-1'::integer", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps.cores_per_node</code>.
     */
    public final TableField<AppsRecord, Integer> CORES_PER_NODE = createField(DSL.name("cores_per_node"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("'-1'::integer", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps.memory_mb</code>.
     */
    public final TableField<AppsRecord, Integer> MEMORY_MB = createField(DSL.name("memory_mb"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("'-1'::integer", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps.max_minutes</code>.
     */
    public final TableField<AppsRecord, Integer> MAX_MINUTES = createField(DSL.name("max_minutes"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("'-1'::integer", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps.archive_includes</code>.
     */
    public final TableField<AppsRecord, String[]> ARCHIVE_INCLUDES = createField(DSL.name("archive_includes"), org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.apps.archive_excludes</code>.
     */
    public final TableField<AppsRecord, String[]> ARCHIVE_EXCLUDES = createField(DSL.name("archive_excludes"), org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.apps.tags</code>. Tags for user supplied key:value pairs
     */
    public final TableField<AppsRecord, String[]> TAGS = createField(DSL.name("tags"), org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "Tags for user supplied key:value pairs");

    /**
     * The column <code>tapis_app.apps.notes</code>. Notes for general information stored as JSON
     */
    public final TableField<AppsRecord, JsonElement> NOTES = createField(DSL.name("notes"), org.jooq.impl.SQLDataType.JSONB.nullable(false), this, "Notes for general information stored as JSON", new JSONBToJsonElementBinding());

    /**
     * The column <code>tapis_app.apps.import_ref_id</code>. Optional reference ID for applications created via import
     */
    public final TableField<AppsRecord, String> IMPORT_REF_ID = createField(DSL.name("import_ref_id"), org.jooq.impl.SQLDataType.VARCHAR(80), this, "Optional reference ID for applications created via import");

    /**
     * The column <code>tapis_app.apps.deleted</code>. Indicates if application has been soft deleted
     */
    public final TableField<AppsRecord, Boolean> DELETED = createField(DSL.name("deleted"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "Indicates if application has been soft deleted");

    /**
     * The column <code>tapis_app.apps.created</code>. UTC time for when record was created
     */
    public final TableField<AppsRecord, LocalDateTime> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("timezone('utc'::text, now())", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "UTC time for when record was created");

    /**
     * The column <code>tapis_app.apps.updated</code>. UTC time for when record was last updated
     */
    public final TableField<AppsRecord, LocalDateTime> UPDATED = createField(DSL.name("updated"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("timezone('utc'::text, now())", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "UTC time for when record was last updated");

    /**
     * Create a <code>tapis_app.apps</code> table reference
     */
    public Apps() {
        this(DSL.name("apps"), null);
    }

    /**
     * Create an aliased <code>tapis_app.apps</code> table reference
     */
    public Apps(String alias) {
        this(DSL.name(alias), APPS);
    }

    /**
     * Create an aliased <code>tapis_app.apps</code> table reference
     */
    public Apps(Name alias) {
        this(alias, APPS);
    }

    private Apps(Name alias, Table<AppsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Apps(Name alias, Table<AppsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Apps(Table<O> child, ForeignKey<O, AppsRecord> key) {
        super(child, key, APPS);
    }

    @Override
    public Schema getSchema() {
        return TapisApp.TAPIS_APP;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.APP_TENANT_NAME_IDX);
    }

    @Override
    public Identity<AppsRecord, Integer> getIdentity() {
        return Keys.IDENTITY_APPS;
    }

    @Override
    public UniqueKey<AppsRecord> getPrimaryKey() {
        return Keys.APPS_PKEY;
    }

    @Override
    public List<UniqueKey<AppsRecord>> getKeys() {
        return Arrays.<UniqueKey<AppsRecord>>asList(Keys.APPS_PKEY, Keys.APPS_TENANT_ID_VERSION_KEY);
    }

    @Override
    public Apps as(String alias) {
        return new Apps(DSL.name(alias), this);
    }

    @Override
    public Apps as(Name alias) {
        return new Apps(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Apps rename(String name) {
        return new Apps(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Apps rename(Name name) {
        return new Apps(name, null);
    }
}
