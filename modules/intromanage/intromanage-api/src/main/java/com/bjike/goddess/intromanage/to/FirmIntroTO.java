package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.intromanage.type.DemandType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 公司简介
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirmIntroTO extends BaseTO {

    /**
     * 公司名称
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "公司名称不能为空")
    private String firmName;

    /**
     * 公司性质
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "公司性质不能为空")
    private String firmNature;

    /**
     * 注册资金
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "注册资金不能为空")
    private String registerMoney;

    /**
     * 注册时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "注册时间不能为空")
    private String registerDate;

    /**
     * 公司精神
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "公司精神不能为空")
    private String firmSpirit;

    /**
     * 服务意识
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "服务意识不能为空")
    private String serviceAwareness;

    /**
     * 公司宗旨
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "公司宗旨不能为空")
    private String firmTenet;

    /**
     * 人才观
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "人才观不能为空")
    private String talentView;

    /**
     * 经营观
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "经营观不能为空")
    private String operationView;

    /**
     * 质量观
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "质量观不能为空")
    private String qualityView;

    /**
     * 组织结构
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "组织结构不能为空")
    private String organization;

    /**
     * 管理模式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "管理模式不能为空")
    private String manageModel;

    /**
     * 服务团队介绍
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "服务团队介绍不能为空")
    private String serviceTeamIntro;

    /**
     * 员工数量
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "员工数量不能为空")
    private String staffNo;

    /**
     * 囊括区域
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "囊括区域不能为空")
    private String includeArea;

    /**
     * 一体化解决方案
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "一体化解决方案不能为空")
    private String solvingScheme;

    /**
     * 需求类型
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "需求类型不能为空")
    private DemandType demandType;

    /**
     * 荣誉与资质
     */
    private List<HonorAndQualityTO> honorAndQualityTOS;

    /**
     * 主业介绍
     */
    private List<MainBusinessIntroTO> mainBusinessIntroTOS;

    /**
     * 成功案例
     */
    private List<SuccessStoriesTO> successStoriesTOS;

    /**
     * 客户及合作伙伴
     */
    private List<CustomerAndPartnerTO> customerAndPartnerTOS;

    /**
     * 通讯途径
     */
    private List<CommunicationPathTO> communicationPathTOS;

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

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
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

    public List<HonorAndQualityTO> getHonorAndQualityTOS() {
        return honorAndQualityTOS;
    }

    public void setHonorAndQualityTOS(List<HonorAndQualityTO> honorAndQualityTOS) {
        this.honorAndQualityTOS = honorAndQualityTOS;
    }

    public List<MainBusinessIntroTO> getMainBusinessIntroTOS() {
        return mainBusinessIntroTOS;
    }

    public void setMainBusinessIntroTOS(List<MainBusinessIntroTO> mainBusinessIntroTOS) {
        this.mainBusinessIntroTOS = mainBusinessIntroTOS;
    }

    public List<SuccessStoriesTO> getSuccessStoriesTOS() {
        return successStoriesTOS;
    }

    public void setSuccessStoriesTOS(List<SuccessStoriesTO> successStoriesTOS) {
        this.successStoriesTOS = successStoriesTOS;
    }

    public List<CustomerAndPartnerTO> getCustomerAndPartnerTOS() {
        return customerAndPartnerTOS;
    }

    public void setCustomerAndPartnerTOS(List<CustomerAndPartnerTO> customerAndPartnerTOS) {
        this.customerAndPartnerTOS = customerAndPartnerTOS;
    }

    public List<CommunicationPathTO> getCommunicationPathTOS() {
        return communicationPathTOS;
    }

    public void setCommunicationPathTOS(List<CommunicationPathTO> communicationPathTOS) {
        this.communicationPathTOS = communicationPathTOS;
    }
}