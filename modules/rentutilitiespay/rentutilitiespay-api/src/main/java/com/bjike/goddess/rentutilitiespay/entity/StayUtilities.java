package com.bjike.goddess.rentutilitiespay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.rentutilitiespay.enums.StaffVerify;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 员工住宿水电费
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-13 07:44 ]
 * @Description: [ 员工住宿水电费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "rentutilitiespay_stayutilities")
public class StayUtilities extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 住宿地址
     */
    @Column(name = "address", columnDefinition = "VARCHAR(255)   COMMENT '住宿地址'")
    private String address;

    /**
     * 员工姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '员工姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "num", columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String num;

    /**
     * 住宿开始时间
     */
    @Column(name = "stayStartTime", columnDefinition = "DATE   COMMENT '住宿开始时间'")
    private LocalDate stayStartTime;

    /**
     * 住宿结束时间
     */
    @Column(name = "stayEndTime", columnDefinition = "DATE   COMMENT '住宿结束时间'")
    private LocalDate stayEndTime;

    /**
     * 住宿天数
     */
    @Column(name = "stayDay", columnDefinition = "DECIMAL(10,2)   COMMENT '住宿天数'")
    private Double stayDay;
    /**
     * 房租
     */
    @Column(name = "rent", columnDefinition = "DECIMAL(10,2)   COMMENT '房租'")
    private Double rent;
    /**
     * 房租公司缴纳
     */
    @Column(name = "rentCompanyPay", columnDefinition = "DECIMAL(10,2)   COMMENT '房租公司缴纳'")
    private Double rentCompanyPay;

    /**
     * 房租员工缴纳
     */
    @Column(name = "rentStaffPay", columnDefinition = "DECIMAL(10,2)   COMMENT '房租员工缴纳'")
    private Double rentStaffPay;

    /**
     * 当月应缴水费总额
     */
    @Column(name = "waterAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '当月应缴水费总额'")
    private Double waterAmount;

    /**
     * 水费公司缴纳
     */
    @Column(name = "waterCompanyPay", columnDefinition = "DECIMAL(10,2)   COMMENT '水费公司缴纳'")
    private Double waterCompanyPay;

    /**
     * 水费员工缴纳（(当月应缴水费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
     */
    @Column(name = "waterStaffPay", columnDefinition = "DECIMAL(10,2)   COMMENT '水费员工缴纳'")
    private Double waterStaffPay;

    /**
     * 水费员工预缴
     */
    @Column(name = "waterStaffPrepay", columnDefinition = "DECIMAL(10,2)   COMMENT '水费员工预缴'")
    private Double waterStaffPrepay;


    /**
     * 当月应缴电费总额
     */
    @Column(name = "energyAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '当月应缴电费总额'")
    private Double energyAmount;

    /**
     * 电费公司缴纳
     */
    @Column(name = "energyCompanyPay", columnDefinition = "DECIMAL(10,2)   COMMENT '电费公司缴纳'")
    private Double energyCompanyPay;

    /**
     * 电费员工预缴
     */
    @Column(name = "energyStaffPrepay", columnDefinition = "DECIMAL(10,2)   COMMENT '电费员工预缴'")
    private Double energyStaffPrepay;

    /**
     * 电费员工缴纳（(当月应缴电费总额/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
     */
    @Column(name = "energyStaffPay", columnDefinition = "DECIMAL(10,2)   COMMENT '电费员工缴纳'")
    private Double energyStaffPay;

    /**
     * 管道燃气费充值额度
     */
    @Column(name = "gasRechargeLines", columnDefinition = "DECIMAL(10,2)   COMMENT '管道燃气费充值额度'")
    private Double gasRechargeLines;

    /**
     * 燃气费公司缴纳
     */
    @Column(name = "gasCompanyPay", columnDefinition = "DECIMAL(10,2)   COMMENT '燃气费公司缴纳'")
    private Double gasCompanyPay;

    /**
     * 燃气费员工预缴
     */
    @Column(name = "gasStaffPrepay", columnDefinition = "DECIMAL(10,2)   COMMENT '燃气费员工预缴'")
    private Double gasStaffPrepay;

    /**
     * 燃气费员工缴纳（(管道燃气费充值额度/同一住宿地址员工住宿天数总和)*个人员工住宿天数）
     */
    @Column(name = "gasStaffPay", columnDefinition = "DECIMAL(10,2)   COMMENT '燃气费员工缴纳'")
    private Double gasStaffPay;

    /**
     * 员工应缴金额汇总（水费员工缴纳+电费员工缴纳+燃气费员工缴纳）
     */
    @Column(name = "staffPayCollect", columnDefinition = "DECIMAL(10,2)   COMMENT '员工应缴金额汇总（水费员工缴纳+电费员工缴纳+燃气费员工缴纳）'")
    private Double staffPayCollect;

    /**
     * 员工核实
     */
    @Column(name = "staffVerify", columnDefinition = "INT(2)   COMMENT '员工核实'")
    private StaffVerify staffVerify;
    /**
     * 综合资源部核实情况（是否需要修改）
     */
    @Column(name = "is_comprehensiveVerifySituation", columnDefinition = "TINYINT(2) COMMENT '综合资源部核实情况（是否需要修改）'")
    private Boolean comprehensiveVerifySituation;

    /**
     * 扣款情况（运营商务部确认）
     */
    @Column(name = "deductionSituation", columnDefinition = "VARCHAR(255)   COMMENT '扣款情况（运营商务部确认）'")
    private String deductionSituation;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    private Integer sumDays;//天数总和
    private Integer personalDays;//个人天数

    public Integer getPersonalDays() {
        return personalDays;
    }

    public void setPersonalDays(Integer personalDays) {
        this.personalDays = personalDays;
    }

    public Integer getSumDays() {
        return sumDays;
    }

    public void setSumDays(Integer sumDays) {
        this.sumDays = sumDays;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public LocalDate getStayStartTime() {
        return stayStartTime;
    }

    public void setStayStartTime(LocalDate stayStartTime) {
        this.stayStartTime = stayStartTime;
    }

    public LocalDate getStayEndTime() {
        return stayEndTime;
    }

    public void setStayEndTime(LocalDate stayEndTime) {
        this.stayEndTime = stayEndTime;
    }

    public Double getStayDay() {
        return stayDay;
    }

    public void setStayDay(Double stayDay) {
        this.stayDay = stayDay;
    }

    public Double getRentCompanyPay() {
        return rentCompanyPay;
    }

    public void setRentCompanyPay(Double rentCompanyPay) {
        this.rentCompanyPay = rentCompanyPay;
    }

    public Double getRentStaffPay() {
        return rentStaffPay;
    }

    public void setRentStaffPay(Double rentStaffPay) {
        this.rentStaffPay = rentStaffPay;
    }

    public Double getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(Double waterAmount) {
        this.waterAmount = waterAmount;
    }

    public Double getWaterCompanyPay() {
        return waterCompanyPay;
    }

    public void setWaterCompanyPay(Double waterCompanyPay) {
        this.waterCompanyPay = waterCompanyPay;
    }

    public Double getWaterStaffPay() {
        return waterStaffPay;
    }

    public void setWaterStaffPay(Double waterStaffPay) {
        this.waterStaffPay = waterStaffPay;
    }

    public Double getEnergyAmount() {
        return energyAmount;
    }

    public void setEnergyAmount(Double energyAmount) {
        this.energyAmount = energyAmount;
    }

    public Double getEnergyCompanyPay() {
        return energyCompanyPay;
    }

    public void setEnergyCompanyPay(Double energyCompanyPay) {
        this.energyCompanyPay = energyCompanyPay;
    }

    public Double getEnergyStaffPay() {
        return energyStaffPay;
    }

    public void setEnergyStaffPay(Double energyStaffPay) {
        this.energyStaffPay = energyStaffPay;
    }

    public Double getGasRechargeLines() {
        return gasRechargeLines;
    }

    public void setGasRechargeLines(Double gasRechargeLines) {
        this.gasRechargeLines = gasRechargeLines;
    }

    public Double getGasCompanyPay() {
        return gasCompanyPay;
    }

    public void setGasCompanyPay(Double gasCompanyPay) {
        this.gasCompanyPay = gasCompanyPay;
    }

    public Double getGasStaffPay() {
        return gasStaffPay;
    }

    public void setGasStaffPay(Double gasStaffPay) {
        this.gasStaffPay = gasStaffPay;
    }

    public Double getStaffPayCollect() {
        return staffPayCollect;
    }

    public void setStaffPayCollect(Double staffPayCollect) {
        this.staffPayCollect = staffPayCollect;
    }

    public StaffVerify getStaffVerify() {
        return staffVerify;
    }

    public void setStaffVerify(StaffVerify staffVerify) {
        this.staffVerify = staffVerify;
    }

    public Boolean getComprehensiveVerifySituation() {
        return comprehensiveVerifySituation;
    }

    public void setComprehensiveVerifySituation(Boolean comprehensiveVerifySituation) {
        this.comprehensiveVerifySituation = comprehensiveVerifySituation;
    }

    public String getDeductionSituation() {
        return deductionSituation;
    }

    public void setDeductionSituation(String deductionSituation) {
        this.deductionSituation = deductionSituation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}