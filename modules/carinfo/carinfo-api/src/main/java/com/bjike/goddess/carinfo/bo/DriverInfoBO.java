package com.bjike.goddess.carinfo.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 车辆信息管理业务传输对象
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 07:46 ]
 * @Description: [ 车辆信息管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DriverInfoBO extends BaseBO {

//    /**
//     * 账务模块审核意见
//     */
//    private String suggest;
//
//    /**
//     * 审核结果
//     */
//    private Boolean audit;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组
     */
    private String department;


    /**
     * 结算方式
     */
    private String payWay;

    /**
     * 司机名称
     */
    private String driver;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 地址
     */
    private String address;

    /**
     * 车辆型号
     */
    private String carModel;

    /**
     * 车牌号码
     */
    private String carNum;

    /**
     * 发动机号
     */
    private String engineNum;

    /**
     * 汽车购买时间
     */
    private String buyDate;

    /**
     * 汽车使用时长
     */
    private Double useTime;

    /**
     * 汽车排量
     */
    private Double emissions;

    /**
     * 本车耗油
     */
    private Double carFuel;

    /**
     * 是否签订租车协议
     */
    private Boolean agreement;

    /**
     * 是否提供协议附件
     */
    private Boolean attachment;

    /**
     * 签订日期
     */
    private String signDate;

    /**
     * 合同开始日期
     */
    private String startDate;

    /**
     * 合同终止日期
     */
    private String endDate;

    /**
     * 是否提供行驶证照片
     */
    private Boolean travel;

    /**
     * 行驶证所有者
     */
    private String travelName;

    /**
     * 是否提供驾驶证照片
     */
    private Boolean driverLicense;

    /**
     * 驾驶证所有者
     */
    private String licenseName;

    /**
     * 是否提供本车辆保险
     */
    private Boolean carInsurance;

    /**
     * 开户人
     */
    private String cardUser;

    /**
     * 开户账号
     */
    private String cardNum;

    /**
     * 开户行
     */
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


}