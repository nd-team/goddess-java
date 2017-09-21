package com.bjike.goddess.task.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 问题录入对象传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-20 10:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProblemTO extends BaseTO {

    /**
     * 所属地区
     */
    @NotBlank(message = "所属地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;
    /**
     * 所属部门
     */
    @NotBlank(message = "所属部门不能为空", groups = {ADD.class, EDIT.class})

    private String department;
    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空", groups = {ADD.class, EDIT.class})

    private String position;
    /**
     * 问题提出人
     */
    @NotBlank(message = "问题提出人不能为空", groups = {ADD.class, EDIT.class})

    private String claimer;
    /**
     * 问题项目
     */
    @NotBlank(message = "问题项目不能为空", groups = {ADD.class, EDIT.class})
    private String project;
    /**
     * 背景描述
     */
    @NotBlank(message = "背景描述不能为空", groups = {ADD.class, EDIT.class})

    private String describes;
    /**
     * 问题描述
     */
    @NotBlank(message = "问题描述不能为空", groups = {ADD.class, EDIT.class})

    private String details;

    /**
     * 问题类型
     */
    @NotBlank(message = "问题类型不能为空", groups = {ADD.class, EDIT.class})

    private String typeId;
    /**
     * 是否上报
     */
    private Boolean report;
    /**
     * 审核人
     */
    @NotBlank(message = "审核人不能为空", groups = {ADD.class, EDIT.class})
    private String auditor;
    /**
     * 期望受理时间
     */
    @NotBlank(message = "期望受理时间不能为空", groups = {ADD.class, EDIT.class})

    private String expectTime;
    /**
     * 协助部门
     */
    @NotBlank(message = "协助部门不能为空", groups = {ADD.class, EDIT.class})

    private String giveDepartment;
    /**
     * 协助模块
     */
    @NotBlank(message = "协助模块不能为空", groups = {ADD.class, EDIT.class})

    private String giveModule;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getClaimer() {
        return claimer;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(String expectTime) {
        this.expectTime = expectTime;
    }

    public String getGiveDepartment() {
        return giveDepartment;
    }

    public void setGiveDepartment(String giveDepartment) {
        this.giveDepartment = giveDepartment;
    }

    public String getGiveModule() {
        return giveModule;
    }

    public void setGiveModule(String giveModule) {
        this.giveModule = giveModule;
    }
}
