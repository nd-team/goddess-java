package com.bjike.goddess.intromanage.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.intromanage.type.DemandType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

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

    //荣誉与资质
    /**
     * 各类证书
     */
    private String[] certificates;

    /**
     * 软件著作权
     */
    private String[] softwareCopyrights;

    //主业介绍
    /**
     * 业务类型
     */
    private String[] businessTypes;

    /**
     * 项目科目
     */
    private String[] projectSubjects;

    //成功案例
    /**
     * 通信类
     */
    private String[] communications;

    /**
     * 软件类
     */
    private String[] softwares;

    /**
     * 系统集成类
     */
    private String[] systemIntegrations;

    /**
     * 营销策划类
     */
    private String[] marketingPlannings;

    //客户及合作伙伴
    /**
     * 运营商
     */
    private String[] operators;

    /**
     * 厂家
     */
    private String[] manufacturers;

    /**
     * 各政府单位
     */
    private String[] governmentUnits;

    /**
     * 合作伙伴
     */
    private String[] partners;

    //通讯途径
    /**
     * 总公司地址
     */
    private String[] headOfficeAddresses;

    /**
     * 总公司联系方式
     */
    private String[] headOfficeContactes;

    /**
     * 分公司地址
     */
    private String[] branchAddresses;

    /**
     * 分公司联系方式
     */
    private String[] branchPhones;

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

    public String[] getCertificates() {
        return certificates;
    }

    public void setCertificates(String[] certificates) {
        this.certificates = certificates;
    }

    public String[] getSoftwareCopyrights() {
        return softwareCopyrights;
    }

    public void setSoftwareCopyrights(String[] softwareCopyrights) {
        this.softwareCopyrights = softwareCopyrights;
    }

    public String[] getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(String[] businessTypes) {
        this.businessTypes = businessTypes;
    }

    public String[] getProjectSubjects() {
        return projectSubjects;
    }

    public void setProjectSubjects(String[] projectSubjects) {
        this.projectSubjects = projectSubjects;
    }

    public String[] getCommunications() {
        return communications;
    }

    public void setCommunications(String[] communications) {
        this.communications = communications;
    }

    public String[] getSoftwares() {
        return softwares;
    }

    public void setSoftwares(String[] softwares) {
        this.softwares = softwares;
    }

    public String[] getSystemIntegrations() {
        return systemIntegrations;
    }

    public void setSystemIntegrations(String[] systemIntegrations) {
        this.systemIntegrations = systemIntegrations;
    }

    public String[] getMarketingPlannings() {
        return marketingPlannings;
    }

    public void setMarketingPlannings(String[] marketingPlannings) {
        this.marketingPlannings = marketingPlannings;
    }

    public String[] getOperators() {
        return operators;
    }

    public void setOperators(String[] operators) {
        this.operators = operators;
    }

    public String[] getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(String[] manufacturers) {
        this.manufacturers = manufacturers;
    }

    public String[] getGovernmentUnits() {
        return governmentUnits;
    }

    public void setGovernmentUnits(String[] governmentUnits) {
        this.governmentUnits = governmentUnits;
    }

    public String[] getPartners() {
        return partners;
    }

    public void setPartners(String[] partners) {
        this.partners = partners;
    }

    public String[] getHeadOfficeAddresses() {
        return headOfficeAddresses;
    }

    public void setHeadOfficeAddresses(String[] headOfficeAddresses) {
        this.headOfficeAddresses = headOfficeAddresses;
    }

    public String[] getHeadOfficeContactes() {
        return headOfficeContactes;
    }

    public void setHeadOfficeContactes(String[] headOfficeContactes) {
        this.headOfficeContactes = headOfficeContactes;
    }

    public String[] getBranchAddresses() {
        return branchAddresses;
    }

    public void setBranchAddresses(String[] branchAddresses) {
        this.branchAddresses = branchAddresses;
    }

    public String[] getBranchPhones() {
        return branchPhones;
    }

    public void setBranchPhones(String[] branchPhones) {
        this.branchPhones = branchPhones;
    }
}