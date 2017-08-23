package com.bjike.goddess.rentcar.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 租车协议管理
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:47 ]
 * @Description: [ 租车协议管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DriverInfoTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 结算方式
     */
    @NotBlank(message = "结算方式不能为空",groups = {ADD.class, EDIT.class})
    private String payWay;

    /**
     * 司机名称
     */
    @NotBlank(message = "司机名称不能为空",groups = {ADD.class, EDIT.class})
    private String driver;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空",groups = {ADD.class, EDIT.class})
    private String phone;

    /**
     * 身份证号
     */
    @NotBlank(message = "身份证号不能为空",groups = {ADD.class, EDIT.class})
    private String idCard;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空",groups = {ADD.class, EDIT.class})
    private String address;

    /**
     * 车辆型号
     */
    @NotBlank(message = "车辆型号不能为空",groups = {ADD.class, EDIT.class})
    private String carModel;

    /**
     * 车牌号码
     */
    @NotBlank(message = "车牌号码不能为空",groups = {ADD.class, EDIT.class})
    private String carNum;

    /**
     * 发动机号
     */
    @NotBlank(message = "发动机号不能为空",groups = {ADD.class, EDIT.class})
    private String engineNum;

    /**
     * 汽车购买时间
     */
    @NotBlank(message = "汽车购买时间不能为空",groups = {ADD.class, EDIT.class})
    private String buyDate;

    /**
     * 汽车使用时长
     */
    @NotNull(message = "汽车使用时长不能为空",groups = {ADD.class, EDIT.class})
    private Double useTime;

    /**
     * 汽车排量
     */
    @NotNull(message = "汽车排量不能为空",groups = {ADD.class, EDIT.class})
    private Double emissions;

    /**
     * 本车耗油
     */
    @NotNull(message = "本车耗油不能为空",groups = {ADD.class, EDIT.class})
    private Double carFuel;

    /**
     * 是否签订租车协议
     */
    @NotNull(message = "是否签订租车协议不能为空",groups = {ADD.class, EDIT.class})
    private Boolean agreement;

    /**
     * 是否提供协议附件
     */
    @NotNull(message = "是否提供协议附件不能为空",groups = {ADD.class, EDIT.class})
    private Boolean attachment;

    /**
     * 签订日期
     */
    @NotBlank(message = "签订日期不能为空",groups = {ADD.class, EDIT.class})
    private String signDate;

    /**
     * 合同开始日期
     */
    @NotBlank(message = "合同开始日期不能为空",groups = {ADD.class, EDIT.class})
    private String startDate;

    /**
     * 合同终止日期
     */
    @NotBlank(message = "合同终止日期不能为空",groups = {ADD.class, EDIT.class})
    private String endDate;

    /**
     * 是否提供行驶证照片
     */
    @NotNull(message = "是否提供行驶证照片不能为空",groups = {ADD.class, EDIT.class})
    private Boolean travel;

    /**
     * 行驶证所有者
     */
    @NotBlank(message = "行驶证所有者不能为空",groups = {ADD.class, EDIT.class})
    private String travelName;

    /**
     * 是否提供驾驶证照片
     */
    @NotNull(message = "是否提供驾驶证照片不能为空",groups = {ADD.class, EDIT.class})
    private Boolean driverLicense;

    /**
     * 驾驶证所有者
     */
    @NotBlank(message = "驾驶证所有者不能为空",groups = {ADD.class, EDIT.class})
    private String licenseName;

    /**
     * 是否提供本车辆保险
     */
    @NotNull(message = "是否提供本车辆保险不能为空",groups = {ADD.class, EDIT.class})
    private Boolean carInsurance;

    /**
     * 开户人
     */
    @NotBlank(message = "开户人不能为空",groups = {ADD.class, EDIT.class})
    private String cardUser;

    /**
     * 开户账号
     */
    @NotBlank(message = "开户账号不能为空",groups = {ADD.class, EDIT.class})
    private String cardNum;

    /**
     * 开户行
     */
    @NotBlank(message = "开户行不能为空",groups = {ADD.class, EDIT.class})
    private String cardBank;

    /**
     * 是否解约
     */
    private Boolean breakAgreement;

    /**
     * 备注
     */
    private String remark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getEngineNum() {
        return engineNum;
    }

    public void setEngineNum(String engineNum) {
        this.engineNum = engineNum;
    }

    public Double getUseTime() {
        return useTime;
    }

    public void setUseTime(Double useTime) {
        this.useTime = useTime;
    }

    public Double getEmissions() {
        return emissions;
    }

    public void setEmissions(Double emissions) {
        this.emissions = emissions;
    }

    public Double getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(Double carFuel) {
        this.carFuel = carFuel;
    }

    public Boolean getAgreement() {
        return agreement;
    }

    public void setAgreement(Boolean agreement) {
        this.agreement = agreement;
    }

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean getTravel() {
        return travel;
    }

    public void setTravel(Boolean travel) {
        this.travel = travel;
    }

    public String getTravelName() {
        return travelName;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public Boolean getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(Boolean driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public Boolean getCarInsurance() {
        return carInsurance;
    }

    public void setCarInsurance(Boolean carInsurance) {
        this.carInsurance = carInsurance;
    }

    public String getCardUser() {
        return cardUser;
    }

    public void setCardUser(String cardUser) {
        this.cardUser = cardUser;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public Boolean getBreakAgreement() {
        return breakAgreement;
    }

    public void setBreakAgreement(Boolean breakAgreement) {
        this.breakAgreement = breakAgreement;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}