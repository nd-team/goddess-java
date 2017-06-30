package com.bjike.goddess.socialfee.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 社会缴费
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "socialfee_socialfee")
public class SocialFee extends BaseEntity {

    /**
     * 缴费所属年
     */
    @Column(name = "payTimeYear",  columnDefinition = "VARCHAR(255)   COMMENT '缴费所属年'")
    private String payTimeYear;

    /**
     * 缴费所属月
     */
    @Column(name = "payTimeMonth",  columnDefinition = "VARCHAR(255)   COMMENT '缴费所属月'")
    private String payTimeMonth;

    /**
     * 缴费所属时期
     */
    @Column(name = "payTime", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '缴费所属时期'")
    private String payTime;

    /**
     * 纳税人名称
     */
    @Column(name = "payFeer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '纳税人名称'")
    private String payFeer;

    /**
     * 单位社保号
     */
    @Column(name = "workSocalNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '单位社保号'")
    private String workSocalNum;

    /**
     * 姓名
     */
    @Column(name = "empName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String empName;

    /**
     * 身份证明号码
     */
    @Column(name = "cardNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '身份证明号码'")
    private String cardNum;

    /**
     * 证件号
     */
    @Column(name = "identityNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证件号'")
    private String identityNum;

    /**
     * 个人社保号
     */
    @Column(name = "empSocalNum",  columnDefinition = "VARCHAR(255)   COMMENT '个人社保号'")
    private String empSocalNum;

    /**
     * 基本养老保险计费工资
     */
    @Column(name = "baseSalary",  columnDefinition = "DECIMAL(10,2)   COMMENT '基本养老保险计费工资'")
    private Double baseSalary;

    /**
     * 基本养老保险单位
     */
    @Column(name = "baseWork",  columnDefinition = "DECIMAL(10,2)   COMMENT '基本养老保险单位'")
    private Double baseWork;

    /**
     * 基本养老保险个人
     */
    @Column(name = "baseEmp",  columnDefinition = "DECIMAL(10,2)   COMMENT '基本养老保险个人'")
    private Double baseEmp;

    /**
     * 工伤保险计费工资
     */
    @Column(name = "injurySalary",  columnDefinition = "DECIMAL(10,2)   COMMENT '工伤保险计费工资'")
    private Double injurySalary;

    /**
     * 工伤保险单位
     */
    @Column(name = "injuryWork",  columnDefinition = "DECIMAL(10,2)   COMMENT '工伤保险单位'")
    private Double injuryWork;

    /**
     * 工伤保险个人
     */
    @Column(name = "injuryEmp",  columnDefinition = "DECIMAL(10,2)   COMMENT '工伤保险个人'")
    private Double injuryEmp;

    /**
     * 失业保险计费工资
     */
    @Column(name = "unemploySalary",  columnDefinition = "DECIMAL(10,2)   COMMENT '失业保险计费工资'")
    private Double unemploySalary;

    /**
     * 失业保险单位
     */
    @Column(name = "unemployWork",  columnDefinition = "DECIMAL(10,2)   COMMENT '失业保险单位'")
    private Double unemployWork;

    /**
     * 失业保险个人
     */
    @Column(name = "unemployEmp",  columnDefinition = "DECIMAL(10,2)   COMMENT '失业保险个人'")
    private Double unemployEmp;

    /**
     * 社会医疗保险计费工资
     */
    @Column(name = "socialMediSalary",  columnDefinition = "DECIMAL(10,2)   COMMENT '社会医疗保险计费工资'")
    private Double socialMediSalary;

    /**
     * 社会医疗保险单位
     */
    @Column(name = "socialMediWork",  columnDefinition = "DECIMAL(10,2)   COMMENT '社会医疗保险单位'")
    private Double socialMediWork;

    /**
     * 社会医疗保险个人
     */
    @Column(name = "socialMediEmp",  columnDefinition = "DECIMAL(10,2)   COMMENT '社会医疗保险个人'")
    private Double socialMediEmp;

    /**
     * 重大疾病医疗补助计费工资
     */
    @Column(name = "illSalary",  columnDefinition = "DECIMAL(10,2)   COMMENT '重大疾病医疗补助计费工资'")
    private Double illSalary;

    /**
     * 重大疾病医疗补助单位
     */
    @Column(name = "illWork",  columnDefinition = "DECIMAL(10,2)   COMMENT '重大疾病医疗补助单位'")
    private Double illWork;

    /**
     * 重大疾病医疗补助个人
     */
    @Column(name = "illEmp",  columnDefinition = "DECIMAL(10,2)   COMMENT '重大疾病医疗补助个人'")
    private Double illEmp;

    /**
     * 生育保险计费工资
     */
    @Column(name = "pregnantSalary",  columnDefinition = "DECIMAL(10,2)   COMMENT '生育保险计费工资'")
    private Double pregnantSalary;

    /**
     * 生育保险单位
     */
    @Column(name = "pregnantWork",  columnDefinition = "DECIMAL(10,2)   COMMENT '生育保险单位'")
    private Double pregnantWork;

    /**
     * 生育保险个人
     */
    @Column(name = "pregnantEmp", columnDefinition = "DECIMAL(10,2)   COMMENT '生育保险个人'")
    private Double pregnantEmp;

    /**
     * 单位合计
     */
    @Column(name = "workMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '单位合计'")
    private Double workMoney;

    /**
     * 个人合计
     */
    @Column(name = "empMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '个人合计'")
    private Double empMoney;

    /**
     * 应缴金额
     */
    @Column(name = "totalMoney",  columnDefinition = "DECIMAL(10,2)   COMMENT '应缴金额'")
    private Double totalMoney;






    public String getPayTimeYear() {
        return payTimeYear;
    }

    public void setPayTimeYear(String payTimeYear) {
        this.payTimeYear = payTimeYear;
    }

    public String getPayTimeMonth() {
        return payTimeMonth;
    }

    public void setPayTimeMonth(String payTimeMonth) {
        this.payTimeMonth = payTimeMonth;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayFeer() {
        return payFeer;
    }

    public void setPayFeer(String payFeer) {
        this.payFeer = payFeer;
    }

    public String getWorkSocalNum() {
        return workSocalNum;
    }

    public void setWorkSocalNum(String workSocalNum) {
        this.workSocalNum = workSocalNum;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getEmpSocalNum() {
        return empSocalNum;
    }

    public void setEmpSocalNum(String empSocalNum) {
        this.empSocalNum = empSocalNum;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getBaseWork() {
        return baseWork;
    }

    public void setBaseWork(Double baseWork) {
        this.baseWork = baseWork;
    }

    public Double getBaseEmp() {
        return baseEmp;
    }

    public void setBaseEmp(Double baseEmp) {
        this.baseEmp = baseEmp;
    }

    public Double getInjurySalary() {
        return injurySalary;
    }

    public void setInjurySalary(Double injurySalary) {
        this.injurySalary = injurySalary;
    }

    public Double getInjuryWork() {
        return injuryWork;
    }

    public void setInjuryWork(Double injuryWork) {
        this.injuryWork = injuryWork;
    }

    public Double getInjuryEmp() {
        return injuryEmp;
    }

    public void setInjuryEmp(Double injuryEmp) {
        this.injuryEmp = injuryEmp;
    }

    public Double getUnemploySalary() {
        return unemploySalary;
    }

    public void setUnemploySalary(Double unemploySalary) {
        this.unemploySalary = unemploySalary;
    }

    public Double getUnemployWork() {
        return unemployWork;
    }

    public void setUnemployWork(Double unemployWork) {
        this.unemployWork = unemployWork;
    }

    public Double getUnemployEmp() {
        return unemployEmp;
    }

    public void setUnemployEmp(Double unemployEmp) {
        this.unemployEmp = unemployEmp;
    }

    public Double getSocialMediSalary() {
        return socialMediSalary;
    }

    public void setSocialMediSalary(Double socialMediSalary) {
        this.socialMediSalary = socialMediSalary;
    }

    public Double getSocialMediWork() {
        return socialMediWork;
    }

    public void setSocialMediWork(Double socialMediWork) {
        this.socialMediWork = socialMediWork;
    }

    public Double getSocialMediEmp() {
        return socialMediEmp;
    }

    public void setSocialMediEmp(Double socialMediEmp) {
        this.socialMediEmp = socialMediEmp;
    }

    public Double getIllSalary() {
        return illSalary;
    }

    public void setIllSalary(Double illSalary) {
        this.illSalary = illSalary;
    }

    public Double getIllWork() {
        return illWork;
    }

    public void setIllWork(Double illWork) {
        this.illWork = illWork;
    }

    public Double getIllEmp() {
        return illEmp;
    }

    public void setIllEmp(Double illEmp) {
        this.illEmp = illEmp;
    }

    public Double getPregnantSalary() {
        return pregnantSalary;
    }

    public void setPregnantSalary(Double pregnantSalary) {
        this.pregnantSalary = pregnantSalary;
    }

    public Double getPregnantWork() {
        return pregnantWork;
    }

    public void setPregnantWork(Double pregnantWork) {
        this.pregnantWork = pregnantWork;
    }

    public Double getPregnantEmp() {
        return pregnantEmp;
    }

    public void setPregnantEmp(Double pregnantEmp) {
        this.pregnantEmp = pregnantEmp;
    }

    public Double getWorkMoney() {
        return workMoney;
    }

    public void setWorkMoney(Double workMoney) {
        this.workMoney = workMoney;
    }

    public Double getEmpMoney() {
        return empMoney;
    }

    public void setEmpMoney(Double empMoney) {
        this.empMoney = empMoney;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }



}