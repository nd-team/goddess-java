package com.bjike.goddess.materialsummary.vo;

import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.materialinstock.type.MaterialState;

/**
 * 入库地区年汇总表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-05-22 11:11 ]
 * @Description: [ 入库地区年汇总表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InStockAreaYearSumVO {

    /**
     * id
     */
    private String id;
    /**
     * 汇总起始时间
     */
    private String sumStartTime;

    /**
     * 汇总结束时间
     */
    private String sumEndTime;

    /**
     * 现入库地区
     */
    private String curInstockArea;

    /**
     * 部门(项目组)
     */
    private String projectGroup;

    /**
     * 入库物资状态
     */
    private MaterialState instockState;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public MaterialState getInstockState() {
        return instockState;
    }

    public void setInstockState(MaterialState instockState) {
        this.instockState = instockState;
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