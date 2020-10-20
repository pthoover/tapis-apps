/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables;


import edu.utexas.tacc.tapis.apps.gen.jooq.Keys;
import edu.utexas.tacc.tapis.apps.gen.jooq.TapisApp;
import edu.utexas.tacc.tapis.apps.gen.jooq.tables.records.CapabilitiesRecord;
import edu.utexas.tacc.tapis.apps.model.Capability.Category;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
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
public class Capabilities extends TableImpl<CapabilitiesRecord> {

    private static final long serialVersionUID = 709293147;

    /**
     * The reference instance of <code>tapis_app.capabilities</code>
     */
    public static final Capabilities CAPABILITIES = new Capabilities();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CapabilitiesRecord> getRecordType() {
        return CapabilitiesRecord.class;
    }

    /**
     * The column <code>tapis_app.capabilities.id</code>. Capability id
     */
    public final TableField<CapabilitiesRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('capabilities_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "Capability id");

    /**
     * The column <code>tapis_app.capabilities.app_id</code>. Id of application supporting the capability
     */
    public final TableField<CapabilitiesRecord, Integer> APP_ID = createField(DSL.name("app_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('capabilities_app_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "Id of application supporting the capability");

    /**
     * The column <code>tapis_app.capabilities.category</code>. Category for grouping of capabilities
     */
    public final TableField<CapabilitiesRecord, Category> CATEGORY = createField(DSL.name("category"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false).asEnumDataType(edu.utexas.tacc.tapis.apps.gen.jooq.enums.CapabilityCategoryType.class), this, "Category for grouping of capabilities", new org.jooq.impl.EnumConverter<edu.utexas.tacc.tapis.apps.gen.jooq.enums.CapabilityCategoryType, edu.utexas.tacc.tapis.apps.model.Capability.Category>(edu.utexas.tacc.tapis.apps.gen.jooq.enums.CapabilityCategoryType.class, edu.utexas.tacc.tapis.apps.model.Capability.Category.class));

    /**
     * The column <code>tapis_app.capabilities.name</code>. Name of capability
     */
    public final TableField<CapabilitiesRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false).defaultValue(org.jooq.impl.DSL.field("''::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "Name of capability");

    /**
     * The column <code>tapis_app.capabilities.value</code>. Value for the capability
     */
    public final TableField<CapabilitiesRecord, String> VALUE = createField(DSL.name("value"), org.jooq.impl.SQLDataType.VARCHAR(256).nullable(false).defaultValue(org.jooq.impl.DSL.field("''::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "Value for the capability");

    /**
     * The column <code>tapis_app.capabilities.created</code>. UTC time for when record was created
     */
    public final TableField<CapabilitiesRecord, LocalDateTime> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("timezone('utc'::text, now())", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "UTC time for when record was created");

    /**
     * The column <code>tapis_app.capabilities.updated</code>. UTC time for when record was last updated
     */
    public final TableField<CapabilitiesRecord, LocalDateTime> UPDATED = createField(DSL.name("updated"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("timezone('utc'::text, now())", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "UTC time for when record was last updated");

    /**
     * Create a <code>tapis_app.capabilities</code> table reference
     */
    public Capabilities() {
        this(DSL.name("capabilities"), null);
    }

    /**
     * Create an aliased <code>tapis_app.capabilities</code> table reference
     */
    public Capabilities(String alias) {
        this(DSL.name(alias), CAPABILITIES);
    }

    /**
     * Create an aliased <code>tapis_app.capabilities</code> table reference
     */
    public Capabilities(Name alias) {
        this(alias, CAPABILITIES);
    }

    private Capabilities(Name alias, Table<CapabilitiesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Capabilities(Name alias, Table<CapabilitiesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Capabilities(Table<O> child, ForeignKey<O, CapabilitiesRecord> key) {
        super(child, key, CAPABILITIES);
    }

    @Override
    public Schema getSchema() {
        return TapisApp.TAPIS_APP;
    }

    @Override
    public Identity<CapabilitiesRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CAPABILITIES;
    }

    @Override
    public UniqueKey<CapabilitiesRecord> getPrimaryKey() {
        return Keys.CAPABILITIES_PKEY;
    }

    @Override
    public List<UniqueKey<CapabilitiesRecord>> getKeys() {
        return Arrays.<UniqueKey<CapabilitiesRecord>>asList(Keys.CAPABILITIES_PKEY, Keys.CAPABILITIES_APP_ID_CATEGORY_NAME_KEY);
    }

    @Override
    public List<ForeignKey<CapabilitiesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CapabilitiesRecord, ?>>asList(Keys.CAPABILITIES__CAPABILITIES_APP_ID_FKEY);
    }

    public Apps apps() {
        return new Apps(this, Keys.CAPABILITIES__CAPABILITIES_APP_ID_FKEY);
    }

    @Override
    public Capabilities as(String alias) {
        return new Capabilities(DSL.name(alias), this);
    }

    @Override
    public Capabilities as(Name alias) {
        return new Capabilities(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Capabilities rename(String name) {
        return new Capabilities(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Capabilities rename(Name name) {
        return new Capabilities(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, Category, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
