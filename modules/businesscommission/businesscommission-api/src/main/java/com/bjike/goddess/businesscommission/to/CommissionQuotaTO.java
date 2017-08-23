package com.bjike.goddess.businesscommission.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 业务提成定额表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-29 04:49 ]
 * @Description: [ 业务提成定额表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CommissionQuotaTO extends BaseTO {

    public interface TestAdd{}
    /**
     * 地区
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "地区不能为空")
    private String area;

    /**
     * 项目名称
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "项目名称不能为空")
    private String projectName;

    /**
     * 实际业务提成总额
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "实际业务提成总额不能为空")
    private Double actualAmount;

    /**
     * 信息提供人
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "信息提供人不能为空")
    private String informationProvide;

    /**
     * 信息提供占额
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "信息提供占额不能为空")
    private Double provideAccount;

    /**
     * 业务揽接人
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "业务揽接人不能为空")
    private String businessContracting;

    /**
     * 业务揽接占额
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "业务揽接占额不能为空")
    private Double contractAccount;

    /**
     * 业务洽谈人
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "业务洽谈人不能为空")
    private String businessNegotiation;

    /**
     * 业务洽谈占额
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "业务洽谈占额不能为空")
    private Double negotiationAccount;

    /**
     * 维护人
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "维护人不能为空")
    private String maintenance;

    /**
     * 维护占额
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "维护占额不能为空")
    private Double maintenanceAccount;

    /**
     * 剩余占额
     */
    @NotNull(groups = {WeightAllotTO.TestAdd.class} , message = "剩余占额不能为空")
    private Double remainingAccount;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Double getMaintenanceAccount() {
        return maintenanceAccount;
    }

    public void setMaintenanceAccount(Double maintenanceAccount) {
        this.maintenanceAccount = maintenanceAccount;
    }

    public Double getRemainingAccount() {
        return remainingAccount;
    }

    public void setRemainingAccount(Double remainingAccount) {
        this.remainingAccount = remainingAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}