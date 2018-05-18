package com.bjike.goddess.fundrecords.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 资金流水数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FundRecordDTO extends BaseDTO {

    /**
     * 账户来源
     */
    private String dataSource;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 流水日期
     */
    private String recordDate;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 收入
     */
    private Double income;

    /**
     * 支出
     */
    private Double expenditure;

    /**
     * 余额
     */
    private Double amount;

    /**
     * 记账凭证导入资金流水对应的数据id
     */
    private String voucherGenerateId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否获取最新数据
     */
    private boolean lastest;

    public boolean isLastest() {
        return lastest;
    }

    public void setLastest(boolean lastest) {
        this.lastest = lastest;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getVoucherGenerateId() {
        return voucherGenerateId;
    }

    public void setVoucherGenerateId(String voucherGenerateId) {
        this.voucherGenerateId = voucherGenerateId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public FundRecordDTO() {
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
}