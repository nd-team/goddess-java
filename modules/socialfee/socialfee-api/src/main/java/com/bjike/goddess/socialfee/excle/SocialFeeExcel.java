package com.bjike.goddess.socialfee.excle;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;


/**
 * 社会缴费导出
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-19 03:25 ]
 * @Description: [ 社会缴费 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */

public class SocialFeeExcel extends BaseTO {

    /**
     * 缴费所属年
     */
    @ExcelHeader(name = "缴费所属年")
    private String payTimeYear;

    /**
     * 缴费所属月
     */
    @ExcelHeader(name = "缴费所属月")
    private String payTimeMonth;



    /**
     * 纳税人名称
     */
    @ExcelHeader(name = "纳税人名称", notNull = true)
    private String payFeer;

    /**
     * 单位社保号
     */
    @ExcelHeader(name = "单位社保号", notNull = true)
    private String workSocalNum;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String empName;

    /**
     * 身份证明号码
     */
    @ExcelHeader(name = "身份证明号码", notNull = true)
    private String cardNum;

    /**
     * 证件号
     */
    @ExcelHeader(name = "证件号", notNull = true)
    private String identityNum;

    /**
     * 个人社保号
     */
    @ExcelHeader(name = "个人社保号")
    private String empSocalNum;

    /**
     * 基本养老保险计费工资
     */
    @ExcelHeader(name = "基本养老保险计费工资")
    private Double baseSalary;

    /**
     * 基本养老保险单位
     */
    @ExcelHeader(name = "基本养老保险单位")
    private Double baseWork;

    /**
     * 基本养老保险个人
     */
    @ExcelHeader(name = "基本养老保险个人")
    private Double baseEmp;

    /**
     * 工伤保险计费工资
     */
    @ExcelHeader(name = "工伤保险计费工资")
    private Double injurySalary;

    /**
     * 工伤保险单位
     */
    @ExcelHeader(name = "工伤保险单位")
    private Double injuryWork;

    /**
     * 工伤保险个人
     */
    @ExcelHeader(name = "工伤保险个人")
    private Double injuryEmp;

    /**
     * 失业保险计费工资
     */
    @ExcelHeader(name = "失业保险计费工资")
    private Double unemploySalary;

    /**
     * 失业保险单位
     */
    @ExcelHeader(name = "失业保险单位")
    private Double unemployWork;

    /**
     * 失业保险个人
     */
    @ExcelHeader(name = "失业保险个人")
    private Double unemployEmp;

    /**
     * 社会医疗保险计费工资
     */
    @ExcelHeader(name = "社会医疗保险计费工资")
    private Double socialMediSalary;

    /**
     * 社会医疗保险单位
     */
    @ExcelHeader(name = "社会医疗保险单位")
    private Double socialMediWork;

    /**
     * 社会医疗保险个人
     */
    @ExcelHeader(name = "社会医疗保险个人")
    private Double socialMediEmp;

    /**
     * 重大疾病医疗补助计费工资
     */
    @ExcelHeader(name = "重大疾病医疗补助计费工资")
    private Double illSalary;

    /**
     * 重大疾病医疗补助单位
     */
    @ExcelHeader(name = "重大疾病医疗补助单位")
    private Double illWork;

    /**
     * 重大疾病医疗补助个人
     */
    @ExcelHeader(name = "重大疾病医疗补助个人")
    private Double illEmp;

    /**
     * 生育保险计费工资
     */
    @ExcelHeader(name = "生育保险计费工资")
    private Double pregnantSalary;

    /**
     * 生育保险单位
     */
    @ExcelHeader(name = "生育保险单位")
    private Double pregnantWork;

    /**
     * 生育保险个人
     */
    @ExcelHeader(name = "生育保险个人")
    private Double pregnantEmp;



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



}