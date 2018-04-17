package com.bjike.goddess.fundrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * @Author: [xiexiaoting]
 * @Date: [2018-04-03 14:30]
 * @Description: [ 资金流水记录]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CapitalFlowRecordDTO extends BaseDTO {

    private String createTime;

    private String modifyTime;

    private String amount;

    private String area;

    private String dataSource;

    private String digest;

    private String expenditure;

    private String income;

    private String project;

    private String projectGroup;

    private String recordDate;

    private String voucherGenerateId;

    private String startTime;

    private String endTime;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getVoucherGenerateId() {
        return voucherGenerateId;
    }

    public void setVoucherGenerateId(String voucherGenerateId) {
        this.voucherGenerateId = voucherGenerateId;
    }
}
