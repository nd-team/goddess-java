package com.bjike.goddess.driverinfo.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.driverinfo.enums.DriverInfoType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * 司机信息数据库映射模型
 *
 * @Author: [Jason]
 * @Date: [17-3-8 上午9:21]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "driverinfo")
public class DriverInfo extends BaseEntity {
    /**
     * 数据状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '数据状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 审核人
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '审核人'")
    private String auditor;

    /**
     * 审核意见
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '审核意见'")
    private String auditIdea;

    /**
     * 地区
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '地区'")
    private String area;

    /**
     * 结算方式
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '结算方式'")
    private String way;

    /**
     * 司机名称
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '司机名称'")
    private String driverName;

    /**
     * 手机号码
     */
    @Column(columnDefinition = "VARCHAR(11) COMMENT '手机号码'")
    private String phoneNum;

    /**
     * 身份证
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '身份证'", unique = true, nullable = false)
    private String idCard;

    /**
     * 地址
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '地址'")
    private String address;

    /**
     * 车辆型号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '车辆型号'")
    private String carModel;

    /**
     * 车牌号码
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '车牌号码'")
    private String carNum;

    /**
     * 发动机号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '发动机号'")
    private String engineNum;

    /**
     * 购买时间
     */
    @Column(columnDefinition = "DATE  COMMENT '购买时间'")
    private LocalDate buyDate;

    /**
     * 使用时长
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '使用时长'")
    private String useDate;

    /**
     * 本车排放量
     */
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '本车排放量'")
    private Double emissions;

    /**
     * 本车耗油
     */
    @Column(columnDefinition = "DECIMAL(10,2) COMMENT '本车耗油'")
    private Double carFuel;

    /**
     * 是否签订租车协议
     */
    @Column(columnDefinition = "BIT(1) COMMENT '是否签订租车协议'", name = "isRentalAgreement")
    private Boolean rentalAgreement = Boolean.FALSE;

    /**
     * 协议签订日期
     */
    @Column(columnDefinition = "DATE  COMMENT '协议签订日期'")
    private LocalDate signDate;

    /**
     * 合同开始日期
     */
    @Column(columnDefinition = "DATE  COMMENT '合同开始日期'")
    private LocalDate agreementStartDate;

    /**
     * 合同到期日期
     */
    @Column(columnDefinition = "DATE  COMMENT '合同结束日期'")
    private LocalDate agreementEndDate;

    /**
     * 是否提供行驶证照片
     */
    @Column(columnDefinition = "BIT(1) COMMENT '是否提供行驶证照片'", name = "isTravel")
    private Boolean travel = Boolean.FALSE;

    /**
     * 行驶证所有者姓名
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '行驶证所有者姓名'")
    private String travelName;

    /**
     * 是否提供驾驶证照片
     */
    @Column(columnDefinition = "BIT(1) COMMENT '是否提供驾驶证照片'", name = "isDriverLicense")
    private Boolean driveLicense = Boolean.FALSE;

    /**
     * 驾驶证所有者姓名
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '驾驶证所有者姓名'")
    private String driveLicenseName;

    /**
     * 是否提供本车辆保险
     */
    @Column(columnDefinition = "BIT(1) COMMENT '是否提供驾驶证照片'", name = "isCarInsurance")
    private Boolean carInsurance = Boolean.FALSE;

    /**
     * 开户人
     */
    @Column(columnDefinition = "VARCHAR(18) COMMENT '开户人'")
    private String carUserName;

    /**
     * 银行卡号
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '银行卡号'")
    private String bankCardNum;

    /**
     * 银行名称
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '银行名称'")
    private String bankName;


    /**
     * 备注
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '备注'")
    private String remark;

    /**
     * 密码
     */
    @Column(columnDefinition = "VARCHAR(36) COMMENT '密码'")
    private String password;

    /**
     * 是否解约
     */
    @Column(columnDefinition = "BIT(1) COMMENT '是否解约'", name = "isBreakAgreement")
    private Boolean breakAgreement;

    /**
     * 是否联系
     */
    @Column(columnDefinition = "BIT(1) COMMENT '是否联系'", name = "isContact")
    private Boolean contact = Boolean.FALSE;

    /**
     * 是否备用
     */
    @Column(columnDefinition = "BIT(1) COMMENT '是否备用'", name = "isSpare")
    private Boolean spare = Boolean.FALSE;


    @Column(columnDefinition = "TINYINT(2) COMMENT '用户状态'", nullable = false)
    private DriverInfoType infoType;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditIdea() {
        return auditIdea;
    }

    public void setAuditIdea(String auditIdea) {
        this.auditIdea = auditIdea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
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

    public Boolean getRentalAgreement() {
        return rentalAgreement;
    }

    public void setRentalAgreement(Boolean rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public LocalDate getAgreementStartDate() {
        return agreementStartDate;
    }

    public void setAgreementStartDate(LocalDate agreementStartDate) {
        this.agreementStartDate = agreementStartDate;
    }

    public LocalDate getAgreementEndDate() {
        return agreementEndDate;
    }

    public void setAgreementEndDate(LocalDate agreementEndDate) {
        this.agreementEndDate = agreementEndDate;
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

    public Boolean getDriveLicense() {
        return driveLicense;
    }

    public void setDriveLicense(Boolean driveLicense) {
        this.driveLicense = driveLicense;
    }

    public String getDriveLicenseName() {
        return driveLicenseName;
    }

    public void setDriveLicenseName(String driveLicenseName) {
        this.driveLicenseName = driveLicenseName;
    }

    public Boolean getCarInsurance() {
        return carInsurance;
    }

    public void setCarInsurance(Boolean carInsurance) {
        this.carInsurance = carInsurance;
    }

    public String getCarUserName() {
        return carUserName;
    }

    public void setCarUserName(String carUserName) {
        this.carUserName = carUserName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBreakAgreement() {
        return breakAgreement;
    }

    public void setBreakAgreement(Boolean breakAgreement) {
        this.breakAgreement = breakAgreement;
    }

    public Boolean getContact() {
        return contact;
    }

    public void setContact(Boolean contact) {
        this.contact = contact;
    }

    public Boolean getSpare() {
        return spare;
    }

    public void setSpare(Boolean spare) {
        this.spare = spare;
    }

    public DriverInfoType getInfoType() {
        return infoType;
    }

    public void setInfoType(DriverInfoType infoType) {
        this.infoType = infoType;
    }
}
