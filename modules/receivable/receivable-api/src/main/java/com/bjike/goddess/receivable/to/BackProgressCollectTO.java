package com.bjike.goddess.receivable.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 回款进度
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-10 02:52 ]
 * @Description: [ 回款进度 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BackProgressCollectTO extends BaseTO {

    /**
     * 地区
     */
    private String area;

    /**
     * 外包单位
     */
    private String contractor;

    /**
     * 类型
     */
    private String type;

    /**
     * 专业
     */
    private String major;
    /**
     * 开发票开始时间
     */
    private String invoiceStartTime;
    /**
     * 开发票结束时间
     */
    private String invoiceEndTime;

    /**
     * 预收帐款时间开始时间
     */
    private String advanceAccountStartTime;
    /**
     * 预收帐款时间结束时间
     */
    private String advanceAccountEndTime;
    /**
     * 运营商名称
     */
    private String operatorName;
    /**
     * 派工情况
     */
    private String taskCase;

    /**
     * 实际完工状态
     */
    private String completeStatus;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInvoiceStartTime() {
        return invoiceStartTime;
    }

    public void setInvoiceStartTime(String invoiceStartTime) {
        this.invoiceStartTime = invoiceStartTime;
    }

    public String getInvoiceEndTime() {
        return invoiceEndTime;
    }

    public void setInvoiceEndTime(String invoiceEndTime) {
        this.invoiceEndTime = invoiceEndTime;
    }

    public String getAdvanceAccountStartTime() {
        return advanceAccountStartTime;
    }

    public void setAdvanceAccountStartTime(String advanceAccountStartTime) {
        this.advanceAccountStartTime = advanceAccountStartTime;
    }

    public String getAdvanceAccountEndTime() {
        return advanceAccountEndTime;
    }

    public void setAdvanceAccountEndTime(String advanceAccountEndTime) {
        this.advanceAccountEndTime = advanceAccountEndTime;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTaskCase() {
        return taskCase;
    }

    public void setTaskCase(String taskCase) {
        this.taskCase = taskCase;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }
}