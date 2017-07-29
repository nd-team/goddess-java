package com.bjike.goddess.bidding.bo;

import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import org.apache.commons.lang3.StringUtils;
import scala.util.parsing.combinator.testing.Str;

/**
 * 招标信息业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.947 ]
 * @Description: [ 招标信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingInfoBO extends BaseBO {
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
     * 招投标类型
     */
    private BiddingType biddingType;

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    private String businessDirectionSubject;

    /**
     * 标书类型
     */
    private String tenderModule;

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
     * 价格
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
     * 交保证金方式
     */
    private String marginMethod;

    /**
     * 保证金退回时间
     */
    private String backTimeDeposit;

    /**
     * 备注
     */
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

    public String getBusinessDirectionSubject() {
        return businessDirectionSubject;
    }

    public void setBusinessDirectionSubject(String businessDirectionSubject) {
        this.businessDirectionSubject = businessDirectionSubject;
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