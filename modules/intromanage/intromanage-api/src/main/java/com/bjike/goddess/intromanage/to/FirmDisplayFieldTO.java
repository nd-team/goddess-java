package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 公司简介显示字段
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-06 10:15 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirmDisplayFieldTO extends BaseTO {

    /**
     * 是否显示公司名称
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示公司名称不能为空")
    private Boolean ifShowFirmName;

    /**
     * 是否显示公司性质
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示公司性质不能为空")
    private Boolean ifShowFirmNature;

    /**
     * 是否显示注册资金
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示注册资金不能为空")
    private Boolean ifShowRegisterMoney;

    /**
     * 是否显示注册时间
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示注册时间不能为空")
    private Boolean ifShowRegisterDate;

    /**
     * 是否显示公司精神
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示公司精神不能为空")
    private Boolean ifShowFirmSpirit;

    /**
     * 是否显示服务意识
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示服务意识不能为空")
    private Boolean ifShowServiceAwareness;

    /**
     * 是否显示公司宗旨
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示公司宗旨不能为空")
    private Boolean ifShowFirmTenet;

    /**
     * 是否显示人才观
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示人才观不能为空")
    private Boolean ifShowTalentView;

    /**
     * 是否显示经营观
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示经营观不能为空")
    private Boolean ifShowOperationView;

    /**
     * 是否显示质量观
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示质量观不能为空")
    private Boolean ifShowQualityView;

    /**
     * 是否显示组织结构
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示组织结构不能为空")
    private Boolean ifShowOrganization;

    /**
     * 是否显示管理模式
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示管理模式不能为空")
    private Boolean ifShowManageModel;

    /**
     * 是否显示服务团队介绍
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示服务团队介绍不能为空")
    private Boolean ifShowServiceTeamIntro;

    /**
     * 是否显示员工数量
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示员工数量不能为空")
    private Boolean ifShowStaffNo;

    /**
     * 是否显示囊括区域
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示囊括区域不能为空")
    private Boolean ifShowIncludeArea;

    /**
     * 是否显示一体化解决方案
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示一体化解决方案不能为空")
    private Boolean ifShowSolvingScheme;

    /**
     * 是否显示需求类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "是否显示需求类型不能为空")
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