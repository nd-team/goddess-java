package com.bjike.goddess.accommodation.entity;

import com.bjike.goddess.accommodation.enums.PassStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房方案]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "accommodation_rentalprecept")
public class RentalPrecept extends BaseEntity {
    /**
     * 租房编号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房编号'",unique = true)
    private String rentNum;
    /**
     * 姓名（用户名称）
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '姓名（用户名称）'")
    private String name;
    /**
     * 员工编号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '员工编号'")
    private String employeeNum;
    /**
     * 地区
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;
    /**
     * 岗位
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '岗位'")
    private String jobs;
    /**
     * 项目组
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目组'")
    private String projectGroup;
    /**
     * 项目名称
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目名称'")
    private String projectName;
    /**
     * 租房用途
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房用途'")
    private String purpose;
    /**
     * 住宿人数
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '住宿人数'")
    private Integer accommodationPeople;
    /**
     * 租房规格
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房规格'")
    private String norms;
    /**
     * 租房要求
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房要求'")
    private String requirements;
    /**
     * 租房期限
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '租房期限'")
    private String deadline;
    /**
     * 完成租房开始时间
     */
    @Column(columnDefinition = "DATE COMMENT '完成租房开始时间'")
    private LocalDate completeStartTime;
    /**
     * 完成租房结束时间
     */
    @Column(columnDefinition = "DATE COMMENT '完成租房结束时间'")
    private LocalDate completeEndTime;
    /**
     * 注意事项
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '注意事项'")
    private String attention;
    /**
     * 资金意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '资金意见'")
    private String moneyOn;
    /**
     * 商务发展部意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '商务发展部意见'")
    private String commerceRemark;
    /**
     * 运营财务部意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '运营财务部意见'")
    private String operatingRemark;
    /**
     * 综合资源部意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '综合资源部意见'")
    private String comprehensiveRemark;
    /**
     * 项目经理
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目经理'")
    private String manage;
    /**
     * 项目经理意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目经理意见'")
    private String manageOpinion;
    /**
     * 项目经理是否通过(是/否)
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '项目经理是否通过(是/否)'")
    private String managePass;

    /**
     * 总经办
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '总经办'")
    private String general;
    /**
     * 总经办意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '总经办意见'")
    private String generalOpinion;
    /**
     * 总经办是否通过(是/否)
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '总经办是否通过(是/否)'")
    private String generalPass;
    /**
     * 状态
     */
    @Column(name = "passStatus",  columnDefinition = "INT(2)   COMMENT '状态'")
    private PassStatus passStatus;
    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    public String getRentNum() {
        return rentNum;
    }

    public void setRentNum(String rentNum) {
        this.rentNum = rentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getAccommodationPeople() {
        return accommodationPeople;
    }

    public void setAccommodationPeople(Integer accommodationPeople) {
        this.accommodationPeople = accommodationPeople;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCompleteStartTime() {
        return completeStartTime;
    }

    public void setCompleteStartTime(LocalDate completeStartTime) {
        this.completeStartTime = completeStartTime;
    }

    public LocalDate getCompleteEndTime() {
        return completeEndTime;
    }

    public void setCompleteEndTime(LocalDate completeEndTime) {
        this.completeEndTime = completeEndTime;
    }

    public String getMoneyOn() {
        return moneyOn;
    }

    public void setMoneyOn(String moneyOn) {
        this.moneyOn = moneyOn;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getCommerceRemark() {
        return commerceRemark;
    }

    public void setCommerceRemark(String commerceRemark) {
        this.commerceRemark = commerceRemark;
    }

    public String getOperatingRemark() {
        return operatingRemark;
    }

    public void setOperatingRemark(String operatingRemark) {
        this.operatingRemark = operatingRemark;
    }

    public String getComprehensiveRemark() {
        return comprehensiveRemark;
    }

    public void setComprehensiveRemark(String comprehensiveRemark) {
        this.comprehensiveRemark = comprehensiveRemark;
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage;
    }

    public String getManageOpinion() {
        return manageOpinion;
    }

    public void setManageOpinion(String manageOpinion) {
        this.manageOpinion = manageOpinion;
    }

    public String getManagePass() {
        return managePass;
    }

    public void setManagePass(String managePass) {
        this.managePass = managePass;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getGeneralOpinion() {
        return generalOpinion;
    }

    public void setGeneralOpinion(String generalOpinion) {
        this.generalOpinion = generalOpinion;
    }

    public String getGeneralPass() {
        return generalPass;
    }

    public void setGeneralPass(String generalPass) {
        this.generalPass = generalPass;
    }

    public PassStatus getPassStatus() {
        return passStatus;
    }

    public void setPassStatus(PassStatus passStatus) {
        this.passStatus = passStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
