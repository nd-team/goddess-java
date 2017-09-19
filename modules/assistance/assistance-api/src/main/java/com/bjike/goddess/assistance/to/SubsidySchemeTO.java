package com.bjike.goddess.assistance.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 公司补助方案
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SubsidySchemeTO extends BaseTO {

    public interface testAudit{}
    /**
     * 制作方案时间
     */
    @NotBlank(message = "制作方案时间不能为空",groups = {ADD.class, EDIT.class})
    private String makeSchemeDate;

    /**
     * 方案序号
     */
    @NotNull(message = "方案序号不能为空",groups = {ADD.class, EDIT.class})
    private Integer schemeSeq;

    /**
     * 方案名称
     */
    @NotBlank(message = "方案名称不能为空",groups = {ADD.class, EDIT.class})
    private String schemeName;

    /**
     * 补助名称
     */
    @NotBlank(message = "补助名称不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyName;

    /**
     * 补助目的
     */
    @NotBlank(message = "补助目的不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyPurpose;

    /**
     * 补助适用地区
     */
    @NotBlank(message = "补助适用地区不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyApplyArea;

    /**
     * 补助适用项目组/部门
     */
    @NotBlank(message = "补助适用项目组不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyApplyDepart;

    /**
     * 补助对象
     */
    @NotBlank(message = "补助对象不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyObject;

    /**
     * 补助开通时间
     */
    @NotBlank(message = "补助开通时间不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyOpenDate;

    /**
     * 补助结束时间
     */
    @NotBlank(message = "补助结束时间不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyEndDate;

    /**
     * 补助条件
     */
    @NotBlank(message = "补助条件不能为空",groups = {ADD.class, EDIT.class})
    private String subsidyConditions;

    /**
     * 条件依据明细
     */
    @NotBlank(message = "条件依据明细不能为空",groups = {ADD.class, EDIT.class})
    private String conditionsDetail;

    /**
     * 数据来源
     */
    @NotBlank(message = "数据来源不能为空",groups = {ADD.class, EDIT.class})
    private String dateSource;

    /**
     * 补助内容
     */
    @NotBlank(message = "补助内容不能为空",groups = {ADD.class, EDIT.class})
    private String schemeContent;

    /**
     * 补助单价/天
     */
    @NotBlank(message = "补助单价不能为空",groups = {ADD.class, EDIT.class})
    private String schemePrice;

    /**
     * 补助发放形式
     */
    @NotBlank(message = "补助发放形式不能为空",groups = {ADD.class, EDIT.class})
    private String schemeIssue;
    /**
     * 补助发放时间
     */
    @NotBlank(message = "补助发放时间不能为空",groups = {ADD.class, EDIT.class})
    private String schemeIssueDate;
    /**
     * 总经办意见
     */
    @NotBlank(message = "总经办意见不能为空",groups = {SubsidySchemeTO.testAudit.class})
    private String manageOpinion;

    /**
     * 是否可以实施
     */
    @NotNull(message = "是否可以实施不能为空",groups = {SubsidySchemeTO.testAudit.class})
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