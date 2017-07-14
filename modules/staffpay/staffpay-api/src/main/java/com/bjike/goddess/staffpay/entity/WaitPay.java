package com.bjike.goddess.staffpay.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.staffpay.enums.ConfirmStatus;
import com.bjike.goddess.staffpay.enums.FindType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 等待付款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 11:38 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffpay_waitpay")
public class WaitPay extends BaseEntity {

    /**
     * 月份
     */
    @Column( nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '月份'")
    private Integer months;

    /**
     * 计薪周期（开始）
     */
    @Column(name = "startPaidCycle",  columnDefinition = "DATE   COMMENT '计薪周期（开始）'")
    private LocalDate startPaidCycle;

    /**
     * 计薪周期（结束）
     */
    @Column(name = "endPaidCycle",  columnDefinition = "DATE   COMMENT '计薪周期（结束）'")
    private LocalDate endPaidCycle;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 部门
     */
    @Column(name = "department",  columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "jobs", columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String jobs;

    /**
     * 出勤天数
     */
    @Column(name = "attendanceDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出勤天数'")
    private Double attendanceDay;

    /**
     * 员工工资
     */
    @Column(name = "staffWage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '员工工资'")
    private Double staffWage;

    /**
     * 电脑补助
     */
    @Column(name = "computerHelp", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '电脑补助'")
    private Double computerHelp;

    /**
     * 住宿补助
     */
    @Column(name = "stayHelp", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '住宿补助'")
    private Double stayHelp;

    /**
     * 工龄补助
     */
    @Column(name = "lengthHelp", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '工龄补助'")
    private Double lengthHelp;

    /**
     * 高温补助
     */
    @Column(name = "hyperthermiaHelp", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '高温补助'")
    private Double hyperthermiaHelp;


    /**
     * 社保补助
     */
    @Column(name = "socialHelp", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '社保补助'")
    private Double socialHelp;

    /**
     * 其他费用
     */
    @Column(name = "otherFee", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '其他费用'")
    private Double otherFee;

    /**
     * 工资总额
     */
    @Column(name = "totalWages", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '工资总额'")
    private Double totalWages;

    /**
     * 假期加班费
     */
    @Column(name = "holidayOvertimePay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '假期加班费'")
    private Double holidayOvertimePay;

    /**
     * 社保扣款
     */
    @Column(name = "socialDeduction", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '社保扣款'")
    private Double socialDeduction;

    /**
     * 水电费扣款
     */
    @Column(name = "utilitiesDeduction", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '水电费扣款'")
    private Double utilitiesDeduction;

    /**
     * 扣分合计
     */
    @Column(name = "deductionTotal", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '扣分合计'")
    private Double deductionTotal;

    /**
     * 奖金处罚扣款
     */
    @Column(name = "bonusPenaltyDeduction", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '奖金处罚扣款'")
    private Double bonusPenaltyDeduction;

    /**
     * 个税扣款
     */
    @Column(name = "incomeTaxDeduction", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '个税扣款'")
    private Double incomeTaxDeduction;

    /**
     * 事病假扣款
     */
    @Column(name = "whatSickLeaveDeduction", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '事病假扣款'")
    private Double whatSickLeaveDeduction;

    /**
     * 旷工扣款
     */
    @Column(name = "absenteeismDeduction", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '旷工扣款'")
    private Double absenteeismDeduction;

    /**
     * 实发工资
     */
    @Column(name = "realWages", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实发工资'")
    private Double realWages;

    /**
     * 是否缴纳个人所得税
     */
    @Column(name = "is_incomeTax", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否缴纳个人所得税'", insertable = false)
    private Boolean incomeTax;

    /**
     * 本月第一次发工资
     */
    @Column(name = "firstSalary", columnDefinition = "DECIMAL(10,2)   COMMENT '本月第一次发工资'")
    private Double firstSalary;

    /**
     * 本月第二次发工资
     */
    @Column(name = "secondSalary", columnDefinition = "DECIMAL(10,2)   COMMENT '本月第二次发工资'")
    private Double secondSalary;

    /**
     * 本月需缴发票金额
     */
    @Column(name = "invoiceValue", columnDefinition = "DECIMAL(10,2)   COMMENT '本月需缴发票金额'")
    private Double invoiceValue;

    /**
     * 累计已缴发票金额
     */
    @Column(name = "paidInvoiceAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '累计已缴发票金额'")
    private Double paidInvoiceAmount;

    /**
     * 是否确认第一次工资发放记录
     */
    @Column(name = "confirmFirstSalary", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '是否确认第一次工资发放记录(是/否)'")
    private ConfirmStatus confirmFirstSalary;

    /**
     * 是否确认工资
     */
    @Column(name = "confirmSalary", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '是否确认工资(是/否)'")
    private ConfirmStatus confirmSalary;

    /**
     * 第一次付款时间
     */
    @Column(name = "firstPayTime",  columnDefinition = "DATE   COMMENT '第一次付款时间'")
    private LocalDate firstPayTime;

    /**
     * 第二次付款时间
     */
    @Column(name = "secondPayTime", columnDefinition = "DATE   COMMENT '第二次付款时间'")
    private LocalDate secondPayTime;
    /**
     * 数据状态
     */
    @Column(name = "findType", columnDefinition = "TINYINT(2)   COMMENT '数据状态'")
    private FindType findType;

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public LocalDate getStartPaidCycle() {
        return startPaidCycle;
    }

    public void setStartPaidCycle(LocalDate startPaidCycle) {
        this.startPaidCycle = startPaidCycle;
    }

    public LocalDate getEndPaidCycle() {
        return endPaidCycle;
    }

    public void setEndPaidCycle(LocalDate endPaidCycle) {
        this.endPaidCycle = endPaidCycle;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public Double getAttendanceDay() {
        return attendanceDay;
    }

    public void setAttendanceDay(Double attendanceDay) {
        this.attendanceDay = attendanceDay;
    }

    public Double getStaffWage() {
        return staffWage;
    }

    public void setStaffWage(Double staffWage) {
        this.staffWage = staffWage;
    }

    public Double getComputerHelp() {
        return computerHelp;
    }

    public void setComputerHelp(Double computerHelp) {
        this.computerHelp = computerHelp;
    }

    public Double getStayHelp() {
        return stayHelp;
    }

    public void setStayHelp(Double stayHelp) {
        this.stayHelp = stayHelp;
    }

    public Double getLengthHelp() {
        return lengthHelp;
    }

    public void setLengthHelp(Double lengthHelp) {
        this.lengthHelp = lengthHelp;
    }

    public Double getHyperthermiaHelp() {
        return hyperthermiaHelp;
    }

    public void setHyperthermiaHelp(Double hyperthermiaHelp) {
        this.hyperthermiaHelp = hyperthermiaHelp;
    }

    public Double getSocialHelp() {
        return socialHelp;
    }

    public void setSocialHelp(Double socialHelp) {
        this.socialHelp = socialHelp;
    }

    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    public Double getTotalWages() {
        return totalWages;
    }

    public void setTotalWages(Double totalWages) {
        this.totalWages = totalWages;
    }

    public Double getHolidayOvertimePay() {
        return holidayOvertimePay;
    }

    public void setHolidayOvertimePay(Double holidayOvertimePay) {
        this.holidayOvertimePay = holidayOvertimePay;
    }

    public Double getSocialDeduction() {
        return socialDeduction;
    }

    public void setSocialDeduction(Double socialDeduction) {
        this.socialDeduction = socialDeduction;
    }

    public Double getUtilitiesDeduction() {
        return utilitiesDeduction;
    }

    public void setUtilitiesDeduction(Double utilitiesDeduction) {
        this.utilitiesDeduction = utilitiesDeduction;
    }

    public Double getDeductionTotal() {
        return deductionTotal;
    }

    public void setDeductionTotal(Double deductionTotal) {
        this.deductionTotal = deductionTotal;
    }

    public Double getBonusPenaltyDeduction() {
        return bonusPenaltyDeduction;
    }

    public void setBonusPenaltyDeduction(Double bonusPenaltyDeduction) {
        this.bonusPenaltyDeduction = bonusPenaltyDeduction;
    }

    public Double getIncomeTaxDeduction() {
        return incomeTaxDeduction;
    }

    public void setIncomeTaxDeduction(Double incomeTaxDeduction) {
        this.incomeTaxDeduction = incomeTaxDeduction;
    }

    public Double getWhatSickLeaveDeduction() {
        return whatSickLeaveDeduction;
    }

    public void setWhatSickLeaveDeduction(Double whatSickLeaveDeduction) {
        this.whatSickLeaveDeduction = whatSickLeaveDeduction;
    }

    public Double getAbsenteeismDeduction() {
        return absenteeismDeduction;
    }

    public void setAbsenteeismDeduction(Double absenteeismDeduction) {
        this.absenteeismDeduction = absenteeismDeduction;
    }

    public Double getRealWages() {
        return realWages;
    }

    public void setRealWages(Double realWages) {
        this.realWages = realWages;
    }

    public Boolean getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(Boolean incomeTax) {
        this.incomeTax = incomeTax;
    }

    public Double getFirstSalary() {
        return firstSalary;
    }

    public void setFirstSalary(Double firstSalary) {
        this.firstSalary = firstSalary;
    }

    public Double getSecondSalary() {
        return secondSalary;
    }

    public void setSecondSalary(Double secondSalary) {
        this.secondSalary = secondSalary;
    }

    public Double getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(Double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public Double getPaidInvoiceAmount() {
        return paidInvoiceAmount;
    }

    public void setPaidInvoiceAmount(Double paidInvoiceAmount) {
        this.paidInvoiceAmount = paidInvoiceAmount;
    }

    public ConfirmStatus getConfirmFirstSalary() {
        return confirmFirstSalary;
    }

    public void setConfirmFirstSalary(ConfirmStatus confirmFirstSalary) {
        this.confirmFirstSalary = confirmFirstSalary;
    }

    public ConfirmStatus getConfirmSalary() {
        return confirmSalary;
    }

    public void setConfirmSalary(ConfirmStatus confirmSalary) {
        this.confirmSalary = confirmSalary;
    }

    public LocalDate getFirstPayTime() {
        return firstPayTime;
    }

    public void setFirstPayTime(LocalDate firstPayTime) {
        this.firstPayTime = firstPayTime;
    }

    public LocalDate getSecondPayTime() {
        return secondPayTime;
    }

    public void setSecondPayTime(LocalDate secondPayTime) {
        this.secondPayTime = secondPayTime;
    }
}