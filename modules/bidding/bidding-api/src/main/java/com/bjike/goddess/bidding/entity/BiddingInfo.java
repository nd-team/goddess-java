package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 招标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.938 ]
 * @Description: [ 招标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_biddinginfo")
public class BiddingInfo extends BaseEntity {

    /**
     * 网站名称
     */
    @Column(name = "webName", columnDefinition = "VARCHAR(255)   COMMENT '网站名称'")
    private String webName;

    /**
     * 网址
     */
    @Column(name = "url", columnDefinition = "VARCHAR(255)   COMMENT '网址'")
    private String url;

    /**
     * 招投标类型
     */
    @Column(name = "biddingType", columnDefinition = "TINYINT(2)   COMMENT '招投标类型'")
    private BiddingType biddingType;


    /**
     * 业务类型
     */
    @Column(name = "businessType", columnDefinition = "TINYINT(2)   COMMENT '业务类型'")
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessDirectionSubject", columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessDirectionSubject;

    /**
     * 标书模块
     */
    @Column(name = "tenderModule", columnDefinition = "VARCHAR(255)   COMMENT '标书模块'")
    private String tenderModule;

    /**
     * 招标编号
     */
    @Column(name = "tenderNumber", columnDefinition = "VARCHAR(255)   COMMENT '招标编号'")
    private String tenderNumber;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 报名时间
     */
    @Column(name = "registrationTime", columnDefinition = "DATETIME   COMMENT '报名时间'")
    private LocalDateTime registrationTime;

    /**
     * 投标时间
     */
    @Column(name = "biddingTime", columnDefinition = "DATETIME   COMMENT '投标时间'")
    private LocalDateTime biddingTime;

    /**
     * 投标资格要求
     */
    @Column(name = "biddingQualifications", columnDefinition = "VARCHAR(255)   COMMENT '投标资格要求'")
    private String biddingQualifications;

    /**
     * 省份
     */
    @Column(name = "provinces", columnDefinition = "VARCHAR(255)   COMMENT '省份'")
    private String provinces;

    /**
     * 地市
     */
    @Column(name = "cities", columnDefinition = "VARCHAR(255)   COMMENT '地市'")
    private String cities;

    /**
     * 账号
     */
    @Column(name = "account", columnDefinition = "VARCHAR(255)   COMMENT '账号'")
    private String account;

    /**
     * 密码
     */
    @Column(name = "password", columnDefinition = "VARCHAR(255)   COMMENT '密码'")
    private String password;

    /**
     * 注册人
     */
    @Column(name = "registrant", columnDefinition = "VARCHAR(255)   COMMENT '注册人'")
    private String registrant;

    /**
     * 注册信息
     */
    @Column(name = "registrationInfo", columnDefinition = "VARCHAR(255)   COMMENT '注册信息'")
    private String registrationInfo;

    /**
     * 状态
     */
    @Column(name = "", columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private Status status;

    /**
     * 购买标书时间
     */
    @Column(name = "buyTenderTime", columnDefinition = "DATETIME   COMMENT '购买标书时间'")
    private LocalDateTime buyTenderTime;

    /**
     * 价格
     */
    @Column(name = "price", columnDefinition = "DECIMAL(10,2)   COMMENT '价格'")
    private Double price;

    /**
     * 购买标书要求
     */
    @Column(name = "buyTenderRequirements", columnDefinition = "VARCHAR(255)   COMMENT '购买标书要求'")
    private String buyTenderRequirements;

    /**
     * 交保证金时间
     */
    @Column(name = "marginTime", columnDefinition = "DATETIME   COMMENT '交保证金时间'")
    private LocalDateTime marginTime;

    /**
     * 交保证金方式
     */
    @Column(name = "marginMethod", columnDefinition = "VARCHAR(255)   COMMENT '交保证金方式'")
    private String marginMethod;

    /**
     * 保证金退回时间
     */
    @Column(name = "backTimeDeposit", columnDefinition = "DATETIME   COMMENT '保证金退回时间'")
    private LocalDateTime backTimeDeposit;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


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

    public BiddingType getBiddingType() {
        return biddingType;
    }

    public void setBiddingType(BiddingType biddingType) {
        this.biddingType = biddingType;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
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

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public LocalDateTime getBiddingTime() {
        return biddingTime;
    }

    public void setBiddingTime(LocalDateTime biddingTime) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getBuyTenderTime() {
        return buyTenderTime;
    }

    public void setBuyTenderTime(LocalDateTime buyTenderTime) {
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

    public LocalDateTime getMarginTime() {
        return marginTime;
    }

    public void setMarginTime(LocalDateTime marginTime) {
        this.marginTime = marginTime;
    }

    public String getMarginMethod() {
        return marginMethod;
    }

    public void setMarginMethod(String marginMethod) {
        this.marginMethod = marginMethod;
    }

    public LocalDateTime getBackTimeDeposit() {
        return backTimeDeposit;
    }

    public void setBackTimeDeposit(LocalDateTime backTimeDeposit) {
        this.backTimeDeposit = backTimeDeposit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}