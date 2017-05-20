package com.bjike.goddess.receivable.vo;

import java.io.Serializable;

/**
 * Created by ike on 17-5-6.
 */
public class CollectAreaDetailVO implements Serializable{
    /**
     * 地区
     */
    private String area;
    /**
     * 项目内部名称
     */
    private String innerName;
    /**
     * 派工单价
     */
    private Double taskPrice;
    /**
     * 已派工量
     */
    private Double pactSize;
    /**
     * 完工时间
     */
    private String finishTime;

    /**
     * 验收交维时间
     */
    private String checkTime;

    /**
     * 签字审批时间
     */
    private String auditTime;

    /**
     * 结算审批时间
     */
    private String countTime;

    /**
     * 发票审核时间
     */
    private String billTime;

    /**
     * 预计支付时间
     */
    private String planTime;

    /**
     * 到账时间
     */
    private String accountTime;

    /**
     * 管理费
     */
    private Double managementFee;
    /**
     * 到账金额
     */
    private Double accountMoney;
    /**
     * 税金
     */
    private Double taxes;
    /**
     * 税后金额
     */
    private Double afterTax;
    /**
     * 总包单位
     */
    private String name;
    /**
     * 是否已走结算流程
     */
    private String is_flow;
    /**
     * 详细情况
     */
    private String detailInfo;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public Double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(Double taskPrice) {
        this.taskPrice = taskPrice;
    }

    public Double getPactSize() {
        return pactSize;
    }

    public void setPactSize(Double pactSize) {
        this.pactSize = pactSize;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getCountTime() {
        return countTime;
    }

    public void setCountTime(String countTime) {
        this.countTime = countTime;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }

    public Double getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Double managementFee) {
        this.managementFee = managementFee;
    }

    public Double getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(Double accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Double getAfterTax() {
        return afterTax;
    }

    public void setAfterTax(Double afterTax) {
        this.afterTax = afterTax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_flow() {
        return is_flow;
    }

    public void setIs_flow(String is_flow) {
        this.is_flow = is_flow;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }
}
