package com.bjike.goddess.businessproject.entity;

import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.ContractProperty;
import com.bjike.goddess.businessproject.enums.ProjectStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 商务项目合同签订与立项管理
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-20T20:13:56.343 ]
 * @Description: [ 商务项目合同签订与立项管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessproject_siginmanage")
public class SiginManage extends BaseEntity {

    /**
     * 业务类型
     */
    @Column(name = "businessType",  columnDefinition = "INT(2)    COMMENT '业务类型'")
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessSubject",  columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessSubject;

    /**
     * 合作方式
     */
    @Column(name = "businessCooperate",  columnDefinition = "INT(2)   COMMENT '合作方式'")
    private BusinessCooperate businessCooperate;

    /**
     * 外部项目名称
     */
    @Column(name = "outerProject",  columnDefinition = "VARCHAR(255)   COMMENT '外部项目名称'")
    private String outerProject;

    /**
     * 甲方公司名称
     */
    @Column(name = "firstCompany",  columnDefinition = "VARCHAR(255)   COMMENT '甲方公司名称'")
    private String firstCompany;

    /**
     * 乙方公司名称
     */
    @Column(name = "secondCompany",  columnDefinition = "VARCHAR(255)   COMMENT '乙方公司名称'")
    private String secondCompany;

    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 合同金额
     */
    @Column(name = "money",  columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double money;

    /**
     * 开工时间
     */
    @Column(name = "startProjectTime",  columnDefinition = "DATE   COMMENT '开工时间'")
    private LocalDate startProjectTime;

    /**
     * 完工时间
     */
    @Column(name = "endProjectTime",  columnDefinition = "DATE  COMMENT '完工时间'")
    private LocalDate endProjectTime;

    /**
     * 签订状态
     */
    @Column(name = "siginStatus",  columnDefinition = "VARCHAR(255)   COMMENT '签订状态'")
    private String siginStatus;

    /**
     * 合同属性
     */
    @Column(name = "contractProperty",  columnDefinition = "INT(2)   COMMENT '合同属性'")
    private ContractProperty contractProperty;

    /**
     * 立项情况
     */
    @Column(name = "makeProject",  columnDefinition = "VARCHAR(255)   COMMENT '立项情况'")
    private String makeProject;

    /**
     * 内部项目名称
     */
    @Column(name = "innerProject",  columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String innerProject;

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目负责人
     */
    @Column(name = "projectCharge",  columnDefinition = "VARCHAR(255)   COMMENT '项目负责人'")
    private String projectCharge;

    /**
     * 备注
     */
    @Column(name = "remark",  columnDefinition = "MEDIUMTEXT   COMMENT '备注'")
    private String remark;

    /**
     * 总经办
     */
    @Column(name = "manager",  columnDefinition = "VARCHAR(255)   COMMENT '总经办'")
    private String manager;

    /**
     * 审核意见
     */
    @Column(name = "auditAdvice",  columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String auditAdvice;
    /**
     * 派工单号
     */
    @Column(name = "taskNum", columnDefinition = "VARCHAR(255)   COMMENT '派工单号'")
    private String taskNum;
    /**
     * 项目状态
     */
    @Column(name = "projectStatus", columnDefinition = "TINYINT(1)   COMMENT '项目状态'")
    private ProjectStatus projectStatus;
    /**
     * 合同规模数量
     */
    @Column(name = "contractScale", columnDefinition = "DECIMAL(10,2)   COMMENT '合同规模数量'")
    private Double contractScale;
    /**
     * 规模数量
     */
    @Column(name = "scale", columnDefinition = "DECIMAL(10,2)   COMMENT '规模数量'")
    private Double scale;
    /**
     * 专业
     */
    @Column(name = "major", columnDefinition = "VARCHAR(255)   COMMENT '专业'")
    private String major;

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Double getContractScale() {
        return contractScale;
    }

    public void setContractScale(Double contractScale) {
        this.contractScale = contractScale;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public BusinessCooperate getBusinessCooperate() {
        return businessCooperate;
    }

    public void setBusinessCooperate(BusinessCooperate businessCooperate) {
        this.businessCooperate = businessCooperate;
    }

    public String getOuterProject() {
        return outerProject;
    }

    public void setOuterProject(String outerProject) {
        this.outerProject = outerProject;
    }

    public String getFirstCompany() {
        return firstCompany;
    }

    public void setFirstCompany(String firstCompany) {
        this.firstCompany = firstCompany;
    }

    public String getSecondCompany() {
        return secondCompany;
    }

    public void setSecondCompany(String secondCompany) {
        this.secondCompany = secondCompany;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDate getStartProjectTime() {
        return startProjectTime;
    }

    public void setStartProjectTime(LocalDate startProjectTime) {
        this.startProjectTime = startProjectTime;
    }

    public LocalDate getEndProjectTime() {
        return endProjectTime;
    }

    public void setEndProjectTime(LocalDate endProjectTime) {
        this.endProjectTime = endProjectTime;
    }

    public String getSiginStatus() {
        return siginStatus;
    }

    public void setSiginStatus(String siginStatus) {
        this.siginStatus = siginStatus;
    }

    public ContractProperty getContractProperty() {
        return contractProperty;
    }

    public void setContractProperty(ContractProperty contractProperty) {
        this.contractProperty = contractProperty;
    }

    public String getMakeProject() {
        return makeProject;
    }

    public void setMakeProject(String makeProject) {
        this.makeProject = makeProject;
    }

    public String getInnerProject() {
        return innerProject;
    }

    public void setInnerProject(String innerProject) {
        this.innerProject = innerProject;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }


}