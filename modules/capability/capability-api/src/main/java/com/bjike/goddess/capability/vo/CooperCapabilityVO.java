package com.bjike.goddess.capability.vo;

import com.bjike.goddess.capability.enums.CompletePro;

/**
 * 合作对象商务展示表现层对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:31 ]
 * @Description: [ 合作对象商务展示表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CooperCapabilityVO {

    /**
     * id
     */
    private String id;
    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 专业资质认证
     */
    private String professionAuthen;

    /**
     * 管理资质认证
     */
    private String manageAuthen;

    /**
     * 公司荣誉证书
     */
    private String companyCertificate;

    /**
     * 公司参与项目
     */
    private String companyProject;

    /**
     * 是否独立完成
     */
    private CompletePro completePro;

    /**
     * 公司联系人
     */
    private String contactName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系方式
     */
    private String contactWay;

    /**
     * 邮箱
     */
    private String emailName;

    /**
     * QQ/微信
     */
    private String qqOrWechat;

    /**
     * 籍贯
     */
    private String natives;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 性格评价
     */
    private String charact;

    /**
     * 家庭成员
     */
    private String family;

    /**
     * 家庭成员与本人关系
     */
    private String familyRelation;

    /**
     * 求学和培训经历
     */
    private String studyExperience;

    /**
     * 接触经历
     */
    private String connectExperience;

    /**
     * 以往工作地区
     */
    private String oldWorkPlace;

    /**
     * 生活地区
     */
    private String livePlace;

    /**
     * 成长地区
     */
    private String growthPlace;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 组织机构
     */
    private String organization;

    /**
     * 现在工作地区
     */
    private String nowWorkPlace;

    /**
     * 所在公司
     */
    private String nowCompany;

    /**
     * 岗位
     */
    private String station;

    /**
     * 职权
     */
    private String duty;

    /**
     * 公司参与项目数
     */
    private String[] completePros;
    /**
     * 专业资质认证数
     */
    private String[] professionAuthens;

    /**
     * 管理资质认证数
     */
    private String[] manageAuthens;

    /**
     * 公司荣誉证书数
     */
    private String[] companyCertificates;

    /**
     * 公司已完成项目数
     */
    private String[] companyProjects;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProfessionAuthen() {
        return professionAuthen;
    }

    public void setProfessionAuthen(String professionAuthen) {
        this.professionAuthen = professionAuthen;
    }

    public String getManageAuthen() {
        return manageAuthen;
    }

    public void setManageAuthen(String manageAuthen) {
        this.manageAuthen = manageAuthen;
    }

    public String getCompanyCertificate() {
        return companyCertificate;
    }

    public void setCompanyCertificate(String companyCertificate) {
        this.companyCertificate = companyCertificate;
    }

    public String getCompanyProject() {
        return companyProject;
    }

    public void setCompanyProject(String companyProject) {
        this.companyProject = companyProject;
    }

    public CompletePro getCompletePro() {
        return completePro;
    }

    public void setCompletePro(CompletePro completePro) {
        this.completePro = completePro;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getEmailName() {
        return emailName;
    }

    public void setEmailName(String emailName) {
        this.emailName = emailName;
    }

    public String getQqOrWechat() {
        return qqOrWechat;
    }

    public void setQqOrWechat(String qqOrWechat) {
        this.qqOrWechat = qqOrWechat;
    }

    public String getNatives() {
        return natives;
    }

    public void setNatives(String natives) {
        this.natives = natives;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCharact() {
        return charact;
    }

    public void setCharact(String charact) {
        this.charact = charact;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFamilyRelation() {
        return familyRelation;
    }

    public void setFamilyRelation(String familyRelation) {
        this.familyRelation = familyRelation;
    }

    public String getStudyExperience() {
        return studyExperience;
    }

    public void setStudyExperience(String studyExperience) {
        this.studyExperience = studyExperience;
    }

    public String getConnectExperience() {
        return connectExperience;
    }

    public void setConnectExperience(String connectExperience) {
        this.connectExperience = connectExperience;
    }

    public String getOldWorkPlace() {
        return oldWorkPlace;
    }

    public void setOldWorkPlace(String oldWorkPlace) {
        this.oldWorkPlace = oldWorkPlace;
    }

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public String getGrowthPlace() {
        return growthPlace;
    }

    public void setGrowthPlace(String growthPlace) {
        this.growthPlace = growthPlace;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getNowWorkPlace() {
        return nowWorkPlace;
    }

    public void setNowWorkPlace(String nowWorkPlace) {
        this.nowWorkPlace = nowWorkPlace;
    }

    public String getNowCompany() {
        return nowCompany;
    }

    public void setNowCompany(String nowCompany) {
        this.nowCompany = nowCompany;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String[] getCompletePros() {
        return completePros;
    }

    public void setCompletePros(String[] completePros) {
        this.completePros = completePros;
    }

    public String[] getProfessionAuthens() {
        return professionAuthens;
    }

    public void setProfessionAuthens(String[] professionAuthens) {
        this.professionAuthens = professionAuthens;
    }

    public String[] getManageAuthens() {
        return manageAuthens;
    }

    public void setManageAuthens(String[] manageAuthens) {
        this.manageAuthens = manageAuthens;
    }

    public String[] getCompanyCertificates() {
        return companyCertificates;
    }

    public void setCompanyCertificates(String[] companyCertificates) {
        this.companyCertificates = companyCertificates;
    }

    public String[] getCompanyProjects() {
        return companyProjects;
    }

    public void setCompanyProjects(String[] companyProjects) {
        this.companyProjects = companyProjects;
    }
}