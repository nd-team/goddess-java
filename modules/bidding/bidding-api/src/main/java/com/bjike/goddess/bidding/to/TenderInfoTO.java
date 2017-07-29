package com.bjike.goddess.bidding.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 标书资料
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.309 ]
 * @Description: [ 标书资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TenderInfoTO extends BaseTO {
    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空",groups = {ADD.class, EDIT.class})
    private String biddingNumber;
    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String projectName;

    /**
     * 资格要求
     */
    @NotBlank(message = "资格要求不能为空",groups = {ADD.class, EDIT.class})
    private String eligibilityRequirements;

    /**
     * 标书编号
     */
    @NotBlank(message = "标书编号不能为空",groups = {ADD.class, EDIT.class})
    private String tenderNumber;

    /**
     * 同类项目经验
     */
    @NotBlank(message = "同类项目经验不能为空",groups = {ADD.class, EDIT.class})
    private String experienceProject;

    /**
     * 主营业务收入
     */
    @NotBlank(message = "主营业务收入不能为空",groups = {ADD.class, EDIT.class})
    private String mainBusinessIncome;

    /**
     * 专业资质认证
     */
    @NotBlank(message = "专业资质认证不能为空",groups = {ADD.class, EDIT.class})
    private String professionalQualificationCertification;

    /**
     * 管理体系认证
     */
    @NotBlank(message = "管理体系认证不能为空",groups = {ADD.class, EDIT.class})
    private String managementSystemCertification;

    /**
     * 项目经理素质
     */
    @NotBlank(message = "项目经理素质不能为空",groups = {ADD.class, EDIT.class})
    private String projectManagerQuality;

    /**
     * 团队成员素质
     */
    @NotBlank(message = "项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String teamMembersQuality;

    /**
     * 资源投入
     */
    @NotBlank(message = "资源投入不能为空",groups = {ADD.class, EDIT.class})
    private String resourcesInto;

    /**
     * 技术方案
     */
    @NotBlank(message = "技术方案不能为空",groups = {ADD.class, EDIT.class})
    private String technicalSolution;

    /**
     * 额外承诺
     */
    @NotBlank(message = "额外承诺不能为空",groups = {ADD.class, EDIT.class})
    private String additionalCommitments;

    /**
     * 补充资料
     */
    @NotBlank(message = "补充资料不能为空",groups = {ADD.class, EDIT.class})
    private String additionalInformation;

    /**
     * 经济分册
     */
    @NotBlank(message = "经济分册不能为空",groups = {ADD.class, EDIT.class})
    private String economicVolume;

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

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