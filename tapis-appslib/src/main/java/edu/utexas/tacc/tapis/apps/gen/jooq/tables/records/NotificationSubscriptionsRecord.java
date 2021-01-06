/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables.records;


import edu.utexas.tacc.tapis.apps.gen.jooq.tables.NotificationSubscriptions;
import edu.utexas.tacc.tapis.apps.model.App.NotificationMechanism;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NotificationSubscriptionsRecord extends UpdatableRecordImpl<NotificationSubscriptionsRecord> implements Record6<Integer, Integer, String, NotificationMechanism, String, String> {

    private static final long serialVersionUID = 610673822;

    /**
     * Setter for <code>tapis_app.notification_subscriptions.seq_id</code>.
     */
    public void setSeqId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tapis_app.notification_subscriptions.seq_id</code>.
     */
    public Integer getSeqId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tapis_app.notification_subscriptions.app_seq_id</code>.
     */
    public void setAppSeqId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>tapis_app.notification_subscriptions.app_seq_id</code>.
     */
    public Integer getAppSeqId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>tapis_app.notification_subscriptions.filter</code>.
     */
    public void setFilter(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tapis_app.notification_subscriptions.filter</code>.
     */
    public String getFilter() {
        return (String) get(2);
    }

    /**
     * Setter for <code>tapis_app.notification_subscriptions.notification_mechanism</code>.
     */
    public void setNotificationMechanism(NotificationMechanism value) {
        set(3, value);
    }

    /**
     * Getter for <code>tapis_app.notification_subscriptions.notification_mechanism</code>.
     */
    public NotificationMechanism getNotificationMechanism() {
        return (NotificationMechanism) get(3);
    }

    /**
     * Setter for <code>tapis_app.notification_subscriptions.webhook_url</code>.
     */
    public void setWebhookUrl(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>tapis_app.notification_subscriptions.webhook_url</code>.
     */
    public String getWebhookUrl() {
        return (String) get(4);
    }

    /**
     * Setter for <code>tapis_app.notification_subscriptions.email_address</code>.
     */
    public void setEmailAddress(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>tapis_app.notification_subscriptions.email_address</code>.
     */
    public String getEmailAddress() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, String, NotificationMechanism, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, Integer, String, NotificationMechanism, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS.SEQ_ID;
    }

    @Override
    public Field<Integer> field2() {
        return NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS.APP_SEQ_ID;
    }

    @Override
    public Field<String> field3() {
        return NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS.FILTER;
    }

    @Override
    public Field<NotificationMechanism> field4() {
        return NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS.NOTIFICATION_MECHANISM;
    }

    @Override
    public Field<String> field5() {
        return NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS.WEBHOOK_URL;
    }

    @Override
    public Field<String> field6() {
        return NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS.EMAIL_ADDRESS;
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
        return getFilter();
    }

    @Override
    public NotificationMechanism component4() {
        return getNotificationMechanism();
    }

    @Override
    public String component5() {
        return getWebhookUrl();
    }

    @Override
    public String component6() {
        return getEmailAddress();
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
        return getFilter();
    }

    @Override
    public NotificationMechanism value4() {
        return getNotificationMechanism();
    }

    @Override
    public String value5() {
        return getWebhookUrl();
    }

    @Override
    public String value6() {
        return getEmailAddress();
    }

    @Override
    public NotificationSubscriptionsRecord value1(Integer value) {
        setSeqId(value);
        return this;
    }

    @Override
    public NotificationSubscriptionsRecord value2(Integer value) {
        setAppSeqId(value);
        return this;
    }

    @Override
    public NotificationSubscriptionsRecord value3(String value) {
        setFilter(value);
        return this;
    }

    @Override
    public NotificationSubscriptionsRecord value4(NotificationMechanism value) {
        setNotificationMechanism(value);
        return this;
    }

    @Override
    public NotificationSubscriptionsRecord value5(String value) {
        setWebhookUrl(value);
        return this;
    }

    @Override
    public NotificationSubscriptionsRecord value6(String value) {
        setEmailAddress(value);
        return this;
    }

    @Override
    public NotificationSubscriptionsRecord values(Integer value1, Integer value2, String value3, NotificationMechanism value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NotificationSubscriptionsRecord
     */
    public NotificationSubscriptionsRecord() {
        super(NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS);
    }

    /**
     * Create a detached, initialised NotificationSubscriptionsRecord
     */
    public NotificationSubscriptionsRecord(Integer seqId, Integer appSeqId, String filter, NotificationMechanism notificationMechanism, String webhookUrl, String emailAddress) {
        super(NotificationSubscriptions.NOTIFICATION_SUBSCRIPTIONS);

        set(0, seqId);
        set(1, appSeqId);
        set(2, filter);
        set(3, notificationMechanism);
        set(4, webhookUrl);
        set(5, emailAddress);
    }
}
