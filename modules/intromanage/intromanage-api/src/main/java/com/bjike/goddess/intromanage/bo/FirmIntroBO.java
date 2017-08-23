package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.intromanage.type.DemandType;

import java.util.List;

/**
 * 公司简介业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirmIntroBO extends BaseBO {

    /**
     * 公司名称
     */
    private String firmName;

    /**
     * 公司性质
     */
    private String firmNature;

    /**
     * 注册资金
     */
    private String registerMoney;

    /**
     * 注册时间
     */
    private String registerDate;

    /**
     * 公司精神
     */
    private String firmSpirit;

    /**
     * 服务意识
     */
    private String serviceAwareness;

    /**
     * 公司宗旨
     */
    private String firmTenet;

    /**
     * 人才观
     */
    private String talentView;

    /**
     * 经营观
     */
    private String operationView;

    /**
     * 质量观
     */
    private String qualityView;

    /**
     * 组织结构
     */
    private String organization;

    /**
     * 管理模式
     */
    private String manageModel;

    /**
     * 服务团队介绍
     */
    private String serviceTeamIntro;

    /**
     * 员工数量
     */
    private String staffNo;

    /**
     * 囊括区域
     */
    private String includeArea;

    /**
     * 一体化解决方案
     */
    private String solvingScheme;

    /**
     * 需求类型
     */
    private DemandType demandType;

    /**
     * 荣誉与资质
     */
    private List<HonorAndQualityBO> honorAndQualityBOS;
    /**
     * 主业介绍
     */
    private List<MainBusinessIntroBO> mainBusinessIntroBOS;
    /**
     * 成功案例
     */
    private List<SuccessStoriesBO> successStoriesBOS;

    /**
     * 客户及合作伙伴
     */
    private List<CustomerAndPartnerBO> customerAndPartnerBOS;

    /**
     * 通讯途径
     */
    private List<CommunicationPathBO> communicationPathBOS;


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

    public List<HonorAndQualityBO> getHonorAndQualityBOS() {
        return honorAndQualityBOS;
    }

    public void setHonorAndQualityBOS(List<HonorAndQualityBO> honorAndQualityBOS) {
        this.honorAndQualityBOS = honorAndQualityBOS;
    }

    public List<MainBusinessIntroBO> getMainBusinessIntroBOS() {
        return mainBusinessIntroBOS;
    }

    public void setMainBusinessIntroBOS(List<MainBusinessIntroBO> mainBusinessIntroBOS) {
        this.mainBusinessIntroBOS = mainBusinessIntroBOS;
    }

    public List<SuccessStoriesBO> getSuccessStoriesBOS() {
        return successStoriesBOS;
    }

    public void setSuccessStoriesBOS(List<SuccessStoriesBO> successStoriesBOS) {
        this.successStoriesBOS = successStoriesBOS;
    }

    public List<CustomerAndPartnerBO> getCustomerAndPartnerBOS() {
        return customerAndPartnerBOS;
    }

    public void setCustomerAndPartnerBOS(List<CustomerAndPartnerBO> customerAndPartnerBOS) {
        this.customerAndPartnerBOS = customerAndPartnerBOS;
    }

    public List<CommunicationPathBO> getCommunicationPathBOS() {
        return communicationPathBOS;
    }

    public void setCommunicationPathBOS(List<CommunicationPathBO> communicationPathBOS) {
        this.communicationPathBOS = communicationPathBOS;
    }
}