package com.bjike.goddess.capability.to;

import com.bjike.goddess.capability.enums.CompletePro;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 公司能力展示
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompanyCapabilityTO extends BaseTO {

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空")
    private String company;

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
     * 公司发展规划
     */
    private String companyDevelop;

    /**
     * 项目发展规划
     */
    private String projectDevelop;

    /**
     * 公司所在地区
     */
    private String area;

    /**
     * 公司资金
     */
    private String money;

    /**
     * 人员组成
     */
    private String personForm;

    /**
     * 配置
     */
    private String config;

    /**
     * 设备
     */
    private String device;

    /**
     * 公司占地面积
     */
    private String companyArea;

    /**
     * 公司业务
     */
    private String companyBusiness;

    /**
     * 公司合作对象
     */
    private String cooperate;

    /**
     * 已完成项目
     */
    private CompletePro completePro;

    /**
     * 尚在进行中项目
     */
    private String inProjct;

    /**
     * 公司文化体系交流
     */
    private String culture;

    /**
     * 员工节假日活动
     */
    private String holidayActive;

    /**
     * 公司公告栏
     */
    private String bulletinBoard;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 管理资质认证数组
     */
    private String[] manageAuthens;


    /**
     * 专业资质认证数组
     */
    private String[] professionAuthens;

    /**
     * 公司荣誉证书数组
     */
    private String[] companyCertificates;

    /**
     * 尚在进行中项目
     */
    private String[] inProjcts;

    /**
     * 已完成项目
     */
    private String[] companyProjects;

    /**
     * 公司合作对象
     */
    private String[] cooperates;

    /**
     * 已完成项目
     */
    private String companyProject;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getCompanyDevelop() {
        return companyDevelop;
    }

    public void setCompanyDevelop(String companyDevelop) {
        this.companyDevelop = companyDevelop;
    }

    public String getProjectDevelop() {
        return projectDevelop;
    }

    public void setProjectDevelop(String projectDevelop) {
        this.projectDevelop = projectDevelop;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPersonForm() {
        return personForm;
    }

    public void setPersonForm(String personForm) {
        this.personForm = personForm;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanyBusiness() {
        return companyBusiness;
    }

    public void setCompanyBusiness(String companyBusiness) {
        this.companyBusiness = companyBusiness;
    }

    public String getCooperate() {
        return cooperate;
    }

    public void setCooperate(String cooperate) {
        this.cooperate = cooperate;
    }

    public CompletePro getCompletePro() {
        return completePro;
    }

    public void setCompletePro(CompletePro completePro) {
        this.completePro = completePro;
    }

    public String getInProjct() {
        return inProjct;
    }

    public void setInProjct(String inProjct) {
        this.inProjct = inProjct;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getHolidayActive() {
        return holidayActive;
    }

    public void setHolidayActive(String holidayActive) {
        this.holidayActive = holidayActive;
    }

    public String getBulletinBoard() {
        return bulletinBoard;
    }

    public void setBulletinBoard(String bulletinBoard) {
        this.bulletinBoard = bulletinBoard;
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

    public String[] getManageAuthens() {
        return manageAuthens;
    }

    public void setManageAuthens(String[] manageAuthens) {
        this.manageAuthens = manageAuthens;
    }

    public String[] getProfessionAuthens() {
        return professionAuthens;
    }

    public void setProfessionAuthens(String[] professionAuthens) {
        this.professionAuthens = professionAuthens;
    }

    public String[] getCompanyCertificates() {
        return companyCertificates;
    }

    public void setCompanyCertificates(String[] companyCertificates) {
        this.companyCertificates = companyCertificates;
    }

    public String[] getInProjcts() {
        return inProjcts;
    }

    public void setInProjcts(String[] inProjcts) {
        this.inProjcts = inProjcts;
    }

    public String[] getCompanyProjects() {
        return companyProjects;
    }

    public void setCompanyProjects(String[] companyProjects) {
        this.companyProjects = companyProjects;
    }

    public String[] getCooperates() {
        return cooperates;
    }

    public void setCooperates(String[] cooperates) {
        this.cooperates = cooperates;
    }

    public String getCompanyProject() {
        return companyProject;
    }

    public void setCompanyProject(String companyProject) {
        this.companyProject = companyProject;
    }
}