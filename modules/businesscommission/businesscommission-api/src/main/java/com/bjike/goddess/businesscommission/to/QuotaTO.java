package com.bjike.goddess.businesscommission.to;

import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 业务提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-26 11:38 ]
 * @Description: [ 业务提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QuotaTO extends BaseTO {
    public interface TestAdd {
    }

    /**
     * 地区
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "地区不能为空")
    private String area;

    /**
     * 项目组/部门
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "项目组/部门不能为空")
    private String department;

    /**
     * 内部项目名称
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "内部项目名称不能为空")
    private String projectName;

    /**
     * 是否立项
     */
    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "是否立项不能为空")
    private Boolean isBuild;

    /**
     * 立项时间
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "立项时间不能为空")
    private String buildTime;

    /**
     * 是否完工
     */
    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "是否完工不能为空")
    private Boolean isComplete;

    /**
     * 目标业务提成额
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "目标业务提成额不能为空")
    private Double aimAmount;

    /**
     * 计划业务提成额
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "计划业务提成额不能为空")
    private Double planAmount;

    /**
     * 实际业务提成额
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "实际业务提成额不能为空")
    private Double actualAmount;

    /**
     * 信息提供人
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "信息提供人不能为空")
    private String informationProvide;

    /**
     * 信息提供占比
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "信息提供占比不能为空")
    private Double messageProportion;

    /**
     * 信息提供占额
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "信息提供占额不能为空")
    private Double provideAccount;

    /**
     * 业务揽接人
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "业务揽接人不能为空")
    private String businessContracting;

    /**
     * 业务揽接占比
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "业务揽接占比不能为空")
    private Double businessProportion;

    /**
     * 业务揽接占额
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "业务揽接占额不能为空")
    private Double contractAccount;

    /**
     * 业务洽谈人
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "业务洽谈人不能为空")
    private String businessNegotiation;

    /**
     * 业务洽谈占比
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "业务洽谈占比不能为空")
    private Double talkProportion;

    /**
     * 业务洽谈占额
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "业务洽谈占额不能为空")
    private Double negotiationAccount;

    /**
     * 维护人
     */
    @NotBlank(groups = {QuotaTO.TestAdd.class}, message = "维护人不能为空")
    private String maintenance;

    /**
     * 维护占比
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "维护占比不能为空")
    private Double maintainProportion;

    /**
     * 维护占额
     */
//    @NotNull(groups = {QuotaTO.TestAdd.class}, message = "维护占额不能为空")
    private Double maintenanceAccount;

    /**
     * 剩余分配比例
     */
    private Double surplusProportion;

    /**
     * 剩余占额
     */
    private Double remainingAccount;

    /**
     * 总比例
     */
    private Double ratio;

    /**
     * 备注
     */
    private String remark;


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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Boolean getIsBuild() {
        return isBuild;
    }

    public void setIsBuild(Boolean isBuild) {
        this.isBuild = isBuild;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Double getAimAmount() {
        return aimAmount;
    }

    public void setAimAmount(Double aimAmount) {
        this.aimAmount = aimAmount;
    }

    public Double getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(Double planAmount) {
        this.planAmount = planAmount;
    }

    public Double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getInformationProvide() {
        return informationProvide;
    }

    public void setInformationProvide(String informationProvide) {
        this.informationProvide = informationProvide;
    }

    public Double getMessageProportion() {
        return messageProportion;
    }

    public void setMessageProportion(Double messageProportion) {
        this.messageProportion = messageProportion;
    }

    public Double getProvideAccount() {
        return provideAccount;
    }

    public void setProvideAccount(Double provideAccount) {
        this.provideAccount = provideAccount;
    }

    public String getBusinessContracting() {
        return businessContracting;
    }

    public void setBusinessContracting(String businessContracting) {
        this.businessContracting = businessContracting;
    }

    public Double getBusinessProportion() {
        return businessProportion;
    }

    public void setBusinessProportion(Double businessProportion) {
        this.businessProportion = businessProportion;
    }

    public Double getContractAccount() {
        return contractAccount;
    }

    public void setContractAccount(Double contractAccount) {
        this.contractAccount = contractAccount;
    }

    public String getBusinessNegotiation() {
        return businessNegotiation;
    }

    public void setBusinessNegotiation(String businessNegotiation) {
        this.businessNegotiation = businessNegotiation;
    }

    public Double getTalkProportion() {
        return talkProportion;
    }

    public void setTalkProportion(Double talkProportion) {
        this.talkProportion = talkProportion;
    }

    public Double getNegotiationAccount() {
        return negotiationAccount;
    }

    public void setNegotiationAccount(Double negotiationAccount) {
        this.negotiationAccount = negotiationAccount;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public Double getMaintainProportion() {
        return maintainProportion;
    }

    public void setMaintainProportion(Double maintainProportion) {
        this.maintainProportion = maintainProportion;
    }

    public Double getMaintenanceAccount() {
        return maintenanceAccount;
    }

    public void setMaintenanceAccount(Double maintenanceAccount) {
        this.maintenanceAccount = maintenanceAccount;
    }

    public Double getSurplusProportion() {
        return surplusProportion;
    }

    public void setSurplusProportion(Double surplusProportion) {
        this.surplusProportion = surplusProportion;
    }

    public Double getRemainingAccount() {
        return remainingAccount;
    }

    public void setRemainingAccount(Double remainingAccount) {
        this.remainingAccount = remainingAccount;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}