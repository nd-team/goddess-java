package com.bjike.goddess.bidding.excel;

import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.common.utils.excel.ExcelValue;

/**
 * 招标信息表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.969 ]
 * @Description: [ 招标信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingInfoExport {
    /**
     * 编号
     */
    @ExcelHeader(name = "编号",notNull = true)
    private String biddingNumber;

    /**
     * 网站名称
     */
    @ExcelHeader(name = "网站名称", notNull = true)
    private String webName;

    /**
     * 网址
     */
    @ExcelHeader(name = "网址", notNull = true)
    private String url;

    /**
     * 招投标类型
     */
    @ExcelHeader(name = "招投标类型", notNull = true)
    private String biddingType;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型", notNull = true)
    private String businessType;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目", notNull = true)
    private String businessDirectionSubject;

    /**
     * 标书类型
     */
    @ExcelHeader(name = "标书模块", notNull = true)
    private String tenderModule;


    /**
     * 项目名称
     */
    @ExcelHeader(name = "项目名称", notNull = true)
    private String projectName;

    /**
     * 报名开始时间
     */
    @ExcelHeader(name = "报名开始时间", notNull = true)
    private String registrationStartTime;
    /**
     * 报名开始时间
     */
    @ExcelHeader(name = "报名开始时间", notNull = true)
    private String registrationEndTime;

    /**
     * 投标时间
     */
    @ExcelHeader(name = "投标时间", notNull = true)
    private String biddingTime;

    /**
     * 投标资格要求
     */
    @ExcelHeader(name = "投标资格要求", notNull = true)
    private String biddingQualifications;

    /**
     * 省份
     */
    @ExcelHeader(name = "省份", notNull = true)
    private String provinces;

    /**
     * 地市
     */
    @ExcelHeader(name = "地市", notNull = true)
    private String cities;

    /**
     * 账号
     */
    @ExcelHeader(name = "账号", notNull = true)
    private String account;

    /**
     * 密码
     */
    @ExcelHeader(name = "密码", notNull = true)
    private String password;

    /**
     * 注册人
     */
    @ExcelHeader(name = "注册人", notNull = true)
    private String registrant;

    /**
     * 注册信息
     */
    @ExcelHeader(name = "注册信息", notNull = true)
    private String registrationInfo;

    /**
     * 状态
     */
    @ExcelHeader(name = "状态", notNull = true)
    private String status;

    /**
     * 购买标书时间
     */
    @ExcelHeader(name = "购买标书时间", notNull = true)
    private String buyTenderTime;

    /**
     * 价格
     */
    @ExcelHeader(name = "价格", notNull = true)
    private Double price;

    /**
     * 购买标书要求
     */
    @ExcelHeader(name = "购买标书要求", notNull = true)
    private String buyTenderRequirements;

    /**
     * 交保证金时间
     */
    @ExcelHeader(name = "交保证金时间", notNull = true)
    private String marginTime;

    /**
     * 交保证金方式
     */
    @ExcelHeader(name = "交保证金方式", notNull = true)
    private String marginMethod;

    /**
     * 保证金退回时间
     */
    @ExcelHeader(name = "保证金退回时间", notNull = true)
    private String backTimeDeposit;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注", notNull = true)
    private String remark;

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

    public String getWebName() {
        return webName;
    }

    public void setWebName(String webName) {
        this.webName = webName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(String biddingType) {
        this.biddingType = biddingType;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessDirectionSubject() {
        return businessDirectionSubject;
    }

    public void setBusinessDirectionSubject(String businessDirectionSubject) {
        this.businessDirectionSubject = businessDirectionSubject;
    }

    public String getTenderModule() {
        return tenderModule;
    }

    public void setTenderModule(String tenderModule) {
        this.tenderModule = tenderModule;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(String registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public String getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(String registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
    }

    public String getBiddingTime() {
        return biddingTime;
    }

    public void setBiddingTime(String biddingTime) {
        this.biddingTime = biddingTime;
    }

    public String getBiddingQualifications() {
        return biddingQualifications;
    }

    public void setBiddingQualifications(String biddingQualifications) {
        this.biddingQualifications = biddingQualifications;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getRegistrationInfo() {
        return registrationInfo;
    }

    public void setRegistrationInfo(String registrationInfo) {
        this.registrationInfo = registrationInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyTenderTime() {
        return buyTenderTime;
    }

    public void setBuyTenderTime(String buyTenderTime) {
        this.buyTenderTime = buyTenderTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBuyTenderRequirements() {
        return buyTenderRequirements;
    }

    public void setBuyTenderRequirements(String buyTenderRequirements) {
        this.buyTenderRequirements = buyTenderRequirements;
    }

    public String getMarginTime() {
        return marginTime;
    }

    public void setMarginTime(String marginTime) {
        this.marginTime = marginTime;
    }

    public String getMarginMethod() {
        return marginMethod;
    }

    public void setMarginMethod(String marginMethod) {
        this.marginMethod = marginMethod;
    }

    public String getBackTimeDeposit() {
        return backTimeDeposit;
    }

    public void setBackTimeDeposit(String backTimeDeposit) {
        this.backTimeDeposit = backTimeDeposit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}