package com.bjike.goddess.workjoin.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 工作交接
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkJoinTO extends BaseTO {
    public interface audit{}
    /**
     * 工作交接编号
     */
    @NotBlank(message = "工作交接编号不能为空",groups = {ADD.class, EDIT.class})
    private String workJoinNum;

    /**
     * 工作交接开始时间
     */
    @NotBlank(message = "工作交接开始时间不能为空",groups = {ADD.class, EDIT.class})
    private String workJoinStartTime;

    /**
     * 工作交接结束时间
     */
    @NotBlank(message = "工作交接结束时间不能为空",groups = {ADD.class, EDIT.class})
    private String workJoinendTime;

    /**
     * 所属模块/组别
     */
    @NotBlank(message = "所属模块/组别不能为空",groups = {ADD.class, EDIT.class})
    private String belongModule;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;

    /**
     * 工作交接原因
     */
    @NotBlank(message = "工作交接原因不能为空",groups = {ADD.class, EDIT.class})
    private String workJoinCause;

    /**
     * 工作范围
     */
    @NotBlank(message = "工作范围不能为空",groups = {ADD.class, EDIT.class})
    private String workScope;

    /**
     * 工作目的
     */
    @NotBlank(message = "工作目的不能为空",groups = {ADD.class, EDIT.class})
    private String workPurpose;

    /**
     * 工作权限
     */
    @NotBlank(message = "工作权限不能为空",groups = {ADD.class, EDIT.class})
    private String workPermission;

    /**
     * 交接人
     */
    @NotBlank(message = "交接人不能为空",groups = {ADD.class, EDIT.class})
    private String successor;

    /**
     * 交接人确认(是/否)
     */
    @NotNull(message = "交接人确认(是/否)不能为空",groups = {ADD.class, EDIT.class})
    private Boolean heir;

    /**
     * 接手人
     */
    @NotBlank(message = "接手人不能为空",groups = {ADD.class, EDIT.class})
    private String replacement;

    /**
     * 接手人确认(是/否)
     */
    @NotNull(message = "接手人确认(是/否)不能为空",groups = {ADD.class, EDIT.class})
    private Boolean inheritor;

    /**
     * 负责人
     */
    @NotBlank(message = "负责人不能为空",groups = {WorkJoinTO.audit.class})
    private String principal;

    /**
     * 负责人确认(是/否)
     */
    @NotNull(message = "负责人确认(是/否)不能为空",groups = {WorkJoinTO.audit.class})
    private Boolean head;

    public String getWorkJoinNum() {
        return workJoinNum;
    }

    public void setWorkJoinNum(String workJoinNum) {
        this.workJoinNum = workJoinNum;
    }

    public String getWorkJoinStartTime() {
        return workJoinStartTime;
    }

    public void setWorkJoinStartTime(String workJoinStartTime) {
        this.workJoinStartTime = workJoinStartTime;
    }

    public String getWorkJoinendTime() {
        return workJoinendTime;
    }

    public void setWorkJoinendTime(String workJoinendTime) {
        this.workJoinendTime = workJoinendTime;
    }

    public String getBelongModule() {
        return belongModule;
    }

    public void setBelongModule(String belongModule) {
        this.belongModule = belongModule;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getWorkJoinCause() {
        return workJoinCause;
    }

    public void setWorkJoinCause(String workJoinCause) {
        this.workJoinCause = workJoinCause;
    }

    public String getWorkScope() {
        return workScope;
    }

    public void setWorkScope(String workScope) {
        this.workScope = workScope;
    }

    public String getWorkPurpose() {
        return workPurpose;
    }

    public void setWorkPurpose(String workPurpose) {
        this.workPurpose = workPurpose;
    }

    public String getWorkPermission() {
        return workPermission;
    }

    public void setWorkPermission(String workPermission) {
        this.workPermission = workPermission;
    }

    public String getSuccessor() {
        return successor;
    }

    public void setSuccessor(String successor) {
        this.successor = successor;
    }

    public Boolean getHeir() {
        return heir;
    }

    public void setHeir(Boolean heir) {
        this.heir = heir;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public Boolean getInheritor() {
        return inheritor;
    }

    public void setInheritor(Boolean inheritor) {
        this.inheritor = inheritor;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Boolean getHead() {
        return head;
    }

    public void setHead(Boolean head) {
        this.head = head;
    }
}