package com.bjike.goddess.competitorsmanagement.vo;

import com.bjike.goddess.competitorsmanagement.entity.OrganizationSD;

/**
 * 竞争对手管理表现层对象
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-13 09:45 ]
 * @Description: [ 竞争对手管理表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompetitorVO {

    /**
     * id
     */
    private String id;
    /**
     * 竞争对手收集时间
     */
    private String competitorCollectTime;

    /**
     * 竞争对手来源
     */
    private String competitorSource;

    /**
     * 市场信息编号
     */
    private String marketInfoNum;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 地区
     */
    private String area;

    /**
     * 竞争对手公司名称
     */
    private String rivalCompany;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 地址
     */
    private String address;

    /**
     * 组织结构
     */
    private String organizationStructure;

    /**
     * 影响区域
     */
    private String affectedArea;

    /**
     * 影响区域数
     */
    private Integer affectedAreaNum;

    /**
     * 影响业务
     */
    private String impactOnBusiness;

    /**
     * 影响业务数
     */
    private Integer impactOnBusinessNum;

    /**
     * 信息收集人
     */
    private String informationCollector;

    /**
     * 客户信息编号
     */
    private String customerNum;

    /**
     * 相关联系人
     */
    private String relatedContact;

    /**
     * 电话/座机
     */
    private String phone;

    /**
     * 职务/权职
     */
    private String workRight;

    /**
     * 竞争对手动态
     */
    private String competitorDynamics;

    /**
     * 备注
     */
    private String note;

    /**
     * 竞争对手信息是否完善
     */
    private Boolean competitorInformation;

    /**
     * 组织结构外键
     */
    private OrganizationSD organizationSD;


    public OrganizationSD getOrganizationSD() {
        return organizationSD;
    }

    public void setOrganizationSD(OrganizationSD organizationSD) {
        this.organizationSD = organizationSD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompetitorCollectTime() {
        return competitorCollectTime;
    }

    public void setCompetitorCollectTime(String competitorCollectTime) {
        this.competitorCollectTime = competitorCollectTime;
    }

    public String getCompetitorSource() {
        return competitorSource;
    }

    public void setCompetitorSource(String competitorSource) {
        this.competitorSource = competitorSource;
    }

    public String getMarketInfoNum() {
        return marketInfoNum;
    }

    public void setMarketInfoNum(String marketInfoNum) {
        this.marketInfoNum = marketInfoNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRivalCompany() {
        return rivalCompany;
    }

    public void setRivalCompany(String rivalCompany) {
        this.rivalCompany = rivalCompany;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganizationStructure() {
        return organizationStructure;
    }

    public void setOrganizationStructure(String organizationStructure) {
        this.organizationStructure = organizationStructure;
    }

    public String getAffectedArea() {
        return affectedArea;
    }

    public void setAffectedArea(String affectedArea) {
        this.affectedArea = affectedArea;
    }

    public Integer getAffectedAreaNum() {
        return affectedAreaNum;
    }

    public void setAffectedAreaNum(Integer affectedAreaNum) {
        this.affectedAreaNum = affectedAreaNum;
    }

    public String getImpactOnBusiness() {
        return impactOnBusiness;
    }

    public void setImpactOnBusiness(String impactOnBusiness) {
        this.impactOnBusiness = impactOnBusiness;
    }

    public Integer getImpactOnBusinessNum() {
        return impactOnBusinessNum;
    }

    public void setImpactOnBusinessNum(Integer impactOnBusinessNum) {
        this.impactOnBusinessNum = impactOnBusinessNum;
    }

    public String getInformationCollector() {
        return informationCollector;
    }

    public void setInformationCollector(String informationCollector) {
        this.informationCollector = informationCollector;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getRelatedContact() {
        return relatedContact;
    }

    public void setRelatedContact(String relatedContact) {
        this.relatedContact = relatedContact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkRight() {
        return workRight;
    }

    public void setWorkRight(String workRight) {
        this.workRight = workRight;
    }

    public String getCompetitorDynamics() {
        return competitorDynamics;
    }

    public void setCompetitorDynamics(String competitorDynamics) {
        this.competitorDynamics = competitorDynamics;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getCompetitorInformation() {
        return competitorInformation;
    }

    public void setCompetitorInformation(Boolean competitorInformation) {
        this.competitorInformation = competitorInformation;
    }
}