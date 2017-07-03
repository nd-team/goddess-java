package com.bjike.goddess.capability.excele;

import com.bjike.goddess.capability.enums.CompletePro;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午4:38
 * @Description: [个人能力展示]
 * @Version: [1.0.0]
 */
public class CooperCapabilityExcel extends BaseTO{

    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称",notNull = true)
    private String companyName;

    /**
     * 专业资质认证
     */
    @ExcelHeader(name = "专业资质认证",notNull = true)
    private String professionAuthen;

    /**
     * 管理资质认证
     */
    @ExcelHeader(name = "管理资质认证",notNull = true)
    private String manageAuthen;

    /**
     * 公司荣誉证书
     */
    @ExcelHeader(name = "公司荣誉证书",notNull = true)
    private String companyCertificate;

    /**
     * 公司参与项目
     */
    @ExcelHeader(name = "公司参与项目",notNull = true)
    private String companyProject;

    /**
     * 是否独立完成
     */
    @ExcelHeader(name = "是否独立完成",notNull = true)
    private CompletePro completePro;

    /**
     * 公司联系人
     */
    @ExcelHeader(name = "公司联系人",notNull = true)
    private String contactName;

    /**
     * 性别
     */
    @ExcelHeader(name = "性别",notNull = true)
    private String sex;

    /**
     * 联系方式
     */
    @ExcelHeader(name = "联系方式",notNull = true)
    private String contactWay;

    /**
     * 邮箱
     */
    @ExcelHeader(name = "邮箱",notNull = true)
    private String emailName;

    /**
     * QQ/微信
     */
    @ExcelHeader(name = "QQ/微信",notNull = true)
    private String qqOrWechat;

    /**
     * 籍贯
     */
    @ExcelHeader(name = "籍贯",notNull = true)
    private String natives;

    /**
     * 爱好
     */
    @ExcelHeader(name = "爱好",notNull = true)
    private String hobby;

    /**
     * 性格评价
     */
    @ExcelHeader(name = "性格评价",notNull = true)
    private String charact;

    /**
     * 家庭成员
     */
    @ExcelHeader(name = "家庭成员",notNull = true)
    private String family;

    /**
     * 家庭成员与本人关系
     */
    @ExcelHeader(name = "家庭成员与本人关系",notNull = true)
    private String familyRelation;

    /**
     * 求学和培训经历
     */
    @ExcelHeader(name = "求学和培训经历",notNull = true)
    private String studyExperience;

    /**
     * 接触经历
     */
    @ExcelHeader(name = "接触经历",notNull = true)
    private String connectExperience;

    /**
     * 以往工作地区
     */
    @ExcelHeader(name = "以往工作地区",notNull = true)
    private String oldWorkPlace;

    /**
     * 生活地区
     */
    @ExcelHeader(name = "生活地区",notNull = true)
    private String livePlace;

    /**
     * 成长地区
     */
    @ExcelHeader(name = "成长地区",notNull = true)
    private String growthPlace;

    /**
     * 组织机构
     */
    @ExcelHeader(name = "组织机构",notNull = true)
    private String organization;

    /**
     * 现在工作地区
     */
    @ExcelHeader(name = "现在工作地区",notNull = true)
    private String nowWorkPlace;

    /**
     * 所在公司
     */
    @ExcelHeader(name = "所在公司",notNull = true)
    private String nowCompany;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位",notNull = true)
    private String station;

    /**
     * 职权
     */
    @ExcelHeader(name = "职权",notNull = true)
    private String duty;


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

}
