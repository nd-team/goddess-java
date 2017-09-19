package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
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
     * 编号
     */
    @Column(name = "biddingNumber",unique = true,columnDefinition = "VARCHAR(255)   COMMENT '招标编号'")
    private String biddingNumber;
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
     * 年份
     */
    @Column(name = "year", columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;
    /**
     * 月份
     */
    @Column(name = "month", columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;


    /**
     * 招投标类型
     */
    @Column(name = "biddingType", columnDefinition = "VARCHAR(255)   COMMENT '招投标类型'")
    private String biddingType;


    /**
     * 业务类型
     */
    @Column(name = "businessType", columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessDirectionSubject", columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessDirectionSubject;

    /**
     * 标书类型
     */
    @Column(name = "tenderModule", columnDefinition = "VARCHAR(255)   COMMENT '标书模块'")
    private String tenderModule;


    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 报名开始时间
     */
    @Column(name = "registrationStartTime", columnDefinition = "DATETIME   COMMENT '报名时间'")
    private LocalDateTime registrationStartTime;
    /**
     * 报名结束时间
     */
    @Column(name = "registrationEndTime", columnDefinition = "DATETIME   COMMENT '报名时间'")
    private LocalDateTime registrationEndTime;

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
    @Column(columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private String status;

    /**
     * 购买标书时间
     */
    @Column(name = "buyTenderTime", columnDefinition = "DATE   COMMENT '购买标书时间'")
    private LocalDate buyTenderTime;

    /**
     * 标书价格
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
    @Column(name = "marginTime", columnDefinition = "DATE   COMMENT '交保证金时间'")
    private LocalDate marginTime;
    /**
     * 保证金金额
     */
    @Column(name = "marginPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '保证金金额'")
    private Double marginPrice;
    /**
     * 退回保证金金额
     */
    @Column(name = "returnMarginPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '退回保证金金额'")
    private Double returnMarginPrice;

    /**
     * 交保证金方式
     */
    @Column(name = "marginMethod", columnDefinition = "VARCHAR(255)   COMMENT '交保证金方式'")
    private String marginMethod;

    /**
     * 保证金退回时间
     */
    @Column(name = "backTimeDeposit", columnDefinition = "DATE   COMMENT '保证金退回时间'")
    private LocalDate backTimeDeposit;
    /**
     * 竞争对手数量
     */
    @Column(name = "contendNum", columnDefinition = "INT(1)   COMMENT '竞争对手数量'")
    private Integer contendNum;
    /**
     * 是否进行项目测算
     */
    @Column(name = "is_projectEstimates", columnDefinition = "TINYINT(1)   COMMENT '是否进行项目测算'")
    private Boolean projectEstimates;
    /**
     * 项目测算是否通过
     */
    @Column(name = "is_passProjectEstimates", columnDefinition = "TINYINT(1)   COMMENT '项目测算是否通过'")
    private Boolean passProjectEstimates;
    /**
     * 规模数量
     */
    @Column(name = "scale", columnDefinition = "INT(11)   COMMENT '规模数量'")
    private Integer scale;
    /**
     * 是否转为商机
     */
    @Column(name = "is_opportunity", columnDefinition = "TINYINT(1)   COMMENT '是否转为商机'")
    private Boolean opportunity;
    /**
     * 更新时间
     */
    @Column(name = "updateTime", columnDefinition = "DATE   COMMENT '更新时间'")
    private LocalDate updateTime;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getMarginPrice() {
        return marginPrice;
    }

    public void setMarginPrice(Double marginPrice) {
        this.marginPrice = marginPrice;
    }

    public Double getReturnMarginPrice() {
        return returnMarginPrice;
    }

    public void setReturnMarginPrice(Double returnMarginPrice) {
        this.returnMarginPrice = returnMarginPrice;
    }

    public Integer getContendNum() {
        return contendNum;
    }

    public void setContendNum(Integer contendNum) {
        this.contendNum = contendNum;
    }

    public Boolean getProjectEstimates() {
        return projectEstimates;
    }

    public void setProjectEstimates(Boolean projectEstimates) {
        this.projectEstimates = projectEstimates;
    }

    public Boolean getPassProjectEstimates() {
        return passProjectEstimates;
    }

    public void setPassProjectEstimates(Boolean passProjectEstimates) {
        this.passProjectEstimates = passProjectEstimates;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Boolean getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Boolean opportunity) {
        this.opportunity = opportunity;
    }

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

    public LocalDateTime getRegistrationStartTime() {
        return registrationStartTime;
    }

    public void setRegistrationStartTime(LocalDateTime registrationStartTime) {
        this.registrationStartTime = registrationStartTime;
    }

    public LocalDateTime getRegistrationEndTime() {
        return registrationEndTime;
    }

    public void setRegistrationEndTime(LocalDateTime registrationEndTime) {
        this.registrationEndTime = registrationEndTime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBuyTenderTime() {
        return buyTenderTime;
    }

    public void setBuyTenderTime(LocalDate buyTenderTime) {
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

    public LocalDate getMarginTime() {
        return marginTime;
    }

    public void setMarginTime(LocalDate marginTime) {
        this.marginTime = marginTime;
    }

    public String getMarginMethod() {
        return marginMethod;
    }

    public void setMarginMethod(String marginMethod) {
        this.marginMethod = marginMethod;
    }

    public LocalDate getBackTimeDeposit() {
        return backTimeDeposit;
    }

    public void setBackTimeDeposit(LocalDate backTimeDeposit) {
        this.backTimeDeposit = backTimeDeposit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}