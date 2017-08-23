package com.bjike.goddess.market.vo;


import com.bjike.goddess.market.enums.MarketProjectNature;
import com.bjike.goddess.market.enums.MarketWorkType;
import com.bjike.goddess.market.enums.Scale;

/**
 * 市场信息管理表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.548 ]
 * @Description: [ 市场信息管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketInfoVO {

    /**
     * id
     */
    private String id;
    /**
     * 审核人
     */
    private String auditor;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 市场信息编号
     */
    private String marketInfoId;

    /**
     * 市场收集日期
     */
    private String infoCollectionDate;

    /**
     * 行业
     */
    private MarketWorkType workType;

    /**
     * 技术类别
     */
    private String technologyCategory;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 规模
     */
    private Scale scale;

    /**
     * 发起时间
     */
    private String startTime;

    /**
     * 重要时间点
     */
    private String importantPoint;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 信息来源
     */
    private String infoSource;

    /**
     * 市场信息收集人
     */
    private String marketInfoCollecting;

    /**
     * 是否为有效信息(0无，1有)
     */
    private Boolean effective;

    /**
     * 项目性质
     */
    private MarketProjectNature projectNature;

    /**
     * 市场分析人员
     */
    private String marketAnalysts;

    /**
     * 配置要求
     */
    private String configurationRequirement;

    /**
     * 技术要求
     */
    private String technicalRequirement;

    /**
     * 资质要求
     */
    private String qualificationRequirement;

    /**
     * 客户信息编号
     */
    private String customerNum;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 组织机构名称
     */
    private String origanizion;

    /**
     * 竞争对手名称
     */
    private String competitorsName;

    /**
     * 客户/竞争对手信息填写人
     */
    private String fillPerson;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getInfoCollectionDate() {
        return infoCollectionDate;
    }

    public void setInfoCollectionDate(String infoCollectionDate) {
        this.infoCollectionDate = infoCollectionDate;
    }

    public MarketWorkType getWorkType() {
        return workType;
    }

    public void setWorkType(MarketWorkType workType) {
        this.workType = workType;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getImportantPoint() {
        return importantPoint;
    }

    public void setImportantPoint(String importantPoint) {
        this.importantPoint = importantPoint;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public MarketProjectNature getProjectNature() {
        return projectNature;
    }

    public void setProjectNature(MarketProjectNature projectNature) {
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