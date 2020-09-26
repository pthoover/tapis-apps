/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables.records;


import edu.utexas.tacc.tapis.apps.gen.jooq.tables.Capabilities;
import edu.utexas.tacc.tapis.apps.model.Capability.Category;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CapabilitiesRecord extends UpdatableRecordImpl<CapabilitiesRecord> implements Record7<Integer, Integer, Category, String, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = -633107016;

    /**
     * Setter for <code>tapis_app.capabilities.id</code>. Capability id
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tapis_app.capabilities.id</code>. Capability id
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tapis_app.capabilities.app_id</code>. Id of app supporting the capability
     */
    public void setAppId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>tapis_app.capabilities.app_id</code>. Id of app supporting the capability
     */
    public Integer getAppId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>tapis_app.capabilities.category</code>. Category for grouping of capabilities
     */
    public void setCategory(Category value) {
        set(2, value);
    }

    /**
     * Getter for <code>tapis_app.capabilities.category</code>. Category for grouping of capabilities
     */
    public Category getCategory() {
        return (Category) get(2);
    }

    /**
     * Setter for <code>tapis_app.capabilities.name</code>. Name of capability
     */
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>tapis_app.capabilities.name</code>. Name of capability
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>tapis_app.capabilities.value</code>. Value for the capability
     */
    public void setValue(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>tapis_app.capabilities.value</code>. Value for the capability
     */
    public String getValue() {
        return (String) get(4);
    }

    /**
     * Setter for <code>tapis_app.capabilities.created</code>. UTC time for when record was created
     */
    public void setCreated(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>tapis_app.capabilities.created</code>. UTC time for when record was created
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>tapis_app.capabilities.updated</code>. UTC time for when record was last updated
     */
    public void setUpdated(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>tapis_app.capabilities.updated</code>. UTC time for when record was last updated
     */
    public LocalDateTime getUpdated() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, Category, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<Integer, Integer, Category, String, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Capabilities.CAPABILITIES.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Capabilities.CAPABILITIES.APP_ID;
    }

    @Override
    public Field<Category> field3() {
        return Capabilities.CAPABILITIES.CATEGORY;
    }

    @Override
    public Field<String> field4() {
        return Capabilities.CAPABILITIES.NAME;
    }

    @Override
    public Field<String> field5() {
        return Capabilities.CAPABILITIES.VALUE;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Capabilities.CAPABILITIES.CREATED;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return Capabilities.CAPABILITIES.UPDATED;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getAppId();
    }

    @Override
    public Category component3() {
        return getCategory();
    }

    @Override
    public String component4() {
        return getName();
    }

    @Override
    public String component5() {
        return getValue();
    }

    @Override
    public LocalDateTime component6() {
        return getCreated();
    }

    @Override
    public LocalDateTime component7() {
        return getUpdated();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getAppId();
    }

    @Override
    public Category value3() {
        return getCategory();
    }

    @Override
    public String value4() {
        return getName();
    }

    @Override
    public String value5() {
        return getValue();
    }

    @Override
    public LocalDateTime value6() {
        return getCreated();
    }

    @Override
    public LocalDateTime value7() {
        return getUpdated();
    }

    @Override
    public CapabilitiesRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public CapabilitiesRecord value2(Integer value) {
        setAppId(value);
        return this;
    }

    @Override
    public CapabilitiesRecord value3(Category value) {
        setCategory(value);
        return this;
    }

    @Override
    public CapabilitiesRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public CapabilitiesRecord value5(String value) {
        setValue(value);
        return this;
    }

    @Override
    public CapabilitiesRecord value6(LocalDateTime value) {
        setCreated(value);
        return this;
    }

    @Override
    public CapabilitiesRecord value7(LocalDateTime value) {
        setUpdated(value);
        return this;
    }

    @Override
    public CapabilitiesRecord values(Integer value1, Integer value2, Category value3, String value4, String value5, LocalDateTime value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CapabilitiesRecord
     */
    public CapabilitiesRecord() {
        super(Capabilities.CAPABILITIES);
    }

    /**
     * Create a detached, initialised CapabilitiesRecord
     */
    public CapabilitiesRecord(Integer id, Integer appId, Category category, String name, String value, LocalDateTime created, LocalDateTime updated) {
        super(Capabilities.CAPABILITIES);

        set(0, id);
        set(1, appId);
        set(2, category);
        set(3, name);
        set(4, value);
        set(5, created);
        set(6, updated);
    }
}
