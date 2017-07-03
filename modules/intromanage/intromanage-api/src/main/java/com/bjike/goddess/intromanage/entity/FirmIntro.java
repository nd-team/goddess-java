package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.intromanage.type.DemandType;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 公司简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_firmintro")
public class FirmIntro extends BaseEntity {

    /**
     * 公司名称
     */
    @Column(name = "firmName", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司名称'")
    private String firmName;

    /**
     * 公司性质
     */
    @Column(name = "firmNature", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司性质'")
    private String firmNature;

    /**
     * 注册资金
     */
    @Column(name = "registerMoney", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '注册资金'")
    private String registerMoney;

    /**
     * 注册时间
     */
    @Column(name = "registerDate", nullable = false, columnDefinition = "DATE COMMENT '注册时间'")
    private LocalDate registerDate;

    /**
     * 公司精神
     */
    @Column(name = "firmSpirit", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司精神'")
    private String firmSpirit;

    /**
     * 服务意识
     */
    @Column(name = "serviceAwareness", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '服务意识'")
    private String serviceAwareness;

    /**
     * 公司宗旨
     */
    @Column(name = "firmTenet", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '公司宗旨'")
    private String firmTenet;

    /**
     * 人才观
     */
    @Column(name = "talentView", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '人才观'")
    private String talentView;

    /**
     * 经营观
     */
    @Column(name = "operationView", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '经营观'")
    private String operationView;

    /**
     * 质量观
     */
    @Column(name = "qualityView", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '质量观'")
    private String qualityView;

    /**
     * 组织结构
     */
    @Column(name = "organization", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '组织结构'")
    private String organization;

    /**
     * 管理模式
     */
    @Column(name = "manageModel", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '管理模式'")
    private String manageModel;

    /**
     * 服务团队介绍
     */
    @Column(name = "serviceTeamIntro", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '服务团队介绍'")
    private String serviceTeamIntro;

    /**
     * 员工数量
     */
    @Column(name = "staffNo", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '员工数量'")
    private String staffNo;

    /**
     * 囊括区域
     */
    @Column(name = "includeArea", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '囊括区域'")
    private String includeArea;

    /**
     * 一体化解决方案
     */
    @Column(name = "solvingScheme", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '一体化解决方案'")
    private String solvingScheme;

    /**
     * 需求类型
     */
    @Column(name = "demandType", nullable = false, columnDefinition = "TINYINT(2) COMMENT '需求类型'")
    private DemandType demandType;

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmNature() {
        return firmNature;
    }

    public void setFirmNature(String firmNature) {
        this.firmNature = firmNature;
    }

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public String getFirmSpirit() {
        return firmSpirit;
    }

    public void setFirmSpirit(String firmSpirit) {
        this.firmSpirit = firmSpirit;
    }

    public String getServiceAwareness() {
        return serviceAwareness;
    }

    public void setServiceAwareness(String serviceAwareness) {
        this.serviceAwareness = serviceAwareness;
    }

    public String getFirmTenet() {
        return firmTenet;
    }

    public void setFirmTenet(String firmTenet) {
        this.firmTenet = firmTenet;
    }

    public String getTalentView() {
        return talentView;
    }

    public void setTalentView(String talentView) {
        this.talentView = talentView;
    }

    public String getOperationView() {
        return operationView;
    }

    public void setOperationView(String operationView) {
        this.operationView = operationView;
    }

    public String getQualityView() {
        return qualityView;
    }

    public void setQualityView(String qualityView) {
        this.qualityView = qualityView;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getManageModel() {
        return manageModel;
    }

    public void setManageModel(String manageModel) {
        this.manageModel = manageModel;
    }

    public String getServiceTeamIntro() {
        return serviceTeamIntro;
    }

    public void setServiceTeamIntro(String serviceTeamIntro) {
        this.serviceTeamIntro = serviceTeamIntro;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getIncludeArea() {
        return includeArea;
    }

    public void setIncludeArea(String includeArea) {
        this.includeArea = includeArea;
    }

    public String getSolvingScheme() {
        return solvingScheme;
    }

    public void setSolvingScheme(String solvingScheme) {
        this.solvingScheme = solvingScheme;
    }

    public DemandType getDemandType() {
        return demandType;
    }

    public void setDemandType(DemandType demandType) {
        this.demandType = demandType;
    }
}