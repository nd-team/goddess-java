package com.bjike.goddess.driverinfo.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.driverinfo.enums.DriverInfoType;

import java.math.BigDecimal;

/**
 * 司机信息业务数据传输类
 *
 * @Author: [Jason]
 * @Date: [17-3-8 上午9:25]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DriverInfoBO extends BaseBO {

    /**
     * 数据状态
     */
    private Status status;

    /**
     * 审核人
     */
    private String auditor;

    /**
     * 审核意见
     */
    private String auditIdea;

    /**
     * 地区
     */
    private String area;

    /**
     * 结算方式
     */
    private String way;

    /**
     * 司机名称
     */
    private String driverName;

    /**
     * 手机号码
     */
    private String phoneNum;

    /**
     * 身份证
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
     * 购买时间
     */
    private String buyDate;

    /**
     * 使用时长
     */
    private String useDate;

    /**
     * 本车排放量
     */
    private BigDecimal emissions;

    /**
     * 本车耗油
     */
    private BigDecimal carFuel;

    /**
     * 是否签订租车协议
     */
    private Boolean rentalAgreement = Boolean.FALSE;

    /**
     * 协议签订日期
     */
    private String signDate;

    /**
     * 合同签订日期
     */
    private String agreementStartDate;

    /**
     * 合同到期日期
     */
    private String agreementEndDate;

    /**
     * 是否提供行驶证照片
     */
    private Boolean travel = Boolean.FALSE;

    /**
     * 行驶证所有者姓名
     */
    private String travelName;

    /**
     * 是否提供驾驶证照片
     */
    private Boolean driveLicense = Boolean.FALSE;

    /**
     * 驾驶证所有者姓名
     */
    private String driveLicenseName;

    /**
     * 是否提供本车辆保险
     */
    private Boolean carInsurance = Boolean.FALSE;

    /**
     * 开户人
     */
    private String carUserName;

    /**
     * 银行卡号
     */
    private String bankCardNum;

    /**
     * 银行名称
     */
    private String bankName;


    /**
     * 备注
     */
    private String remark;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否解约
     */
    private Boolean breakAgreement;

    /**
     * 是否联系
     */
    private Boolean contact = Boolean.FALSE;

    /**
     * 是否备用
     */
    private Boolean spare = Boolean.FALSE;

    /**
     * 创建时间
     */
    private String createTime;

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

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public BigDecimal getEmissions() {
        return emissions;
    }

    public void setEmissions(BigDecimal emissions) {
        this.emissions = emissions;
    }

    public BigDecimal getCarFuel() {
        return carFuel;
    }

    public void setCarFuel(BigDecimal carFuel) {
        this.carFuel = carFuel;
    }

    public Boolean getRentalAgreement() {
        return rentalAgreement;
    }

    public void setRentalAgreement(Boolean rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getAgreementStartDate() {
        return agreementStartDate;
    }

    public void setAgreementStartDate(String agreementStartDate) {
        this.agreementStartDate = agreementStartDate;
    }

    public String getAgreementEndDate() {
        return agreementEndDate;
    }

    public void setAgreementEndDate(String agreementEndDate) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public DriverInfoType getInfoType() {
        return infoType;
    }

    public void setInfoType(DriverInfoType infoType) {
        this.infoType = infoType;
    }
}
