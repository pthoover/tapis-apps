/*
 * This file is generated by jOOQ.
 */
package edu.utexas.tacc.tapis.apps.gen.jooq.tables.records;


import com.google.gson.JsonElement;

import edu.utexas.tacc.tapis.apps.gen.jooq.tables.AppsVersions;
import edu.utexas.tacc.tapis.apps.model.App.JobType;
import edu.utexas.tacc.tapis.apps.model.App.Runtime;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AppsVersionsRecord extends UpdatableRecordImpl<AppsVersionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>tapis_app.apps_versions.seq_id</code>. Sequence id for
     * specific version of application
     */
    public void setSeqId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.seq_id</code>. Sequence id for
     * specific version of application
     */
    public Integer getSeqId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.app_seq_id</code>. Sequence id
     * of application
     */
    public void setAppSeqId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.app_seq_id</code>. Sequence id
     * of application
     */
    public Integer getAppSeqId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.tenant</code>.
     */
    public void setTenant(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.tenant</code>.
     */
    public String getTenant() {
        return (String) get(2);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.id</code>.
     */
    public void setId(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.id</code>.
     */
    public String getId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.version</code>. Application
     * version
     */
    public void setVersion(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.version</code>. Application
     * version
     */
    public String getVersion() {
        return (String) get(4);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.description</code>. Application
     * description
     */
    public void setDescription(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.description</code>. Application
     * description
     */
    public String getDescription() {
        return (String) get(5);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.runtime</code>.
     */
    public void setRuntime(Runtime value) {
        set(6, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.runtime</code>.
     */
    public Runtime getRuntime() {
        return (Runtime) get(6);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.runtime_version</code>.
     */
    public void setRuntimeVersion(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.runtime_version</code>.
     */
    public String getRuntimeVersion() {
        return (String) get(7);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.runtime_options</code>.
     */
    public void setRuntimeOptions(String[] value) {
        set(8, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.runtime_options</code>.
     */
    public String[] getRuntimeOptions() {
        return (String[]) get(8);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.container_image</code>.
     */
    public void setContainerImage(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.container_image</code>.
     */
    public String getContainerImage() {
        return (String) get(9);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.job_type</code>.
     */
    public void setJobType(JobType value) {
        set(10, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.job_type</code>.
     */
    public JobType getJobType() {
        return (JobType) get(10);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.max_jobs</code>.
     */
    public void setMaxJobs(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.max_jobs</code>.
     */
    public Integer getMaxJobs() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.max_jobs_per_user</code>.
     */
    public void setMaxJobsPerUser(Integer value) {
        set(12, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.max_jobs_per_user</code>.
     */
    public Integer getMaxJobsPerUser() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.strict_file_inputs</code>.
     */
    public void setStrictFileInputs(Boolean value) {
        set(13, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.strict_file_inputs</code>.
     */
    public Boolean getStrictFileInputs() {
        return (Boolean) get(13);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.job_description</code>.
     */
    public void setJobDescription(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.job_description</code>.
     */
    public String getJobDescription() {
        return (String) get(14);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.dynamic_exec_system</code>.
     */
    public void setDynamicExecSystem(Boolean value) {
        set(15, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.dynamic_exec_system</code>.
     */
    public Boolean getDynamicExecSystem() {
        return (Boolean) get(15);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.exec_system_constraints</code>.
     */
    public void setExecSystemConstraints(String[] value) {
        set(16, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.exec_system_constraints</code>.
     */
    public String[] getExecSystemConstraints() {
        return (String[]) get(16);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.exec_system_id</code>.
     */
    public void setExecSystemId(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.exec_system_id</code>.
     */
    public String getExecSystemId() {
        return (String) get(17);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.exec_system_exec_dir</code>.
     */
    public void setExecSystemExecDir(String value) {
        set(18, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.exec_system_exec_dir</code>.
     */
    public String getExecSystemExecDir() {
        return (String) get(18);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.exec_system_input_dir</code>.
     */
    public void setExecSystemInputDir(String value) {
        set(19, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.exec_system_input_dir</code>.
     */
    public String getExecSystemInputDir() {
        return (String) get(19);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.exec_system_output_dir</code>.
     */
    public void setExecSystemOutputDir(String value) {
        set(20, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.exec_system_output_dir</code>.
     */
    public String getExecSystemOutputDir() {
        return (String) get(20);
    }

    /**
     * Setter for
     * <code>tapis_app.apps_versions.exec_system_logical_queue</code>.
     */
    public void setExecSystemLogicalQueue(String value) {
        set(21, value);
    }

    /**
     * Getter for
     * <code>tapis_app.apps_versions.exec_system_logical_queue</code>.
     */
    public String getExecSystemLogicalQueue() {
        return (String) get(21);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.archive_system_id</code>.
     */
    public void setArchiveSystemId(String value) {
        set(22, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.archive_system_id</code>.
     */
    public String getArchiveSystemId() {
        return (String) get(22);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.archive_system_dir</code>.
     */
    public void setArchiveSystemDir(String value) {
        set(23, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.archive_system_dir</code>.
     */
    public String getArchiveSystemDir() {
        return (String) get(23);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.archive_on_app_error</code>.
     */
    public void setArchiveOnAppError(Boolean value) {
        set(24, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.archive_on_app_error</code>.
     */
    public Boolean getArchiveOnAppError() {
        return (Boolean) get(24);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.is_mpi</code>.
     */
    public void setIsMpi(Boolean value) {
        set(25, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.is_mpi</code>.
     */
    public Boolean getIsMpi() {
        return (Boolean) get(25);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.mpi_cmd</code>.
     */
    public void setMpiCmd(String value) {
        set(26, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.mpi_cmd</code>.
     */
    public String getMpiCmd() {
        return (String) get(26);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.cmd_prefix</code>.
     */
    public void setCmdPrefix(String value) {
        set(27, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.cmd_prefix</code>.
     */
    public String getCmdPrefix() {
        return (String) get(27);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.parameter_set</code>.
     */
    public void setParameterSet(JsonElement value) {
        set(28, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.parameter_set</code>.
     */
    public JsonElement getParameterSet() {
        return (JsonElement) get(28);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.file_inputs</code>.
     */
    public void setFileInputs(JsonElement value) {
        set(29, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.file_inputs</code>.
     */
    public JsonElement getFileInputs() {
        return (JsonElement) get(29);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.file_input_arrays</code>.
     */
    public void setFileInputArrays(JsonElement value) {
        set(30, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.file_input_arrays</code>.
     */
    public JsonElement getFileInputArrays() {
        return (JsonElement) get(30);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.node_count</code>.
     */
    public void setNodeCount(Integer value) {
        set(31, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.node_count</code>.
     */
    public Integer getNodeCount() {
        return (Integer) get(31);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.cores_per_node</code>.
     */
    public void setCoresPerNode(Integer value) {
        set(32, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.cores_per_node</code>.
     */
    public Integer getCoresPerNode() {
        return (Integer) get(32);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.memory_mb</code>.
     */
    public void setMemoryMb(Integer value) {
        set(33, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.memory_mb</code>.
     */
    public Integer getMemoryMb() {
        return (Integer) get(33);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.max_minutes</code>.
     */
    public void setMaxMinutes(Integer value) {
        set(34, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.max_minutes</code>.
     */
    public Integer getMaxMinutes() {
        return (Integer) get(34);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.subscriptions</code>.
     */
    public void setSubscriptions(JsonElement value) {
        set(35, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.subscriptions</code>.
     */
    public JsonElement getSubscriptions() {
        return (JsonElement) get(35);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.job_tags</code>.
     */
    public void setJobTags(String[] value) {
        set(36, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.job_tags</code>.
     */
    public String[] getJobTags() {
        return (String[]) get(36);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.tags</code>. Tags for user
     * supplied key:value pairs
     */
    public void setTags(String[] value) {
        set(37, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.tags</code>. Tags for user
     * supplied key:value pairs
     */
    public String[] getTags() {
        return (String[]) get(37);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.notes</code>. Notes for general
     * information stored as JSON
     */
    public void setNotes(JsonElement value) {
        set(38, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.notes</code>. Notes for general
     * information stored as JSON
     */
    public JsonElement getNotes() {
        return (JsonElement) get(38);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.uuid</code>.
     */
    public void setUuid(UUID value) {
        set(39, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.uuid</code>.
     */
    public UUID getUuid() {
        return (UUID) get(39);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.created</code>. UTC time for
     * when record was created
     */
    public void setCreated(LocalDateTime value) {
        set(40, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.created</code>. UTC time for
     * when record was created
     */
    public LocalDateTime getCreated() {
        return (LocalDateTime) get(40);
    }

    /**
     * Setter for <code>tapis_app.apps_versions.updated</code>. UTC time for
     * when record was last updated
     */
    public void setUpdated(LocalDateTime value) {
        set(41, value);
    }

    /**
     * Getter for <code>tapis_app.apps_versions.updated</code>. UTC time for
     * when record was last updated
     */
    public LocalDateTime getUpdated() {
        return (LocalDateTime) get(41);
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
     * Create a detached AppsVersionsRecord
     */
    public AppsVersionsRecord() {
        super(AppsVersions.APPS_VERSIONS);
    }

    /**
     * Create a detached, initialised AppsVersionsRecord
     */
    public AppsVersionsRecord(Integer seqId, Integer appSeqId, String tenant, String id, String version, String description, Runtime runtime, String runtimeVersion, String[] runtimeOptions, String containerImage, JobType jobType, Integer maxJobs, Integer maxJobsPerUser, Boolean strictFileInputs, String jobDescription, Boolean dynamicExecSystem, String[] execSystemConstraints, String execSystemId, String execSystemExecDir, String execSystemInputDir, String execSystemOutputDir, String execSystemLogicalQueue, String archiveSystemId, String archiveSystemDir, Boolean archiveOnAppError, Boolean isMpi, String mpiCmd, String cmdPrefix, JsonElement parameterSet, JsonElement fileInputs, JsonElement fileInputArrays, Integer nodeCount, Integer coresPerNode, Integer memoryMb, Integer maxMinutes, JsonElement subscriptions, String[] jobTags, String[] tags, JsonElement notes, UUID uuid, LocalDateTime created, LocalDateTime updated) {
        super(AppsVersions.APPS_VERSIONS);

        setSeqId(seqId);
        setAppSeqId(appSeqId);
        setTenant(tenant);
        setId(id);
        setVersion(version);
        setDescription(description);
        setRuntime(runtime);
        setRuntimeVersion(runtimeVersion);
        setRuntimeOptions(runtimeOptions);
        setContainerImage(containerImage);
        setJobType(jobType);
        setMaxJobs(maxJobs);
        setMaxJobsPerUser(maxJobsPerUser);
        setStrictFileInputs(strictFileInputs);
        setJobDescription(jobDescription);
        setDynamicExecSystem(dynamicExecSystem);
        setExecSystemConstraints(execSystemConstraints);
        setExecSystemId(execSystemId);
        setExecSystemExecDir(execSystemExecDir);
        setExecSystemInputDir(execSystemInputDir);
        setExecSystemOutputDir(execSystemOutputDir);
        setExecSystemLogicalQueue(execSystemLogicalQueue);
        setArchiveSystemId(archiveSystemId);
        setArchiveSystemDir(archiveSystemDir);
        setArchiveOnAppError(archiveOnAppError);
        setIsMpi(isMpi);
        setMpiCmd(mpiCmd);
        setCmdPrefix(cmdPrefix);
        setParameterSet(parameterSet);
        setFileInputs(fileInputs);
        setFileInputArrays(fileInputArrays);
        setNodeCount(nodeCount);
        setCoresPerNode(coresPerNode);
        setMemoryMb(memoryMb);
        setMaxMinutes(maxMinutes);
        setSubscriptions(subscriptions);
        setJobTags(jobTags);
        setTags(tags);
        setNotes(notes);
        setUuid(uuid);
        setCreated(created);
        setUpdated(updated);
    }
}
