package com.bjike.goddess.capability.excele;

import com.bjike.goddess.capability.enums.CompletePro;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

/**
 * @Author: [zhuangkaiqin]
 * @Date 17-6-16下午4:38
 * @Description: [公司能力展示]
 * @Version: [1.0.0]
 */
public class CompanyCapabilityExcele extends BaseTO{
    /**
     * 公司名称
     */
    @ExcelHeader(name = "公司名称",notNull = true)
    private String company;

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
     * 公司发展规划
     */
    @ExcelHeader(name = "公司发展规划",notNull = true)
    private String companyDevelop;

    /**
     * 项目发展规划
     */
    @ExcelHeader(name = "项目发展规划",notNull = true)
    private String projectDevelop;

    /**
     * 公司所在地区
     */
    @ExcelHeader(name = "公司所在地区",notNull = true)
    private String area;

    /**
     * 公司资金
     */
    @ExcelHeader(name = "公司资金",notNull = true)
    private String money;

    /**
     * 人员组成
     */
    @ExcelHeader(name = "人员组成",notNull = true)
    private String personForm;

    /**
     * 配置
     */
    @ExcelHeader(name = "配置",notNull = true)
    private String config;

    /**
     * 设备
     */
    @ExcelHeader(name = "设备",notNull = true)
    private String device;

    /**
     * 公司占地面积
     */
    @ExcelHeader(name = "公司占地面积",notNull = true)
    private String companyArea;

    /**
     * 公司业务
     */
    @ExcelHeader(name = "公司业务",notNull = true)
    private String companyBusiness;

    /**
     * 公司合作对象
     */
    @ExcelHeader(name = "公司合作对象",notNull = true)
    private String cooperate;

    /**
     * 已完成项目
     */
//    @ExcelHeader(name = "已完成项目",notNull = true)
//    private CompletePro completePro;


    /**
     * 已完成项目
     */
    @ExcelHeader(name = "已完成项目", notNull = true)
    private String companyProject;

    /**
     * 尚在进行中项目
     */
    @ExcelHeader(name = "尚在进行中项目",notNull = true)
    private String inProjct;

    /**
     * 公司文化体系交流
     */
    @ExcelHeader(name = "公司文化体系交流",notNull = true)
    private String culture;

    /**
     * 员工节假日活动
     */
    @ExcelHeader(name = "员工节假日活动",notNull = true)
    private String holidayActive;

    /**
     * 公司公告栏
     */
    @ExcelHeader(name = "公司公告栏",notNull = true)
    private String bulletinBoard;



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

    public String getCompanyProject() {
        return companyProject;
    }

    public void setCompanyProject(String companyProject) {
        this.companyProject = companyProject;
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

}
