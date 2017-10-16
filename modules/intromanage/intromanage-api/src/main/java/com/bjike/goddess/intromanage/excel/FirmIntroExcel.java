package com.bjike.goddess.intromanage.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.intromanage.type.DemandType;

import java.time.LocalDate;


/**
 * 公司简介导入
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirmIntroExcel extends BaseTO {
    /**
     * 序号
     */
    @ExcelHeader(name = "序号", notNull = true)
    private Integer seqNum;
    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称", notNull = true)
    private String firmName;

    /**
     * 公司性质
     */
    @ExcelHeader(name = "公司性质", notNull = true)
    private String firmNature;

    /**
     * 注册资金
     */
    @ExcelHeader(name = "注册资金", notNull = true)
    private String registerMoney;

    /**
     * 注册时间
     */
    @ExcelHeader(name = "注册时间", notNull = true)
    private LocalDate registerDate;

    /**
     * 公司精神
     */
    @ExcelHeader(name = "公司精神", notNull = true)
    private String firmSpirit;

    /**
     * 服务意识
     */
    @ExcelHeader(name = "服务意识", notNull = true)
    private String serviceAwareness;

    /**
     * 公司宗旨
     */
    @ExcelHeader(name = "公司宗旨", notNull = true)
    private String firmTenet;

    /**
     * 人才观
     */
    @ExcelHeader(name = "人才观", notNull = true)
    private String talentView;

    /**
     * 经营观
     */
    @ExcelHeader(name = "经营观", notNull = true)
    private String operationView;

    /**
     * 质量观
     */
    @ExcelHeader(name = "质量观", notNull = true)
    private String qualityView;

    /**
     * 组织结构
     */
    @ExcelHeader(name = "组织结构", notNull = true)
    private String organization;

    /**
     * 管理模式
     */
    @ExcelHeader(name = "管理模式", notNull = true)
    private String manageModel;

    /**
     * 服务团队介绍
     */
    @ExcelHeader(name = "服务团队介绍", notNull = true)
    private String serviceTeamIntro;

    /**
     * 员工数量
     */
    @ExcelHeader(name = "员工数量", notNull = true)
    private String staffNo;

    /**
     * 囊括区域
     */
    @ExcelHeader(name = "囊括区域", notNull = true)
    private String includeArea;

    /**
     * 一体化解决方案
     */
    @ExcelHeader(name = "一体化解决方案", notNull = true)
    private String solvingScheme;

    /**
     * 需求类型
     */
    @ExcelHeader(name = "需求类型", notNull = true)
    private DemandType demandType;

    /**
     * 战略定位
     */
    @ExcelHeader(name = "战略定位", notNull = true)
    private String positioning;
    /**
     * 各类证书
     */
    @ExcelHeader(name = "各类证书")
    private String certificates;

    /**
     * 软件著作权
     */
    @ExcelHeader(name = "软件著作权")
    private String softwareCopyright;
    /**
     * 业务类型
     */
    @ExcelHeader(name="业务类型")
    private String businessType;

    /**
     * 项目科目
     */
    @ExcelHeader(name="项目科目")
    private String projectSubject;

    /**
     * 通信类
     */
    @ExcelHeader(name="通信类")
    private String communication;

    /**
     * 软件类
     */
    @ExcelHeader(name="软件类")
    private String software;

    /**
     * 系统集成类
     */
    @ExcelHeader(name="系统集成类")
    private String systemIntegration;

    /**
     * 营销策划类
     */
    @ExcelHeader(name="营销策划类")
    private String marketingPlanning;
    /**
     * 运营商
     */
    @ExcelHeader(name="运营商")
    private String operators;

    /**
     * 厂家
     */
    @ExcelHeader(name="厂家")
    private String manufacturer;

    /**
     * 各政府单位
     */
    @ExcelHeader(name="各政府单位")
    private String governmentUnit;

    /**
     * 合作伙伴
     */
    @ExcelHeader(name="合作伙伴")
    private String partner;

    /**
     * 总公司地址
     */
    @ExcelHeader(name="总公司地址")
    private String headOfficeAddress;

    /**
     * 总公司联系方式
     */
    @ExcelHeader(name="总公司联系方式")
    private String headOfficeContact;

    /**
     * 分公司地址
     */
    @ExcelHeader(name="分公司地址")
    private String branchAddress;

    /**
     * 分公司联系方式
     */
    @ExcelHeader(name="分公司联系方式")
    private String branchPhone;

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

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

    public String getPositioning() {
        return positioning;
    }

    public void setPositioning(String positioning) {
        this.positioning = positioning;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getSoftwareCopyright() {
        return softwareCopyright;
    }

    public void setSoftwareCopyright(String softwareCopyright) {
        this.softwareCopyright = softwareCopyright;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getProjectSubject() {
        return projectSubject;
    }

    public void setProjectSubject(String projectSubject) {
        this.projectSubject = projectSubject;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getSystemIntegration() {
        return systemIntegration;
    }

    public void setSystemIntegration(String systemIntegration) {
        this.systemIntegration = systemIntegration;
    }

    public String getMarketingPlanning() {
        return marketingPlanning;
    }

    public void setMarketingPlanning(String marketingPlanning) {
        this.marketingPlanning = marketingPlanning;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getGovernmentUnit() {
        return governmentUnit;
    }

    public void setGovernmentUnit(String governmentUnit) {
        this.governmentUnit = governmentUnit;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getHeadOfficeAddress() {
        return headOfficeAddress;
    }

    public void setHeadOfficeAddress(String headOfficeAddress) {
        this.headOfficeAddress = headOfficeAddress;
    }

    public String getHeadOfficeContact() {
        return headOfficeContact;
    }

    public void setHeadOfficeContact(String headOfficeContact) {
        this.headOfficeContact = headOfficeContact;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchPhone() {
        return branchPhone;
    }

    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

}