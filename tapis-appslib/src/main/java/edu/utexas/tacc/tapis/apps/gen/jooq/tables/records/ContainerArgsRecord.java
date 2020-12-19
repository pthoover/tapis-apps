/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables.records;


import edu.utexas.tacc.tapis.apps.gen.jooq.tables.ContainerArgs;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ContainerArgsRecord extends UpdatableRecordImpl<ContainerArgsRecord> implements Record9<Integer, Integer, String, String, String, Boolean, String[], LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 2133803759;

    /**
     * Setter for <code>tapis_app.container_args.seq_id</code>. Arg sequence id
     */
    public void setSeqId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.seq_id</code>. Arg sequence id
     */
    public Integer getSeqId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tapis_app.container_args.app_seq_id</code>. Sequence id of application
     */
    public void setAppSeqId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.app_seq_id</code>. Sequence id of application
     */
    public Integer getAppSeqId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>tapis_app.container_args.arg_val</code>.
     */
    public void setArgVal(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.arg_val</code>.
     */
    public String getArgVal() {
        return (String) get(2);
    }

    /**
     * Setter for <code>tapis_app.container_args.meta_name</code>.
     */
    public void setMetaName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.meta_name</code>.
     */
    public String getMetaName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>tapis_app.container_args.meta_description</code>.
     */
    public void setMetaDescription(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.meta_description</code>.
     */
    public String getMetaDescription() {
        return (String) get(4);
    }

    /**
     * Setter for <code>tapis_app.container_args.meta_required</code>.
     */
    public void setMetaRequired(Boolean value) {
        set(5, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.meta_required</code>.
     */
    public Boolean getMetaRequired() {
        return (Boolean) get(5);
    }

    /**
     * Setter for <code>tapis_app.container_args.meta_kv</code>.
     */
    public void setMetaKv(String[] value) {
        set(6, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.meta_kv</code>.
     */
    public String[] getMetaKv() {
        return (String[]) get(6);
    }

    /**
     * Setter for <code>tapis_app.container_args.created</code>.
     */
    public void setCreated(LocalDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.created</code>.
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(7);
    }

    /**
     * Setter for <code>tapis_app.container_args.updated</code>.
     */
    public void setUpdated(LocalDateTime value) {
        set(8, value);
    }

    /**
     * Getter for <code>tapis_app.container_args.updated</code>.
     */
    public LocalDateTime getUpdated() {
        return (LocalDateTime) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, Integer, String, String, String, Boolean, String[], LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<Integer, Integer, String, String, String, Boolean, String[], LocalDateTime, LocalDateTime> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return ContainerArgs.CONTAINER_ARGS.SEQ_ID;
    }

    @Override
    public Field<Integer> field2() {
        return ContainerArgs.CONTAINER_ARGS.APP_SEQ_ID;
    }

    @Override
    public Field<String> field3() {
        return ContainerArgs.CONTAINER_ARGS.ARG_VAL;
    }

    @Override
    public Field<String> field4() {
        return ContainerArgs.CONTAINER_ARGS.META_NAME;
    }

    @Override
    public Field<String> field5() {
        return ContainerArgs.CONTAINER_ARGS.META_DESCRIPTION;
    }

    @Override
    public Field<Boolean> field6() {
        return ContainerArgs.CONTAINER_ARGS.META_REQUIRED;
    }

    @Override
    public Field<String[]> field7() {
        return ContainerArgs.CONTAINER_ARGS.META_KV;
    }

    @Override
    public Field<LocalDateTime> field8() {
        return ContainerArgs.CONTAINER_ARGS.CREATED;
    }

    @Override
    public Field<LocalDateTime> field9() {
        return ContainerArgs.CONTAINER_ARGS.UPDATED;
    }

    @Override
    public Integer component1() {
        return getSeqId();
    }

    @Override
    public Integer component2() {
        return getAppSeqId();
    }

    @Override
    public String component3() {
        return getArgVal();
    }

    @Override
    public String component4() {
        return getMetaName();
    }

    @Override
    public String component5() {
        return getMetaDescription();
    }

    @Override
    public Boolean component6() {
        return getMetaRequired();
    }

    @Override
    public String[] component7() {
        return getMetaKv();
    }

    @Override
    public LocalDateTime component8() {
        return getCreated();
    }

    @Override
    public LocalDateTime component9() {
        return getUpdated();
    }

    @Override
    public Integer value1() {
        return getSeqId();
    }

    @Override
    public Integer value2() {
        return getAppSeqId();
    }

    @Override
    public String value3() {
        return getArgVal();
    }

    @Override
    public String value4() {
        return getMetaName();
    }

    @Override
    public String value5() {
        return getMetaDescription();
    }

    @Override
    public Boolean value6() {
        return getMetaRequired();
    }

    @Override
    public String[] value7() {
        return getMetaKv();
    }

    @Override
    public LocalDateTime value8() {
        return getCreated();
    }

    @Override
    public LocalDateTime value9() {
        return getUpdated();
    }

    @Override
    public ContainerArgsRecord value1(Integer value) {
        setSeqId(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value2(Integer value) {
        setAppSeqId(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value3(String value) {
        setArgVal(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value4(String value) {
        setMetaName(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value5(String value) {
        setMetaDescription(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value6(Boolean value) {
        setMetaRequired(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value7(String[] value) {
        setMetaKv(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value8(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public ContainerArgsRecord value9(LocalDateTime value) {
        setUpdated(value);
        return this;
    }

    @Override
    public ContainerArgsRecord values(Integer value1, Integer value2, String value3, String value4, String value5, Boolean value6, String[] value7, LocalDateTime value8, LocalDateTime value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ContainerArgsRecord
     */
    public ContainerArgsRecord() {
        super(ContainerArgs.CONTAINER_ARGS);
    }

    /**
     * Create a detached, initialised ContainerArgsRecord
     */
    public ContainerArgsRecord(Integer seqId, Integer appSeqId, String argVal, String metaName, String metaDescription, Boolean metaRequired, String[] metaKv, LocalDateTime created, LocalDateTime updated) {
        super(ContainerArgs.CONTAINER_ARGS);

        set(0, seqId);
        set(1, appSeqId);
        set(2, argVal);
        set(3, metaName);
        set(4, metaDescription);
        set(5, metaRequired);
        set(6, metaKv);
        set(7, created);
        set(8, updated);
    }
}
