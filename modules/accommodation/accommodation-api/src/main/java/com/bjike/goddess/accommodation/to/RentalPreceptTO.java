package com.bjike.goddess.accommodation.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

/**
 * @Author: [xiazhili]
 * @Date: [2017-3-9 10:16]
 * @Description: [租房方案]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RentalPreceptTO extends BaseTO {
    /**
     * 姓名（用户名称）
     */
    @NotBlank(message = "组名不能为空", groups = ADD.class)
    private String name;
    /**
     * 地区
     */
    private String area;
    /**
     * 岗位
     */
    private String jobs;
    /**
     * 项目组
     */
    private String projectGroup;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 租房用途
     */
    private String purpose;
    /**
     * 住宿人数
     */
    private String accommodationPeople;
    /**
     * 租房规格
     */
    private String norms;
    /**
     * 租房要求
     */
    private String requirements;
    /**
     * 租房期限
     */
    private String deadline;
    /**
     * 完成租房时间
     */
    private LocalDate completeTime;
    /**
     * 资金意见
     */
    private String moneyOn;
    /**
     * 注意事项
     */
    private String attention;
    /**
     * 商务发展部意见
     */
    private String commerceRemark;
    /**
     * 运营财务部意见
     */
    private String operatingRemark;
    /**
     * 综合资源部意见
     */
    private String comprehensiveRemark;
    /**
     * 项目经理审批
     */
    private String manageApproval;

    /**
     * 总经办审批
     */
    private String generalApproval;
    /**
     * 备注
     */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAccommodationPeople() {
        return accommodationPeople;
    }

    public void setAccommodationPeople(String accommodationPeople) {
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

    public LocalDate getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(LocalDate completeTime) {
        this.completeTime = completeTime;
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

    public String getManageApproval() {
        return manageApproval;
    }

    public void setManageApproval(String manageApproval) {
        this.manageApproval = manageApproval;
    }

    public String getGeneralApproval() {
        return generalApproval;
    }

    public void setGeneralApproval(String generalApproval) {
        this.generalApproval = generalApproval;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
