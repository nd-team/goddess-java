package com.bjike.goddess.bidding.excel;

import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * 标书资料表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.310 ]
 * @Description: [ 标书资料表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TenderInfoExport {

    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称" ,notNull = true)
    private String projectName;

    /**
     * 资格要求
     */
    @ExcelHeader(name = "资格要求" ,notNull = true)
    private String eligibilityRequirements;

    /**
     * 标书编号
     */
    @ExcelHeader(name = "标书编号" ,notNull = true)
    private String tenderNumber;

    /**
     * 同类项目经验
     */
    @ExcelHeader(name = "同类项目经验" ,notNull = true)
    private String experienceProject;

    /**
     * 主营业务收入
     */
    @ExcelHeader(name = "主营业务收入" ,notNull = true)
    private String mainBusinessIncome;

    /**
     * 专业资质认证
     */
    @ExcelHeader(name = "专业资质认证" ,notNull = true)
    private String professionalQualificationCertification;

    /**
     * 管理体系认证
     */
    @ExcelHeader(name = "管理体系认证" ,notNull = true)
    private String managementSystemCertification;

    /**
     * 项目经理素质
     */
    @ExcelHeader(name = "项目经理素质" ,notNull = true)
    private String projectManagerQuality;

    /**
     * 团队成员素质
     */
    @ExcelHeader(name = "团队成员素质" ,notNull = true)
    private String teamMembersQuality;

    /**
     * 资源投入
     */
    @ExcelHeader(name = "资源投入" ,notNull = true)
    private String resourcesInto;

    /**
     * 技术方案
     */
    @ExcelHeader(name = "技术方案" ,notNull = true)
    private String technicalSolution;

    /**
     * 额外承诺
     */
    @ExcelHeader(name = "额外承诺" ,notNull = true)
    private String additionalCommitments;

    /**
     * 补充资料
     */
    @ExcelHeader(name = "补充资料" ,notNull = true)
    private String additionalInformation;

    /**
     * 经济分册
     */
    @ExcelHeader(name = "经济分册" ,notNull = true)
    private String economicVolume;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEligibilityRequirements() {
        return eligibilityRequirements;
    }

    public void setEligibilityRequirements(String eligibilityRequirements) {
        this.eligibilityRequirements = eligibilityRequirements;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getExperienceProject() {
        return experienceProject;
    }

    public void setExperienceProject(String experienceProject) {
        this.experienceProject = experienceProject;
    }

    public String getMainBusinessIncome() {
        return mainBusinessIncome;
    }

    public void setMainBusinessIncome(String mainBusinessIncome) {
        this.mainBusinessIncome = mainBusinessIncome;
    }

    public String getProfessionalQualificationCertification() {
        return professionalQualificationCertification;
    }

    public void setProfessionalQualificationCertification(String professionalQualificationCertification) {
        this.professionalQualificationCertification = professionalQualificationCertification;
    }

    public String getManagementSystemCertification() {
        return managementSystemCertification;
    }

    public void setManagementSystemCertification(String managementSystemCertification) {
        this.managementSystemCertification = managementSystemCertification;
    }

    public String getProjectManagerQuality() {
        return projectManagerQuality;
    }

    public void setProjectManagerQuality(String projectManagerQuality) {
        this.projectManagerQuality = projectManagerQuality;
    }

    public String getTeamMembersQuality() {
        return teamMembersQuality;
    }

    public void setTeamMembersQuality(String teamMembersQuality) {
        this.teamMembersQuality = teamMembersQuality;
    }

    public String getResourcesInto() {
        return resourcesInto;
    }

    public void setResourcesInto(String resourcesInto) {
        this.resourcesInto = resourcesInto;
    }

    public String getTechnicalSolution() {
        return technicalSolution;
    }

    public void setTechnicalSolution(String technicalSolution) {
        this.technicalSolution = technicalSolution;
    }

    public String getAdditionalCommitments() {
        return additionalCommitments;
    }

    public void setAdditionalCommitments(String additionalCommitments) {
        this.additionalCommitments = additionalCommitments;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getEconomicVolume() {
        return economicVolume;
    }

    public void setEconomicVolume(String economicVolume) {
        this.economicVolume = economicVolume;
    }
}