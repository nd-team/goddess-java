package com.bjike.goddess.businesscommission.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午4:38
 * @Description: [业务提成定额]
 * @Version: [1.0.0]
 */
public class QuotaExcele extends BaseTO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目组/部门
     */
    @ExcelHeader(name = "项目组/部门", notNull = true)
    private String department;

    /**
     * 内部项目名称
     */
    @ExcelHeader(name = "内部项目名称", notNull = true)
    private String projectName;

    /**
     * 是否立项
     */
    @ExcelHeader(name = "是否立项", notNull = true)
    private Boolean isBuild;

    /**
     * 立项时间
     */
    @ExcelHeader(name = "立项时间", notNull = true)
    private String buildTime;

    /**
     * 是否完工
     */
    @ExcelHeader(name = "是否完工", notNull = true)
    private Boolean isComplete;

    /**
     * 目标业务提成额
     */
    @ExcelHeader(name = "目标业务提成额", notNull = true)
    private Double aimAmount;

    /**
     * 计划业务提成额
     */
    @ExcelHeader(name = "计划业务提成额", notNull = true)
    private Double planAmount;

    /**
     * 实际业务提成额
     */
    @ExcelHeader(name = "实际业务提成额", notNull = true)
    private Double actualAmount;

    /**
     * 信息提供人
     */
    @ExcelHeader(name = "信息提供人", notNull = true)
    private String informationProvide;

    /**
     * 信息提供占比
     */
    @ExcelHeader(name = "信息提供占比", notNull = true)
    private Double messageProportion;

    /**
     * 信息提供占额
     */
    @ExcelHeader(name = "信息提供占额", notNull = true)
    private Double provideAccount;

    /**
     * 业务揽接人
     */
    @ExcelHeader(name = "业务揽接人", notNull = true)
    private String businessContracting;

    /**
     * 业务揽接占比
     */
    @ExcelHeader(name = "业务揽接占比", notNull = true)
    private Double businessProportion;

    /**
     * 业务揽接占额
     */
    @ExcelHeader(name = "业务揽接占额", notNull = true)
    private Double contractAccount;

    /**
     * 业务洽谈人
     */
    @ExcelHeader(name = "业务洽谈人", notNull = true)
    private String businessNegotiation;

    /**
     * 业务洽谈占比
     */
    @ExcelHeader(name = "业务洽谈占比", notNull = true)
    private Double talkProportion;

    /**
     * 业务洽谈占额
     */
    @ExcelHeader(name = "业务洽谈占额", notNull = true)
    private Double negotiationAccount;

    /**
     * 维护人
     */
    @ExcelHeader(name = "维护人", notNull = true)
    private String maintenance;

    /**
     * 维护占比
     */
    @ExcelHeader(name = "维护占比", notNull = true)
    private Double maintainProportion;

    /**
     * 维护占额
     */
    @ExcelHeader(name = "维护占额", notNull = true)
    private Double maintenanceAccount;

    /**
     * 剩余分配比例
     */
    @ExcelHeader(name = "剩余分配比例", notNull = true)
    private Double surplusProportion;

    /**
     * 剩余占额
     */
    @ExcelHeader(name = "剩余占额", notNull = true)
    private Double remainingAccount;

    /**
     * 总比例
     */
    @ExcelHeader(name = "总比例", notNull = true)
    private Double ratio;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = false)
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

    public Boolean getBuild() {
        return isBuild;
    }

    public void setBuild(Boolean build) {
        isBuild = build;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
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
