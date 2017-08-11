package com.bjike.goddess.receivable.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 回款明细数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReceivableSubsidiaryDTO extends BaseDTO {
    /**
     * 派工单编号
     */
    private String taskNum;

    /**
     * 派工合同号
     */
    private String contractNum;
    /**
     * 外包合同号
     */
    private String outsourcingNum;

    /**
     * 地区
     */
    private String[] area;

    /**
     * 项目名称
     */
    private String innerName;
    /**
     * 总包单位
     */
    private String contractor;
    /**
     * 月份
     */
    private String month;

    /**
     * 季度
     */
    private String quarter;
    /**
     * 年份
     */
    private String year;

    /**
     * 到账开始时间
     */
    private String startTime;
    /**
     * 到账结束时间
     */
    private String endTime;

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getOutsourcingNum() {
        return outsourcingNum;
    }

    public void setOutsourcingNum(String outsourcingNum) {
        this.outsourcingNum = outsourcingNum;
    }

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }

    public String getInnerName() {
        return innerName;
    }

    public void setInnerName(String innerName) {
        this.innerName = innerName;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}