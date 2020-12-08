/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables;


import edu.utexas.tacc.tapis.apps.gen.jooq.Keys;
import edu.utexas.tacc.tapis.apps.gen.jooq.TapisApp;
import edu.utexas.tacc.tapis.apps.gen.jooq.tables.records.CommandArgsRecord;

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
public class CommandArgs extends TableImpl<CommandArgsRecord> {

    private static final long serialVersionUID = 921907824;

    /**
     * The reference instance of <code>tapis_app.command_args</code>
     */
    public static final CommandArgs COMMAND_ARGS = new CommandArgs();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CommandArgsRecord> getRecordType() {
        return CommandArgsRecord.class;
    }

    /**
     * The column <code>tapis_app.command_args.seq_id</code>. Arg sequence id
     */
    public final TableField<CommandArgsRecord, Integer> SEQ_ID = createField(DSL.name("seq_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('command_args_seq_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "Arg sequence id");

    /**
     * The column <code>tapis_app.command_args.app_seq_id</code>. Sequence id of application
     */
    public final TableField<CommandArgsRecord, Integer> APP_SEQ_ID = createField(DSL.name("app_seq_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('command_args_app_seq_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "Sequence id of application");

    /**
     * The column <code>tapis_app.command_args.arg_val</code>.
     */
    public final TableField<CommandArgsRecord, String> ARG_VAL = createField(DSL.name("arg_val"), org.jooq.impl.SQLDataType.VARCHAR(128).nullable(false).defaultValue(org.jooq.impl.DSL.field("''::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>tapis_app.command_args.meta_name</code>.
     */
    public final TableField<CommandArgsRecord, String> META_NAME = createField(DSL.name("meta_name"), org.jooq.impl.SQLDataType.VARCHAR(128).nullable(false).defaultValue(org.jooq.impl.DSL.field("''::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>tapis_app.command_args.meta_required</code>.
     */
    public final TableField<CommandArgsRecord, Boolean> META_REQUIRED = createField(DSL.name("meta_required"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false).defaultValue(org.jooq.impl.DSL.field("true", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>tapis_app.command_args.meta_kv</code>.
     */
    public final TableField<CommandArgsRecord, String[]> META_KV = createField(DSL.name("meta_kv"), org.jooq.impl.SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>tapis_app.command_args.created</code>.
     */
    public final TableField<CommandArgsRecord, LocalDateTime> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("timezone('utc'::text, now())", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>tapis_app.command_args.updated</code>.
     */
    public final TableField<CommandArgsRecord, LocalDateTime> UPDATED = createField(DSL.name("updated"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.field("timezone('utc'::text, now())", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>tapis_app.command_args</code> table reference
     */
    public CommandArgs() {
        this(DSL.name("command_args"), null);
    }

    /**
     * Create an aliased <code>tapis_app.command_args</code> table reference
     */
    public CommandArgs(String alias) {
        this(DSL.name(alias), COMMAND_ARGS);
    }

    /**
     * Create an aliased <code>tapis_app.command_args</code> table reference
     */
    public CommandArgs(Name alias) {
        this(alias, COMMAND_ARGS);
    }

    private CommandArgs(Name alias, Table<CommandArgsRecord> aliased) {
        this(alias, aliased, null);
    }

    private CommandArgs(Name alias, Table<CommandArgsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> CommandArgs(Table<O> child, ForeignKey<O, CommandArgsRecord> key) {
        super(child, key, COMMAND_ARGS);
    }

    @Override
    public Schema getSchema() {
        return TapisApp.TAPIS_APP;
    }

    @Override
    public Identity<CommandArgsRecord, Integer> getIdentity() {
        return Keys.IDENTITY_COMMAND_ARGS;
    }

    @Override
    public UniqueKey<CommandArgsRecord> getPrimaryKey() {
        return Keys.COMMAND_ARGS_PKEY;
    }

    @Override
    public List<UniqueKey<CommandArgsRecord>> getKeys() {
        return Arrays.<UniqueKey<CommandArgsRecord>>asList(Keys.COMMAND_ARGS_PKEY);
    }

    @Override
    public List<ForeignKey<CommandArgsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CommandArgsRecord, ?>>asList(Keys.COMMAND_ARGS__COMMAND_ARGS_APP_SEQ_ID_FKEY);
    }

    public Apps apps() {
        return new Apps(this, Keys.COMMAND_ARGS__COMMAND_ARGS_APP_SEQ_ID_FKEY);
    }

    @Override
    public CommandArgs as(String alias) {
        return new CommandArgs(DSL.name(alias), this);
    }

    @Override
    public CommandArgs as(Name alias) {
        return new CommandArgs(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CommandArgs rename(String name) {
        return new CommandArgs(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CommandArgs rename(Name name) {
        return new CommandArgs(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, Integer, String, String, Boolean, String[], LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
