package com.bjike.goddess.carinfo.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 司机招聘信息
 *
 * @Author: [ jason ]
 * @Date: [ 2017-07-13 08:27 ]
 * @Description: [ 司机招聘信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DriverRecruitTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组
     */
    @NotBlank(message = "项目组不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 信息收集时间
     */
    @NotBlank(message = "信息收集时间不能为空",groups = {ADD.class, EDIT.class})
    private String informationCollecttionTime;

    /**
     * 是否确定签订协议
     */
    @NotNull(message = "是否确定签订协议不能为空",groups = {ADD.class,EDIT.class})
    private Boolean enSureAgreement;

    /**
     * 结算方式
     */
    @NotBlank(message = "结算方式不能为空",groups = {ADD.class,EDIT.class})
    private String meansOfPayments;


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
     * 是否联系
     */
    @NotNull(message = "是否联系不能为空",groups = {ADD.class, EDIT.class})
    private Boolean contact;

    /**
     * 是否可备用
     */
    @NotNull(message = "是否可备用不能为空",groups = {ADD.class, EDIT.class})
    private Boolean backup;

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
     * 备注
     */
    private String remark;


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


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getInformationCollecttionTime() {
        return informationCollecttionTime;
    }

    public void setInformationCollecttionTime(String informationCollecttionTime) {
        this.informationCollecttionTime = informationCollecttionTime;
    }

    public Boolean getEnSureAgreement() {
        return enSureAgreement;
    }

    public void setEnSureAgreement(Boolean enSureAgreement) {
        this.enSureAgreement = enSureAgreement;
    }

    public String getMeansOfPayments() {
        return meansOfPayments;
    }

    public void setMeansOfPayments(String meansOfPayments) {
        this.meansOfPayments = meansOfPayments;
    }
}