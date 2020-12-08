/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables;


import com.google.gson.JsonElement;

import edu.utexas.tacc.tapis.apps.dao.JSONBToJsonElementBinding;
import edu.utexas.tacc.tapis.apps.gen.jooq.Keys;
import edu.utexas.tacc.tapis.apps.gen.jooq.TapisApp;
import edu.utexas.tacc.tapis.apps.gen.jooq.tables.records.AppUpdatesRecord;
import edu.utexas.tacc.tapis.apps.model.App.AppOperation;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
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
public class AppUpdates extends TableImpl<AppUpdatesRecord> {

    private static final long serialVersionUID = 300247810;

    /**
     * The reference instance of <code>tapis_app.app_updates</code>
     */
    public static final AppUpdates APP_UPDATES = new AppUpdates();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AppUpdatesRecord> getRecordType() {
        return AppUpdatesRecord.class;
    }

    /**
     * The column <code>tapis_app.app_updates.seq_id</code>. Application update request id
     */
    public final TableField<AppUpdatesRecord, Integer> SEQ_ID = createField(DSL.name("seq_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('app_updates_seq_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "Application update request id");

    /**
     * The column <code>tapis_app.app_updates.app_seq_id</code>. Sequence id of application being updated
     */
    public final TableField<AppUpdatesRecord, Integer> APP_SEQ_ID = createField(DSL.name("app_seq_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('app_updates_app_seq_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "Sequence id of application being updated");

    /**
     * The column <code>tapis_app.app_updates.user_name</code>. Name of user who requested the update
     */
    public final TableField<AppUpdatesRecord, String> USER_NAME = createField(DSL.name("user_name"), org.jooq.impl.SQLDataType.VARCHAR(60).nullable(false), this, "Name of user who requested the update");

    /**
     * The column <code>tapis_app.app_updates.user_tenant</code>. Tenant of user who requested the update
     */
    public final TableField<AppUpdatesRecord, String> USER_TENANT = createField(DSL.name("user_tenant"), org.jooq.impl.SQLDataType.VARCHAR(24).nullable(false), this, "Tenant of user who requested the update");

    /**
     * The column <code>tapis_app.app_updates.operation</code>. Type of update operation
     */
    public final TableField<AppUpdatesRecord, AppOperation> OPERATION = createField(DSL.name("operation"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false).asEnumDataType(edu.utexas.tacc.tapis.apps.gen.jooq.enums.OperationType.class), this, "Type of update operation", new org.jooq.impl.EnumConverter<edu.utexas.tacc.tapis.apps.gen.jooq.enums.OperationType, edu.utexas.tacc.tapis.apps.model.App.AppOperation>(edu.utexas.tacc.tapis.apps.gen.jooq.enums.OperationType.class, edu.utexas.tacc.tapis.apps.model.App.AppOperation.class));

    /**
     * The column <code>tapis_app.app_updates.upd_json</code>. JSON representing the update - with secrets scrubbed
     */
    public final TableField<AppUpdatesRecord, JsonElement> UPD_JSON = createField(DSL.name("upd_json"), org.jooq.impl.SQLDataType.JSONB.nullable(false), this, "JSON representing the update - with secrets scrubbed", new JSONBToJsonElementBinding());

    /**
     * The column <code>tapis_app.app_updates.upd_text</code>. Text data supplied by client - secrets should be scrubbed
     */
    public final TableField<AppUpdatesRecord, String> UPD_TEXT = createField(DSL.name("upd_text"), org.jooq.impl.SQLDataType.VARCHAR, this, "Text data supplied by client - secrets should be scrubbed");

    /**
     * The column <code>tapis_app.app_updates.created</code>. UTC time for when record was created
     */
    public final TableField<AppUpdatesRecord, LocalDateTime> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("timezone('utc'::text, now())", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "UTC time for when record was created");

    /**
     * Create a <code>tapis_app.app_updates</code> table reference
     */
    public AppUpdates() {
        this(DSL.name("app_updates"), null);
    }

    /**
     * Create an aliased <code>tapis_app.app_updates</code> table reference
     */
    public AppUpdates(String alias) {
        this(DSL.name(alias), APP_UPDATES);
    }

    /**
     * Create an aliased <code>tapis_app.app_updates</code> table reference
     */
    public AppUpdates(Name alias) {
        this(alias, APP_UPDATES);
    }

    private AppUpdates(Name alias, Table<AppUpdatesRecord> aliased) {
        this(alias, aliased, null);
    }

    private AppUpdates(Name alias, Table<AppUpdatesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> AppUpdates(Table<O> child, ForeignKey<O, AppUpdatesRecord> key) {
        super(child, key, APP_UPDATES);
    }

    @Override
    public Schema getSchema() {
        return TapisApp.TAPIS_APP;
    }

    @Override
    public Identity<AppUpdatesRecord, Integer> getIdentity() {
        return Keys.IDENTITY_APP_UPDATES;
    }

    @Override
    public UniqueKey<AppUpdatesRecord> getPrimaryKey() {
        return Keys.APP_UPDATES_PKEY;
    }

    @Override
    public List<UniqueKey<AppUpdatesRecord>> getKeys() {
        return Arrays.<UniqueKey<AppUpdatesRecord>>asList(Keys.APP_UPDATES_PKEY);
    }

    @Override
    public List<ForeignKey<AppUpdatesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AppUpdatesRecord, ?>>asList(Keys.APP_UPDATES__APP_UPDATES_APP_SEQ_ID_FKEY);
    }

    public Apps apps() {
        return new Apps(this, Keys.APP_UPDATES__APP_UPDATES_APP_SEQ_ID_FKEY);
    }

    @Override
    public AppUpdates as(String alias) {
        return new AppUpdates(DSL.name(alias), this);
    }

    @Override
    public AppUpdates as(Name alias) {
        return new AppUpdates(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUpdates rename(String name) {
        return new AppUpdates(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AppUpdates rename(Name name) {
        return new AppUpdates(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, Integer, String, String, AppOperation, JsonElement, String, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
