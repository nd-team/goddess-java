package com.bjike.goddess.bidding.to;

import com.bjike.goddess.bidding.enums.BiddingType;
import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.api.type.Status;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 招标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T13:48:29.965 ]
 * @Description: [ 招标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class BiddingInfoTO extends BaseTO {

    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空",groups = {EDIT.class})
    private String biddingNumber;
    /**
     * 网站名称
     */
    @NotBlank(message = "网站名称不能为空",groups = {ADD.class, EDIT.class})
    private String webName;

    /**
     * 网址
     */
    @NotBlank(message = "网址不能为空",groups = {ADD.class, EDIT.class})
    private String url;

    /**
     * 招投标类型
     */
    @NotNull(message = "招投标类型不能为空",groups = {ADD.class, EDIT.class})
    private BiddingType biddingType;

    /**
     * 业务类型
     */
    @NotNull(message = "业务类型不能为空",groups = {ADD.class, EDIT.class})
    private BusinessType businessType;

    /**
     * 业务方向科目
     */
    @NotBlank(message = "业务方向科目不能为空",groups = {ADD.class, EDIT.class})
    private String businessDirectionSubject;

    /**
     * 标书类型
     */
    @NotNull(message = "标书类型不能为空",groups = {ADD.class, EDIT.class})
    private String[] tenderModule;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空",groups = {ADD.class, EDIT.class})
    private String projectName;

    /**
     * 报名开始时间
     */
    @NotBlank(message = "报名开始时间不能为空",groups = {ADD.class, EDIT.class})
    private String registrationStartTime;
    /**
     * 报名结束时间
     */
    @NotBlank(message = "报名结束时间不能为空",groups = {ADD.class, EDIT.class})
    private String registrationEndTime;
    /**
     * 投标时间
     */
    @NotBlank(message = "投标时间不能为空",groups = {ADD.class, EDIT.class})
    private String biddingTime;

    /**
     * 投标资格要求
     */
    @NotBlank(message = "投标资格要求不能为空",groups = {ADD.class, EDIT.class})
    private String biddingQualifications;

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空",groups = {ADD.class, EDIT.class})
    private String provinces;

    /**
     * 地市
     */
    @NotBlank(message = "地市不能为空",groups = {ADD.class, EDIT.class})
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
    @NotBlank(message = "购买标书时间不能为空",groups = {ADD.class, EDIT.class})
    private String buyTenderTime;

    /**
     * 价格
     */
    @NotNull(message = "价格不能为空",groups = {ADD.class, EDIT.class})
    private Double price;

    /**
     * 购买标书要求
     */
    @NotBlank(message = "购买标书要求不能为空",groups = {ADD.class, EDIT.class})
    private String buyTenderRequirements;

    /**
     * 交保证金时间
     */
    @NotBlank(message = "交保证金时间不能为空",groups = {ADD.class, EDIT.class})
    private String marginTime;

    /**
     * 交保证金方式
     */
    @NotBlank(message = "交保证金方式不能为空",groups = {ADD.class, EDIT.class})
    private String marginMethod;

    /**
     * 保证金退回时间
     */
    @NotBlank(message = "保证金退回时间不能为空",groups = {ADD.class, EDIT.class})
    private String backTimeDeposit;

    /**
     * 备注
     */
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

    public String[] getTenderModule() {
        return tenderModule;
    }

    public void setTenderModule(String[] tenderModule) {
        this.tenderModule = tenderModule;
    }

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
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