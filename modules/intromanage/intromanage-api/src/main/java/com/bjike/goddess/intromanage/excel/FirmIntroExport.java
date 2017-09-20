package com.bjike.goddess.intromanage.excel;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.intromanage.type.DemandType;

import java.time.LocalDate;
import java.util.List;


/**
 * 公司简介导出
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:20 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirmIntroExport extends BaseBO {

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
     * 更新（制作）时间
     */
    @ExcelHeader(name = "更新（制作）时间", notNull = true)
    private LocalDate updateDate;

    /**
     * 战略定位
     */
    @ExcelHeader(name = "战略定位", notNull = true)
    private String positioning;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private Status status;

    /**
     * 荣誉与资质
     */
    private List<HonorAndQualityExport> honorAndQualityExportList;
    /**
     * 主业介绍
     */
    private List<MainBusinessIntroExport> mainBusinessIntroExportList;
    /**
     * 成功案例
     */
    private List<SuccessStoriesExport> successStoriesExportList;
    /**
     * 客户及合作伙伴
     */
    private List<CustomerAndPartnerExport> customerAndPartnerExportList;
    /**
     * 通讯途径
     */
    private List<CommunicationPathExprot> communicationPathExprotList;


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

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getPositioning() {
        return positioning;
    }

    public void setPositioning(String positioning) {
        this.positioning = positioning;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<HonorAndQualityExport> getHonorAndQualityExportList() {
        return honorAndQualityExportList;
    }

    public void setHonorAndQualityExportList(List<HonorAndQualityExport> honorAndQualityExportList) {
        this.honorAndQualityExportList = honorAndQualityExportList;
    }

    public List<MainBusinessIntroExport> getMainBusinessIntroExportList() {
        return mainBusinessIntroExportList;
    }

    public void setMainBusinessIntroExportList(List<MainBusinessIntroExport> mainBusinessIntroExportList) {
        this.mainBusinessIntroExportList = mainBusinessIntroExportList;
    }

    public List<SuccessStoriesExport> getSuccessStoriesExportList() {
        return successStoriesExportList;
    }

    public void setSuccessStoriesExportList(List<SuccessStoriesExport> successStoriesExportList) {
        this.successStoriesExportList = successStoriesExportList;
    }

    public List<CustomerAndPartnerExport> getCustomerAndPartnerExportList() {
        return customerAndPartnerExportList;
    }

    public void setCustomerAndPartnerExportList(List<CustomerAndPartnerExport> customerAndPartnerExportList) {
        this.customerAndPartnerExportList = customerAndPartnerExportList;
    }

    public List<CommunicationPathExprot> getCommunicationPathExprotList() {
        return communicationPathExprotList;
    }

    public void setCommunicationPathExprotList(List<CommunicationPathExprot> communicationPathExprotList) {
        this.communicationPathExprotList = communicationPathExprotList;
    }
}