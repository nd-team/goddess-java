package com.bjike.goddess.carinfo.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 司机招聘信息业务传输对象
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 08:27 ]
 * @Description: [ 司机招聘信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DriverRecruitBO extends BaseBO {

    /**
     * 账务模块审核意见
     */
    private String suggest;

    /**
     * 地区
     */
    private String area;

    /**
     * 审核结果
     */
    private Boolean aduit;

    /**
     * 司机名称
     */
    private String driver;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 是否联系
     */
    private Boolean contact;

    /**
     * 是否可备用
     */
    private Boolean backup;

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
     * 备注
     */
    private String remark;


    public Boolean getAduit() {
        return aduit;
    }

    public void setAduit(Boolean aduit) {
        this.aduit = aduit;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Boolean getContact() {
        return contact;
    }

    public void setContact(Boolean contact) {
        this.contact = contact;
    }

    public Boolean getBackup() {
        return backup;
    }

    public void setBackup(Boolean backup) {
        this.backup = backup;
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

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}