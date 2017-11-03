package com.bjike.goddess.market.excel;


import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 市场信息管理表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-21T11:00:01.548 ]
 * @Description: [ 市场信息管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MarketInfoExport {

    /**
     * 审核人
     */
    @ExcelHeader(name = "审核人", notNull = true)
    private String auditor;

    /**
     * 审核意见
     */
    @ExcelHeader(name = "审核意见", notNull = true)
    private String auditOpinion;

    /**
     * 市场信息编号
     */
    @ExcelHeader(name = "市场信息编号", notNull = true)
    private String marketInfoId;

    /**
     * 市场收集日期
     */
    @ExcelHeader(name = "市场收集日期", notNull = true)
    private String infoCollectionDate;

    /**
     * 行业
     */
    @ExcelHeader(name = "行业", notNull = true)
    private String workType;

    /**
     * 技术类别
     */
    @ExcelHeader(name = "技术类别", notNull = true)
    private String technologyCategory;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String projectName;

    /**
     * 规模
     */
    @ExcelHeader(name = "规模", notNull = true)
    private String scale;

    /**
     * 发起时间
     */
    @ExcelHeader(name = "发起时间", notNull = true)
    private String startTime;

    /**
     * 重要时间点
     */
    @ExcelHeader(name = "重要时间点", notNull = true)
    private String importantPoint;

    /**
     * 结束时间
     */
    @ExcelHeader(name = "结束时间", notNull = true)
    private String endTime;

    /**
     * 信息来源
     */
    @ExcelHeader(name = "信息来源", notNull = true)
    private String infoSource;

    /**
     * 市场信息收集人
     */
    @ExcelHeader(name = "市场信息收集人", notNull = true)
    private String marketInfoCollecting;

    /**
     * 是否为有效信息
     */
    @ExcelHeader(name = "是否为有效信息", notNull = true)
    private String effective;

    /**
     * 项目性质
     */
    @ExcelHeader(name = "项目性质", notNull = true)
    private String projectNature;

    /**
     * 市场分析人员
     */
    @ExcelHeader(name = "市场分析人员", notNull = true)
    private String marketAnalysts;

    /**
     * 配置要求
     */
    @ExcelHeader(name = "配置要求", notNull = true)
    private String configurationRequirement;

    /**
     * 技术要求
     */
    @ExcelHeader(name = "技术要求", notNull = true)
    private String technicalRequirement;

    /**
     * 资质要求
     */
    @ExcelHeader(name = "资质要求", notNull = true)
    private String qualificationRequirement;

    /**
     * 客户信息编号
     */
    @ExcelHeader(name = "客户信息编号", notNull = true)
    private String customerNum;

    /**
     * 客户名称
     */
    @ExcelHeader(name = "客户名称", notNull = true)
    private String customerName;

    /**
     * 组织机构名称
     */
    @ExcelHeader(name = "组织机构名称", notNull = true)
    private String origanizion;

    /**
     * 竞争对手名称
     */
    @ExcelHeader(name = "竞争对手名称", notNull = true)
    private String competitorsName;

    /**
     * 客户/竞争对手信息填写人
     */
    @ExcelHeader(name = "客户/竞争对手信息填写人", notNull = true)
    private String fillPerson;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = true)
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

    public String getInfoCollectionDate() {
        return infoCollectionDate;
    }

    public void setInfoCollectionDate(String infoCollectionDate) {
        this.infoCollectionDate = infoCollectionDate;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
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

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
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

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
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