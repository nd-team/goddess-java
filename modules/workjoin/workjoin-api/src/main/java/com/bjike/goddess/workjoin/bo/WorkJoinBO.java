package com.bjike.goddess.workjoin.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 工作交接业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkJoinBO extends BaseBO {

    /**
     * 工作交接开始时间
     */
    private String workJoinStartTime;

    /**
     * 工作交接结束时间
     */
    private String workJoinendTime;

    /**
     * 所属模块/组别
     */
    private String belongModule;

    /**
     * 岗位
     */
    private String jobs;

    /**
     * 工作交接原因
     */
    private String workJoinCause;

    /**
     * 工作范围
     */
    private String workScope;

    /**
     * 工作目的
     */
    private String workPurpose;

    /**
     * 工作权限
     */
    private String workPermission;

    /**
     * 交接人
     */
    private String successor;

    /**
     * 交接人确认(是/否)
     */
    private Boolean heir;

    /**
     * 接手人
     */
    private String replacement;

    /**
     * 接手人确认(是/否)
     */
    private Boolean inheritor;

    /**
     * 负责人
     */
    private String principal;

    /**
     * 负责人确认(是/否)
     */
    private Boolean head;


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