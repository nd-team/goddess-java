package com.bjike.goddess.bidding.vo;

import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;

/**
 * 招标信息表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.969 ]
 * @Description: [ 招标信息表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingInfoVO {
    /**
     * id
     */
    private String id;
    /**
     * 编号
     */
    private String biddingNumber;
    /**
     * 网站名称
     */
    private String webName;

    /**
     * 网址
     */
    private String url;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;

    /**
     * 招投标类型
     */
    private String biddingType;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务方向科目
     */
    private String businessDirectionSubject;

    /**
     * 标书模块
     */
    private String tenderModule;

    /**
     * 标书类型
     */
    private String tenderType;


    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 报名开始时间
     */
    private String registrationStartTime;
    /**
     * 报名结束时间
     */
    private String registrationEndTime;

    /**
     * 投标时间
     */
    private String biddingTime;

    /**
     * 投标资格要求
     */
    private String biddingQualifications;

    /**
     * 省份
     */
    private String provinces;

    /**
     * 地市
     */
    private String cities;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册人
     */
    private String registrant;

    /**
     * 注册信息
     */
    private String registrationInfo;

    /**
     * 状态
     */
    private String status;

    /**
     * 购买标书时间
     */
    private String buyTenderTime;

    /**
     * 标书价格
     */
    private Double price;

    /**
     * 购买标书要求
     */
    private String buyTenderRequirements;

    /**
     * 交保证金时间
     */
    private String marginTime;
    /**
     * 保证金金额
     */
    private Double marginPrice;
    /**
     * 退回保证金金额
     */
    private Double returnMarginPrice;

    /**
     * 交保证金方式
     */
    private String marginMethod;

    /**
     * 保证金退回时间
     */
    private String backTimeDeposit;
    /**
     * 竞争对手数量
     */
    private Integer contendNum;
    /**
     * 是否进行项目测算
     */
    private Boolean projectEstimates;
    /**
     * 项目测算是否通过
     */
    private Boolean passProjectEstimates;
    /**
     * 规模数量
     */
    private Integer scale;
    /**
     * 是否转为商机
     */
    private Boolean opportunity;
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 备注
     */
    private String remark;

    public String getTenderType() {
        return tenderType;
    }

    public void setTenderType(String tenderType) {
        this.tenderType = tenderType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
}