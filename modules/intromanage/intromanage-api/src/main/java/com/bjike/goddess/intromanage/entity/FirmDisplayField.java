package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 公司简介显示字段
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 10:15 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_firmdisplayfield")
public class FirmDisplayField extends BaseEntity {

    /**
     * 是否显示公司名称
     */
    @Column(name = "ifShowFirmName", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示公司名称'")
    private Boolean ifShowFirmName;

    /**
     * 是否显示公司性质
     */
    @Column(name = "ifShowFirmNature", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示公司性质'")
    private Boolean ifShowFirmNature;

    /**
     * 是否显示注册资金
     */
    @Column(name = "ifShowRegisterMoney", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示注册资金'")
    private Boolean ifShowRegisterMoney;

    /**
     * 是否显示注册时间
     */
    @Column(name = "ifShowRegisterDate", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示注册时间'")
    private Boolean ifShowRegisterDate;

    /**
     * 是否显示公司精神
     */
    @Column(name = "ifShowFirmSpirit", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示公司精神'")
    private Boolean ifShowFirmSpirit;

    /**
     * 是否显示服务意识
     */
    @Column(name = "ifShowServiceAwareness", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示服务意识'")
    private Boolean ifShowServiceAwareness;

    /**
     * 是否显示公司宗旨
     */
    @Column(name = "ifShowFirmTenet", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示公司宗旨'")
    private Boolean ifShowFirmTenet;

    /**
     * 是否显示人才观
     */
    @Column(name = "ifShowTalentView", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示人才观'")
    private Boolean ifShowTalentView;

    /**
     * 是否显示经营观
     */
    @Column(name = "ifShowOperationView", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示经营观'")
    private Boolean ifShowOperationView;

    /**
     * 是否显示质量观
     */
    @Column(name = "ifShowQualityView", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示质量观'")
    private Boolean ifShowQualityView;

    /**
     * 是否显示组织结构
     */
    @Column(name = "ifShowOrganization", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示组织结构'")
    private Boolean ifShowOrganization;

    /**
     * 是否显示管理模式
     */
    @Column(name = "ifShowManageModel", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示管理模式'")
    private Boolean ifShowManageModel;

    /**
     * 是否显示服务团队介绍
     */
    @Column(name = "ifShowServiceTeamIntro", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示服务团队介绍'")
    private Boolean ifShowServiceTeamIntro;

    /**
     * 是否显示员工数量
     */
    @Column(name = "ifShowStaffNo", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示员工数量'")
    private Boolean ifShowStaffNo;

    /**
     * 是否显示囊括区域
     */
    @Column(name = "ifShowIncludeArea", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示囊括区域'")
    private Boolean ifShowIncludeArea;

    /**
     * 是否显示一体化解决方案
     */
    @Column(name = "ifShowSolvingScheme", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示一体化解决方案'")
    private Boolean ifShowSolvingScheme;

    /**
     * 是否显示需求类型
     */
    @Column(name = "ifShowDemandType", nullable = false, columnDefinition = "TINYINT(1) COMMENT '是否显示需求类型'")
    private Boolean ifShowDemandType;


    public Boolean getIfShowFirmName() {
        return ifShowFirmName;
    }

    public void setIfShowFirmName(Boolean ifShowFirmName) {
        this.ifShowFirmName = ifShowFirmName;
    }

    public Boolean getIfShowFirmNature() {
        return ifShowFirmNature;
    }

    public void setIfShowFirmNature(Boolean ifShowFirmNature) {
        this.ifShowFirmNature = ifShowFirmNature;
    }

    public Boolean getIfShowRegisterMoney() {
        return ifShowRegisterMoney;
    }

    public void setIfShowRegisterMoney(Boolean ifShowRegisterMoney) {
        this.ifShowRegisterMoney = ifShowRegisterMoney;
    }

    public Boolean getIfShowRegisterDate() {
        return ifShowRegisterDate;
    }

    public void setIfShowRegisterDate(Boolean ifShowRegisterDate) {
        this.ifShowRegisterDate = ifShowRegisterDate;
    }

    public Boolean getIfShowFirmSpirit() {
        return ifShowFirmSpirit;
    }

    public void setIfShowFirmSpirit(Boolean ifShowFirmSpirit) {
        this.ifShowFirmSpirit = ifShowFirmSpirit;
    }

    public Boolean getIfShowServiceAwareness() {
        return ifShowServiceAwareness;
    }

    public void setIfShowServiceAwareness(Boolean ifShowServiceAwareness) {
        this.ifShowServiceAwareness = ifShowServiceAwareness;
    }

    public Boolean getIfShowFirmTenet() {
        return ifShowFirmTenet;
    }

    public void setIfShowFirmTenet(Boolean ifShowFirmTenet) {
        this.ifShowFirmTenet = ifShowFirmTenet;
    }

    public Boolean getIfShowTalentView() {
        return ifShowTalentView;
    }

    public void setIfShowTalentView(Boolean ifShowTalentView) {
        this.ifShowTalentView = ifShowTalentView;
    }

    public Boolean getIfShowOperationView() {
        return ifShowOperationView;
    }

    public void setIfShowOperationView(Boolean ifShowOperationView) {
        this.ifShowOperationView = ifShowOperationView;
    }

    public Boolean getIfShowQualityView() {
        return ifShowQualityView;
    }

    public void setIfShowQualityView(Boolean ifShowQualityView) {
        this.ifShowQualityView = ifShowQualityView;
    }

    public Boolean getIfShowOrganization() {
        return ifShowOrganization;
    }

    public void setIfShowOrganization(Boolean ifShowOrganization) {
        this.ifShowOrganization = ifShowOrganization;
    }

    public Boolean getIfShowManageModel() {
        return ifShowManageModel;
    }

    public void setIfShowManageModel(Boolean ifShowManageModel) {
        this.ifShowManageModel = ifShowManageModel;
    }

    public Boolean getIfShowServiceTeamIntro() {
        return ifShowServiceTeamIntro;
    }

    public void setIfShowServiceTeamIntro(Boolean ifShowServiceTeamIntro) {
        this.ifShowServiceTeamIntro = ifShowServiceTeamIntro;
    }

    public Boolean getIfShowStaffNo() {
        return ifShowStaffNo;
    }

    public void setIfShowStaffNo(Boolean ifShowStaffNo) {
        this.ifShowStaffNo = ifShowStaffNo;
    }

    public Boolean getIfShowIncludeArea() {
        return ifShowIncludeArea;
    }

    public void setIfShowIncludeArea(Boolean ifShowIncludeArea) {
        this.ifShowIncludeArea = ifShowIncludeArea;
    }

    public Boolean getIfShowSolvingScheme() {
        return ifShowSolvingScheme;
    }

    public void setIfShowSolvingScheme(Boolean ifShowSolvingScheme) {
        this.ifShowSolvingScheme = ifShowSolvingScheme;
    }

    public Boolean getIfShowDemandType() {
        return ifShowDemandType;
    }

    public void setIfShowDemandType(Boolean ifShowDemandType) {
        this.ifShowDemandType = ifShowDemandType;
    }
}