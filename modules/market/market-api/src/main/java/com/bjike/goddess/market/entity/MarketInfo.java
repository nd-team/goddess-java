package com.bjike.goddess.market.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.market.enums.Scale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 市场信息管理
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.541 ]
 * @Description: [ 市场信息管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "market_marketinfo")
public class MarketInfo extends BaseEntity {

    /**
     * 审核人
     */
    @Column(name = "auditor", columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String auditor;

    /**
     * 审核意见
     */
    @Column(name = "auditOpinion", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String auditOpinion;

    /**
     * 市场信息编号
     */
    @Column(name = "marketInfoId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场信息编号'")
    private String marketInfoId;

    /**
     * 市场收集日期
     */
    @Column(name = "infoCollectionDate", columnDefinition = "DATE   COMMENT '市场收集日期'")
    private LocalDate infoCollectionDate;

    /**
     * 行业
     */
    @Column(name = "workProfession", columnDefinition = "VARCHAR(255)   COMMENT '行业'")
    private String workProfession;

    /**
     * 技术类别
     */
    @Column(name = "technologyCategory", columnDefinition = "VARCHAR(255)   COMMENT '技术类别'")
    private String technologyCategory;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 规模
     */
    @Column(name = "scale", columnDefinition = "INT(2)   COMMENT '规模'")
    private Scale scale;

    /**
     * 发起时间
     */
    @Column(name = "startTime", columnDefinition = "DATETIME   COMMENT '发起时间'")
    private LocalDateTime startTime;

    /**
     * 重要时间点
     */
    @Column(name = "importantPoint", nullable = false, columnDefinition = "DATETIME   COMMENT '重要时间点'")
    private LocalDateTime importantPoint;

    /**
     * 结束时间
     */
    @Column(name = "endTime", columnDefinition = "DATETIME   COMMENT '结束时间'")
    private LocalDateTime endTime;

    /**
     * 信息来源
     */
    @Column(name = "infoSource", columnDefinition = "VARCHAR(255)   COMMENT '信息来源'")
    private String infoSource;

    /**
     * 市场信息收集人
     */
    @Column(name = "marketInfoCollecting", columnDefinition = "VARCHAR(255)   COMMENT '市场信息收集人'")
    private String marketInfoCollecting;

    /**
     * 是否为有效信息(0是，1否)
     */
    @Column(name = "is_effective", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否为有效信息(0是，1否)'")
    private Boolean effective;

    /**
     * 项目性质
     */
    @Column(name = "projectNature", columnDefinition = "VARCHAR(255)   COMMENT '项目性质'")
    private String projectNature;

    /**
     * 市场分析人员
     */
    @Column(name = "marketAnalysts", columnDefinition = "VARCHAR(255)   COMMENT '市场分析人员'")
    private String marketAnalysts;

    /**
     * 配置要求
     */
    @Column(name = "configurationRequirement", columnDefinition = "VARCHAR(255)   COMMENT '配置要求'")
    private String configurationRequirement;

    /**
     * 技术要求
     */
    @Column(name = "technicalRequirement", columnDefinition = "VARCHAR(255)   COMMENT '技术要求'")
    private String technicalRequirement;

    /**
     * 资质要求
     */
    @Column(name = "qualificationRequirement", columnDefinition = "VARCHAR(255)   COMMENT '资质要求'")
    private String qualificationRequirement;

    /**
     * 客户信息编号
     */
    @Column(name = "customerNum", columnDefinition = "VARCHAR(255)   COMMENT '客户信息编号'")
    private String customerNum;

    /**
     * 客户名称
     */
    @Column(name = "customerName", columnDefinition = "VARCHAR(255)   COMMENT '客户名称'")
    private String customerName;

    /**
     * 组织机构名称
     */
    @Column(name = "origanizion", columnDefinition = "VARCHAR(255)   COMMENT '组织机构名称'")
    private String origanizion;

    /**
     * 竞争对手名称
     */
    @Column(name = "competitorsName", columnDefinition = "VARCHAR(255)   COMMENT '竞争对手名称'")
    private String competitorsName;

    /**
     * 客户/竞争对手信息填写人
     */
    @Column(name = "fillPerson", columnDefinition = "VARCHAR(255)   COMMENT '客户/竞争对手信息填写人'")
    private String fillPerson;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getMarketInfoId() {
        return marketInfoId;
    }

    public void setMarketInfoId(String marketInfoId) {
        this.marketInfoId = marketInfoId;
    }

    public LocalDate getInfoCollectionDate() {
        return infoCollectionDate;
    }

    public void setInfoCollectionDate(LocalDate infoCollectionDate) {
        this.infoCollectionDate = infoCollectionDate;
    }

    public String getWorkProfession() {
        return workProfession;
    }

    public void setWorkProfession(String workProfession) {
        this.workProfession = workProfession;
    }

    public String getTechnologyCategory() {
        return technologyCategory;
    }

    public void setTechnologyCategory(String technologyCategory) {
        this.technologyCategory = technologyCategory;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getImportantPoint() {
        return importantPoint;
    }

    public void setImportantPoint(LocalDateTime importantPoint) {
        this.importantPoint = importantPoint;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getMarketInfoCollecting() {
        return marketInfoCollecting;
    }

    public void setMarketInfoCollecting(String marketInfoCollecting) {
        this.marketInfoCollecting = marketInfoCollecting;
    }

    public Boolean getEffective() {
        return effective;
    }

    public void setEffective(Boolean effective) {
        this.effective = effective;
    }

    public String getProjectNature() {
        return projectNature;
    }

    public void setProjectNature(String projectNature) {
        this.projectNature = projectNature;
    }

    public String getMarketAnalysts() {
        return marketAnalysts;
    }

    public void setMarketAnalysts(String marketAnalysts) {
        this.marketAnalysts = marketAnalysts;
    }

    public String getConfigurationRequirement() {
        return configurationRequirement;
    }

    public void setConfigurationRequirement(String configurationRequirement) {
        this.configurationRequirement = configurationRequirement;
    }

    public String getTechnicalRequirement() {
        return technicalRequirement;
    }

    public void setTechnicalRequirement(String technicalRequirement) {
        this.technicalRequirement = technicalRequirement;
    }

    public String getQualificationRequirement() {
        return qualificationRequirement;
    }

    public void setQualificationRequirement(String qualificationRequirement) {
        this.qualificationRequirement = qualificationRequirement;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOriganizion() {
        return origanizion;
    }

    public void setOriganizion(String origanizion) {
        this.origanizion = origanizion;
    }

    public String getCompetitorsName() {
        return competitorsName;
    }

    public void setCompetitorsName(String competitorsName) {
        this.competitorsName = competitorsName;
    }

    public String getFillPerson() {
        return fillPerson;
    }

    public void setFillPerson(String fillPerson) {
        this.fillPerson = fillPerson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}