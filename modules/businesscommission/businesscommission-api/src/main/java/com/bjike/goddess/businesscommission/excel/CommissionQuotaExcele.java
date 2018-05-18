package com.bjike.goddess.businesscommission.excel;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午4:38
 * @Description: [业务提成定额]
 * @Version: [1.0.0]
 */
public class CommissionQuotaExcele extends BaseTO {

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String projectName;

    /**
     * 实际业务提成总额
     */
    @ExcelHeader(name = "实际业务提成总额", notNull = true)
    private Double actualAmount;

    /**
     * 信息提供人
     */
    @ExcelHeader(name = "公司名称", notNull = true)
    private String informationProvide;

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
     * 维护占额
     */
    @ExcelHeader(name = "维护占额", notNull = true)
    private Double maintenanceAccount;

    /**
     * 剩余占额
     */
    @ExcelHeader(name = "剩余占额", notNull = true)
    private Double remainingAccount;

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
