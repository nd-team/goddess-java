package com.bjike.goddess.materialsummary.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialinstock.type.InstockType;

/**
 * 入库来源周汇总业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:05 ]
 * @Description: [ 入库来源周汇总业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InStockSoruceWeekSumBO extends BaseBO {

    /**
     * 汇总起始时间
     */
    private String sumStartTime;

    /**
     * 汇总结束时间
     */
    private String sumEndTime;

    /**
     * 入库来源
     */
    private InstockType instockType;

    /**
     * 现入库地区
     */
    private String curInstockArea;

    /**
     * 部门(项目组)
     */
    private String projectGroup;

    /**
     * 总数量
     */
    private Integer totalQty;

    /**
     * 总金额
     */
    private Double amount;

    /**
     * 状态
     */
    private Status status;

    public String getSumStartTime() {
        return sumStartTime;
    }

    public void setSumStartTime(String sumStartTime) {
        this.sumStartTime = sumStartTime;
    }

    public String getSumEndTime() {
        return sumEndTime;
    }

    public void setSumEndTime(String sumEndTime) {
        this.sumEndTime = sumEndTime;
    }

    public InstockType getInstockType() {
        return instockType;
    }

    public void setInstockType(InstockType instockType) {
        this.instockType = instockType;
    }

    public String getCurInstockArea() {
        return curInstockArea;
    }

    public void setCurInstockArea(String curInstockArea) {
        this.curInstockArea = curInstockArea;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}