package com.bjike.goddess.competitorsmanagement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 竞争对手管理
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-13 09:45 ]
 * @Description: [ 竞争对手管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "competitorsmanagement_competitor")
public class Competitor extends BaseEntity {

    /**
     * 竞争对手收集时间
     */
    @Column(name = "competitorCollectTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争对手收集时间'")
    private LocalDate competitorCollectTime;

    /**
     * 竞争对手来源
     */
    @Column(name = "competitorSource", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争对手来源'")
    private String competitorSource;

    /**
     * 市场信息编号
     */
    @Column(name = "marketInfoNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '市场信息编号'")
    private String marketInfoNum;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 竞争对手公司名称
     */
    @Column(name = "rivalCompany", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争对手公司名称'")
    private String rivalCompany;

    /**
     * 业务类型
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 地址
     */
    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地址'")
    private String address;

    /**
     * 组织结构
     */
    @Column(name = "organizationStructure", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '组织结构'")
    private String organizationStructure;

    /**
     * 影响区域
     */
    @Column(name = "affectedArea", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '影响区域'")
    private String affectedArea;

    /**
     * 影响区域数
     */
    @Column(name = "affectedAreaNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '影响区域数'")
    private Integer affectedAreaNum;

    /**
     * 影响业务
     */
    @Column(name = "impactOnBusiness", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '影响业务'")
    private String impactOnBusiness;

    /**
     * 影响业务数
     */
    @Column(name = "impactOnBusinessNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '影响业务数'")
    private Integer impactOnBusinessNum;

    /**
     * 信息收集人
     */
    @Column(name = "informationCollector", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '信息收集人'")
    private String informationCollector;

    /**
     * 客户信息编号
     */
    @Column(name = "customerNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户信息编号'")
    private String customerNum;

    /**
     * 相关联系人
     */
    @Column(name = "relatedContact", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '相关联系人'")
    private String relatedContact;

    /**
     * 电话/座机
     */
    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '电话/座机'")
    private String phone;

    /**
     * 职务/权职
     */
    @Column(name = "workRight", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职务/权职'")
    private String workRight;

    /**
     * 竞争对手动态
     */
    @Column(name = "competitorDynamics", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争对手动态'")
    private String competitorDynamics;

    /**
     * 备注
     */
    @Column(name = "note", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String note;

    /**
     * 竞争对手信息是否完善
     */
    @Column(name = "is_competitorInformation", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '竞争对手信息是否完善'", insertable = false)
    private Boolean competitorInformation;

    @Embedded
    private OrganizationSD organizationSD;

    public OrganizationSD getOrganizationSD() {
        return organizationSD;
    }

    public void setOrganizationSD(OrganizationSD organizationSD) {
        this.organizationSD = organizationSD;
    }

    public LocalDate getCompetitorCollectTime() {
        return competitorCollectTime;
    }

    public void setCompetitorCollectTime(LocalDate competitorCollectTime) {
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

    @Override
    public String toString() {
        return "Competitor{" +
                "competitorCollectTime=" + competitorCollectTime +
                ", competitorSource='" + competitorSource + '\'' +
                ", marketInfoNum='" + marketInfoNum + '\'' +
                ", projectName='" + projectName + '\'' +
                ", area='" + area + '\'' +
                ", rivalCompany='" + rivalCompany + '\'' +
                ", businessType='" + businessType + '\'' +
                ", address='" + address + '\'' +
                ", organizationStructure='" + organizationStructure + '\'' +
                ", affectedArea='" + affectedArea + '\'' +
                ", affectedAreaNum=" + affectedAreaNum +
                ", impactOnBusiness='" + impactOnBusiness + '\'' +
                ", impactOnBusinessNum=" + impactOnBusinessNum +
                ", informationCollector='" + informationCollector + '\'' +
                ", customerNum='" + customerNum + '\'' +
                ", relatedContact='" + relatedContact + '\'' +
                ", phone='" + phone + '\'' +
                ", workRight='" + workRight + '\'' +
                ", competitorDynamics='" + competitorDynamics + '\'' +
                ", note='" + note + '\'' +
                ", competitorInformation=" + competitorInformation +
                ", organizationSD=" + organizationSD +
                '}';
    }
}