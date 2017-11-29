package com.bjike.goddess.assistance.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 公司补助方案
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-16 10:30 ]
 * @Description: [ 公司补助方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "assistance_subsidyscheme")
public class SubsidyScheme extends BaseEntity {

    /**
     * 制作方案时间
     */
    @Column(name = "makeSchemeDate", nullable = false, columnDefinition = "DATE   COMMENT '制作方案时间'")
    private LocalDate makeSchemeDate;

    /**
     * 方案序号
     */
    @Column(name = "", nullable = false, columnDefinition = "INT(11)   COMMENT '方案序号'")
    private Integer schemeSeq;

    /**
     * 方案名称
     */
    @Column(name = "schemeName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String schemeName;

    /**
     * 补助名称
     */
    @Column(name = "subsidyName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助名称'")
    private String subsidyName;

    /**
     * 补助目的
     */
    @Column(name = "subsidyPurpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助目的'")
    private String subsidyPurpose;

    /**
     *补助适用地区
     */
    @Column(name = "subsidyApplyArea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT ''")
    private String subsidyApplyArea;

    /**
     * 补助适用项目组/部门
     */
    @Column(name = "subsidyApplyDepart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助适用项目组/部门'")
    private String subsidyApplyDepart;

    /**
     * 补助对象
     */
    @Column(name = "subsidyObject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助对象'")
    private String subsidyObject;

    /**
     * 补助开通时间
     */
    @Column(name = "subsidyOpenDate", nullable = false, columnDefinition = "DATE   COMMENT '补助开通时间'")
    private LocalDate subsidyOpenDate;

    /**
     * 补助结束时间
     */
    @Column(name = "subsidyEndDate", columnDefinition = "DATE   COMMENT '补助结束时间'")
    private LocalDate subsidyEndDate;

    /**
     * 补助条件
     */
    @Column(name = "subsidyConditions", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助条件'")
    private String subsidyConditions;

    /**
     * 条件依据明细
     */
    @Column(name = "conditionsDetail", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '条件依据明细'")
    private String conditionsDetail;

    /**
     * 数据来源
     */
    @Column(name = "dateSource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '数据来源'")
    private String dateSource;

    /**
     * 补助内容
     */
    @Column(name = "schemeContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助内容'")
    private String schemeContent;

    /**
     * 补助单价/天
     */
    @Column(name = "schemePrice", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '补助单价/天'")
    private Double schemePrice;

    /**
     * 补助发放形式
     */
    @Column(name = "schemeIssue", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助发放形式'")
    private String schemeIssue;
    /**
     * 补助发放时间
     */
    @Column(name = "schemeIssueDate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '补助发放时间'")
    private String schemeIssueDate;

    /**
     * 总经办意见
     */
    @Column(name = "manageOpinion", columnDefinition = "VARCHAR(255)   COMMENT '总经办意见'")
    private String manageOpinion;

    /**
     * 是否可以实施
     */
    @Column(name = "is_implement", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否可以实施'", insertable = false)
    private Boolean implement;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public LocalDate getMakeSchemeDate() {
        return makeSchemeDate;
    }

    public void setMakeSchemeDate(LocalDate makeSchemeDate) {
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

    public LocalDate getSubsidyOpenDate() {
        return subsidyOpenDate;
    }

    public void setSubsidyOpenDate(LocalDate subsidyOpenDate) {
        this.subsidyOpenDate = subsidyOpenDate;
    }

    public LocalDate getSubsidyEndDate() {
        return subsidyEndDate;
    }

    public void setSubsidyEndDate(LocalDate subsidyEndDate) {
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

    public Double getSchemePrice() {
        return schemePrice;
    }

    public void setSchemePrice(Double schemePrice) {
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