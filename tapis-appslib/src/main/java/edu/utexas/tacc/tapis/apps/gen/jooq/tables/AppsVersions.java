/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables;


import com.google.gson.JsonElement;

import edu.utexas.tacc.tapis.apps.dao.JSONBToJsonElementBinding;
import edu.utexas.tacc.tapis.apps.gen.jooq.Indexes;
import edu.utexas.tacc.tapis.apps.gen.jooq.Keys;
import edu.utexas.tacc.tapis.apps.gen.jooq.TapisApp;
import edu.utexas.tacc.tapis.apps.gen.jooq.tables.records.AppsVersionsRecord;
import edu.utexas.tacc.tapis.apps.model.App.JobType;
import edu.utexas.tacc.tapis.apps.model.App.Runtime;

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
import org.jooq.impl.EnumConverter;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppsVersions extends TableImpl<AppsVersionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tapis_app.apps_versions</code>
     */
    public static final AppsVersions APPS_VERSIONS = new AppsVersions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AppsVersionsRecord> getRecordType() {
        return AppsVersionsRecord.class;
    }

    /**
     * The column <code>tapis_app.apps_versions.seq_id</code>. Sequence id for
     * specific version of application
     */
    public final TableField<AppsVersionsRecord, Integer> SEQ_ID = createField(DSL.name("seq_id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "Sequence id for specific version of application");

    /**
     * The column <code>tapis_app.apps_versions.app_seq_id</code>. Sequence id
     * of application
     */
    public final TableField<AppsVersionsRecord, Integer> APP_SEQ_ID = createField(DSL.name("app_seq_id"), SQLDataType.INTEGER, this, "Sequence id of application");

    /**
     * The column <code>tapis_app.apps_versions.tenant</code>.
     */
    public final TableField<AppsVersionsRecord, String> TENANT = createField(DSL.name("tenant"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>tapis_app.apps_versions.id</code>.
     */
    public final TableField<AppsVersionsRecord, String> ID = createField(DSL.name("id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>tapis_app.apps_versions.version</code>. Application
     * version
     */
    public final TableField<AppsVersionsRecord, String> VERSION = createField(DSL.name("version"), SQLDataType.CLOB.nullable(false), this, "Application version");

    /**
     * The column <code>tapis_app.apps_versions.description</code>. Application
     * description
     */
    public final TableField<AppsVersionsRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "Application description");

    /**
     * The column <code>tapis_app.apps_versions.runtime</code>.
     */
    public final TableField<AppsVersionsRecord, Runtime> RUNTIME = createField(DSL.name("runtime"), SQLDataType.CLOB.nullable(false), this, "", new EnumConverter<String, Runtime>(String.class, Runtime.class));

    /**
     * The column <code>tapis_app.apps_versions.runtime_version</code>.
     */
    public final TableField<AppsVersionsRecord, String> RUNTIME_VERSION = createField(DSL.name("runtime_version"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.runtime_options</code>.
     */
    public final TableField<AppsVersionsRecord, String[]> RUNTIME_OPTIONS = createField(DSL.name("runtime_options"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.apps_versions.container_image</code>.
     */
    public final TableField<AppsVersionsRecord, String> CONTAINER_IMAGE = createField(DSL.name("container_image"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.job_type</code>.
     */
    public final TableField<AppsVersionsRecord, JobType> JOB_TYPE = createField(DSL.name("job_type"), SQLDataType.CLOB, this, "", new EnumConverter<String, JobType>(String.class, JobType.class));

    /**
     * The column <code>tapis_app.apps_versions.max_jobs</code>.
     */
    public final TableField<AppsVersionsRecord, Integer> MAX_JOBS = createField(DSL.name("max_jobs"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("'-1'::integer", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.max_jobs_per_user</code>.
     */
    public final TableField<AppsVersionsRecord, Integer> MAX_JOBS_PER_USER = createField(DSL.name("max_jobs_per_user"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("'-1'::integer", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.strict_file_inputs</code>.
     */
    public final TableField<AppsVersionsRecord, Boolean> STRICT_FILE_INPUTS = createField(DSL.name("strict_file_inputs"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.job_description</code>.
     */
    public final TableField<AppsVersionsRecord, String> JOB_DESCRIPTION = createField(DSL.name("job_description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.dynamic_exec_system</code>.
     */
    public final TableField<AppsVersionsRecord, Boolean> DYNAMIC_EXEC_SYSTEM = createField(DSL.name("dynamic_exec_system"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.exec_system_constraints</code>.
     */
    public final TableField<AppsVersionsRecord, String[]> EXEC_SYSTEM_CONSTRAINTS = createField(DSL.name("exec_system_constraints"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.apps_versions.exec_system_id</code>.
     */
    public final TableField<AppsVersionsRecord, String> EXEC_SYSTEM_ID = createField(DSL.name("exec_system_id"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.exec_system_exec_dir</code>.
     */
    public final TableField<AppsVersionsRecord, String> EXEC_SYSTEM_EXEC_DIR = createField(DSL.name("exec_system_exec_dir"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.exec_system_input_dir</code>.
     */
    public final TableField<AppsVersionsRecord, String> EXEC_SYSTEM_INPUT_DIR = createField(DSL.name("exec_system_input_dir"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.exec_system_output_dir</code>.
     */
    public final TableField<AppsVersionsRecord, String> EXEC_SYSTEM_OUTPUT_DIR = createField(DSL.name("exec_system_output_dir"), SQLDataType.CLOB, this, "");

    /**
     * The column
     * <code>tapis_app.apps_versions.exec_system_logical_queue</code>.
     */
    public final TableField<AppsVersionsRecord, String> EXEC_SYSTEM_LOGICAL_QUEUE = createField(DSL.name("exec_system_logical_queue"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.archive_system_id</code>.
     */
    public final TableField<AppsVersionsRecord, String> ARCHIVE_SYSTEM_ID = createField(DSL.name("archive_system_id"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.archive_system_dir</code>.
     */
    public final TableField<AppsVersionsRecord, String> ARCHIVE_SYSTEM_DIR = createField(DSL.name("archive_system_dir"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.archive_on_app_error</code>.
     */
    public final TableField<AppsVersionsRecord, Boolean> ARCHIVE_ON_APP_ERROR = createField(DSL.name("archive_on_app_error"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.is_mpi</code>.
     */
    public final TableField<AppsVersionsRecord, Boolean> IS_MPI = createField(DSL.name("is_mpi"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.mpi_cmd</code>.
     */
    public final TableField<AppsVersionsRecord, String> MPI_CMD = createField(DSL.name("mpi_cmd"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.cmd_prefix</code>.
     */
    public final TableField<AppsVersionsRecord, String> CMD_PREFIX = createField(DSL.name("cmd_prefix"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.parameter_set</code>.
     */
    public final TableField<AppsVersionsRecord, JsonElement> PARAMETER_SET = createField(DSL.name("parameter_set"), SQLDataType.JSONB.nullable(false), this, "", new JSONBToJsonElementBinding());

    /**
     * The column <code>tapis_app.apps_versions.file_inputs</code>.
     */
    public final TableField<AppsVersionsRecord, JsonElement> FILE_INPUTS = createField(DSL.name("file_inputs"), SQLDataType.JSONB.nullable(false), this, "", new JSONBToJsonElementBinding());

    /**
     * The column <code>tapis_app.apps_versions.file_input_arrays</code>.
     */
    public final TableField<AppsVersionsRecord, JsonElement> FILE_INPUT_ARRAYS = createField(DSL.name("file_input_arrays"), SQLDataType.JSONB.nullable(false), this, "", new JSONBToJsonElementBinding());

    /**
     * The column <code>tapis_app.apps_versions.node_count</code>.
     */
    public final TableField<AppsVersionsRecord, Integer> NODE_COUNT = createField(DSL.name("node_count"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("1", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.cores_per_node</code>.
     */
    public final TableField<AppsVersionsRecord, Integer> CORES_PER_NODE = createField(DSL.name("cores_per_node"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("1", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.memory_mb</code>.
     */
    public final TableField<AppsVersionsRecord, Integer> MEMORY_MB = createField(DSL.name("memory_mb"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("100", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.max_minutes</code>.
     */
    public final TableField<AppsVersionsRecord, Integer> MAX_MINUTES = createField(DSL.name("max_minutes"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("10", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.subscriptions</code>.
     */
    public final TableField<AppsVersionsRecord, JsonElement> SUBSCRIPTIONS = createField(DSL.name("subscriptions"), SQLDataType.JSONB.nullable(false), this, "", new JSONBToJsonElementBinding());

    /**
     * The column <code>tapis_app.apps_versions.job_tags</code>.
     */
    public final TableField<AppsVersionsRecord, String[]> JOB_TAGS = createField(DSL.name("job_tags"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.apps_versions.tags</code>. Tags for user
     * supplied key:value pairs
     */
    public final TableField<AppsVersionsRecord, String[]> TAGS = createField(DSL.name("tags"), SQLDataType.CLOB.getArrayDataType(), this, "Tags for user supplied key:value pairs");

    /**
     * The column <code>tapis_app.apps_versions.notes</code>. Notes for general
     * information stored as JSON
     */
    public final TableField<AppsVersionsRecord, JsonElement> NOTES = createField(DSL.name("notes"), SQLDataType.JSONB.nullable(false), this, "Notes for general information stored as JSON", new JSONBToJsonElementBinding());

    /**
     * The column <code>tapis_app.apps_versions.uuid</code>.
     */
    public final TableField<AppsVersionsRecord, java.util.UUID> UUID = createField(DSL.name("uuid"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>tapis_app.apps_versions.created</code>. UTC time for
     * when record was created
     */
    public final TableField<AppsVersionsRecord, LocalDateTime> CREATED = createField(DSL.name("created"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("timezone('utc'::text, now())", SQLDataType.LOCALDATETIME)), this, "UTC time for when record was created");

    /**
     * The column <code>tapis_app.apps_versions.updated</code>. UTC time for
     * when record was last updated
     */
    public final TableField<AppsVersionsRecord, LocalDateTime> UPDATED = createField(DSL.name("updated"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("timezone('utc'::text, now())", SQLDataType.LOCALDATETIME)), this, "UTC time for when record was last updated");

    /**
     * The column <code>tapis_app.apps_versions.locked</code>.
     */
    public final TableField<AppsVersionsRecord, Boolean> LOCKED = createField(DSL.name("locked"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.apps_versions.enabled</code>.
     */
    public final TableField<AppsVersionsRecord, Boolean> ENABLED = createField(DSL.name("enabled"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");
     * The column <code>tapis_app.apps_versions.dtn_system_input_dir</code>.
     */
    public final TableField<AppsVersionsRecord, String> DTN_SYSTEM_INPUT_DIR = createField(DSL.name("dtn_system_input_dir"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>tapis_app.apps_versions.dtn_system_output_dir</code>.
     */
    public final TableField<AppsVersionsRecord, String> DTN_SYSTEM_OUTPUT_DIR = createField(DSL.name("dtn_system_output_dir"), SQLDataType.CLOB, this, "");

    private AppsVersions(Name alias, Table<AppsVersionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private AppsVersions(Name alias, Table<AppsVersionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>tapis_app.apps_versions</code> table reference
     */
    public AppsVersions(String alias) {
        this(DSL.name(alias), APPS_VERSIONS);
    }

    /**
     * Create an aliased <code>tapis_app.apps_versions</code> table reference
     */
    public AppsVersions(Name alias) {
        this(alias, APPS_VERSIONS);
    }

    /**
     * Create a <code>tapis_app.apps_versions</code> table reference
     */
    public AppsVersions() {
        this(DSL.name("apps_versions"), null);
    }

    public <O extends Record> AppsVersions(Table<O> child, ForeignKey<O, AppsVersionsRecord> key) {
        super(child, key, APPS_VERSIONS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : TapisApp.TAPIS_APP;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.APP_VERSION_SEQID_IDX);
    }

    @Override
    public Identity<AppsVersionsRecord, Integer> getIdentity() {
        return (Identity<AppsVersionsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<AppsVersionsRecord> getPrimaryKey() {
        return Keys.APPS_VERSIONS_PKEY;
    }

    @Override
    public List<UniqueKey<AppsVersionsRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.APPS_VERSIONS_APP_SEQ_ID_VERSION_KEY);
    }

    @Override
    public List<ForeignKey<AppsVersionsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.APPS_VERSIONS__APPS_VERSIONS_APP_SEQ_ID_FKEY);
    }

    private transient Apps _apps;

    /**
     * Get the implicit join path to the <code>tapis_app.apps</code> table.
     */
    public Apps apps() {
        if (_apps == null)
            _apps = new Apps(this, Keys.APPS_VERSIONS__APPS_VERSIONS_APP_SEQ_ID_FKEY);

        return _apps;
    }

    @Override
    public AppsVersions as(String alias) {
        return new AppsVersions(DSL.name(alias), this);
    }

    @Override
    public AppsVersions as(Name alias) {
        return new AppsVersions(alias, this);
    }

    @Override
    public AppsVersions as(Table<?> alias) {
        return new AppsVersions(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public AppsVersions rename(String name) {
        return new AppsVersions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppsVersions rename(Name name) {
        return new AppsVersions(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppsVersions rename(Table<?> name) {
        return new AppsVersions(name.getQualifiedName(), null);
    }
}
