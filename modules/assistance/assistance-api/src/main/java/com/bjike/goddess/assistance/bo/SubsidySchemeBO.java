package com.bjike.goddess.assistance.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 公司补助方案业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubsidySchemeBO extends BaseBO {

    /**
     * 制作方案时间
     */
    private String makeSchemeDate;

    /**
     * 方案序号
     */
    private Integer schemeSeq;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 补助名称
     */
    private String subsidyName;

    /**
     * 补助目的
     */
    private String subsidyPurpose;

    /**
     * 补助适用地区
     */
    private String subsidyApplyArea;

    /**
     * 补助适用项目组/部门
     */
    private String subsidyApplyDepart;

    /**
     * 补助对象
     */
    private String subsidyObject;

    /**
     * 补助开通时间
     */
    private String subsidyOpenDate;

    /**
     * 补助结束时间
     */
    private String subsidyEndDate;

    /**
     * 补助条件
     */
    private String subsidyConditions;

    /**
     * 条件依据明细
     */
    private String conditionsDetail;

    /**
     * 数据来源
     */
    private String dateSource;

    /**
     * 补助内容
     */
    private String schemeContent;

    /**
     * 补助单价/天
     */
    private String schemePrice;

    /**
     * 补助发放形式
     */
    private String schemeIssue;
    /**
     * 补助发放时间
     */
    private String schemeIssueDate;
    /**
     * 总经办意见
     */
    private String manageOpinion;

    /**
     * 是否可以实施
     */
    private Boolean implement;

    /**
     * 备注
     */
    private String remark;

    public String getMakeSchemeDate() {
        return makeSchemeDate;
    }

    public void setMakeSchemeDate(String makeSchemeDate) {
        this.makeSchemeDate = makeSchemeDate;
    }

    public Integer getSchemeSeq() {
        return schemeSeq;
    }

    public void setSchemeSeq(Integer schemeSeq) {
        this.schemeSeq = schemeSeq;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSubsidyName() {
        return subsidyName;
    }

    public void setSubsidyName(String subsidyName) {
        this.subsidyName = subsidyName;
    }

    public String getSubsidyPurpose() {
        return subsidyPurpose;
    }

    public void setSubsidyPurpose(String subsidyPurpose) {
        this.subsidyPurpose = subsidyPurpose;
    }

    public String getSubsidyApplyArea() {
        return subsidyApplyArea;
    }

    public void setSubsidyApplyArea(String subsidyApplyArea) {
        this.subsidyApplyArea = subsidyApplyArea;
    }

    public String getSubsidyApplyDepart() {
        return subsidyApplyDepart;
    }

    public void setSubsidyApplyDepart(String subsidyApplyDepart) {
        this.subsidyApplyDepart = subsidyApplyDepart;
    }

    public String getSubsidyObject() {
        return subsidyObject;
    }

    public void setSubsidyObject(String subsidyObject) {
        this.subsidyObject = subsidyObject;
    }

    public String getSubsidyOpenDate() {
        return subsidyOpenDate;
    }

    public void setSubsidyOpenDate(String subsidyOpenDate) {
        this.subsidyOpenDate = subsidyOpenDate;
    }

    public String getSubsidyEndDate() {
        return subsidyEndDate;
    }

    public void setSubsidyEndDate(String subsidyEndDate) {
        this.subsidyEndDate = subsidyEndDate;
    }

    public String getSubsidyConditions() {
        return subsidyConditions;
    }

    public void setSubsidyConditions(String subsidyConditions) {
        this.subsidyConditions = subsidyConditions;
    }

    public String getConditionsDetail() {
        return conditionsDetail;
    }

    public void setConditionsDetail(String conditionsDetail) {
        this.conditionsDetail = conditionsDetail;
    }

    public String getDateSource() {
        return dateSource;
    }

    public void setDateSource(String dateSource) {
        this.dateSource = dateSource;
    }

    public String getSchemeContent() {
        return schemeContent;
    }

    public void setSchemeContent(String schemeContent) {
        this.schemeContent = schemeContent;
    }

    public String getSchemePrice() {
        return schemePrice;
    }

    public void setSchemePrice(String schemePrice) {
        this.schemePrice = schemePrice;
    }

    public String getSchemeIssue() {
        return schemeIssue;
    }

    public void setSchemeIssue(String schemeIssue) {
        this.schemeIssue = schemeIssue;
    }

    public String getSchemeIssueDate() {
        return schemeIssueDate;
    }

    public void setSchemeIssueDate(String schemeIssueDate) {
        this.schemeIssueDate = schemeIssueDate;
    }

    public String getManageOpinion() {
        return manageOpinion;
    }

    public void setManageOpinion(String manageOpinion) {
        this.manageOpinion = manageOpinion;
    }

    public Boolean getImplement() {
        return implement;
    }

    public void setImplement(Boolean implement) {
        this.implement = implement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}