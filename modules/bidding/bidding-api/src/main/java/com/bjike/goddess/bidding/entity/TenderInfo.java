package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 标书资料
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:05:05.304 ]
 * @Description: [ 标书资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_tenderinfo")
public class TenderInfo extends BaseEntity {

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 资格要求
     */
    @Column(name = "eligibilityRequirements", columnDefinition = "VARCHAR(255)   COMMENT '资格要求'")
    private String eligibilityRequirements;

    /**
     * 标书编号
     */
    @Column(name = "tenderNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '标书编号'")
    private String tenderNumber;

    /**
     * 同类项目经验
     */
    @Column(name = "experienceProject", columnDefinition = "VARCHAR(255)   COMMENT '同类项目经验'")
    private String experienceProject;

    /**
     * 主营业务收入
     */
    @Column(name = "mainBusinessIncome", columnDefinition = "VARCHAR(255)   COMMENT '主营业务收入'")
    private String mainBusinessIncome;

    /**
     * 专业资质认证
     */
    @Column(name = "professionalQualificationCertification", columnDefinition = "VARCHAR(255)   COMMENT '专业资质认证'")
    private String professionalQualificationCertification;

    /**
     * 管理体系认证
     */
    @Column(name = "managementSystemCertification", columnDefinition = "VARCHAR(255)   COMMENT '管理体系认证'")
    private String managementSystemCertification;

    /**
     * 项目经理素质
     */
    @Column(name = "projectManagerQuality", columnDefinition = "VARCHAR(255)   COMMENT '项目经理素质'")
    private String projectManagerQuality;

    /**
     * 团队成员素质
     */
    @Column(name = "teamMembersQuality", columnDefinition = "VARCHAR(255)   COMMENT '团队成员素质'")
    private String teamMembersQuality;

    /**
     * 资源投入
     */
    @Column(name = "resourcesInto", columnDefinition = "VARCHAR(255)   COMMENT '资源投入'")
    private String resourcesInto;

    /**
     * 技术方案
     */
    @Column(name = "technicalSolution", columnDefinition = "VARCHAR(255)   COMMENT '技术方案'")
    private String technicalSolution;

    /**
     * 额外承诺
     */
    @Column(name = "additionalCommitments", columnDefinition = "VARCHAR(255)   COMMENT '额外承诺'")
    private String additionalCommitments;

    /**
     * 补充资料
     */
    @Column(name = "additionalInformation", columnDefinition = "VARCHAR(255)   COMMENT '补充资料'")
    private String additionalInformation;

    /**
     * 经济分册
     */
    @Column(name = "economicVolume", columnDefinition = "VARCHAR(255)   COMMENT '经济分册'")
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