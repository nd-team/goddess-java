package com.bjike.goddess.staffpay.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.staffpay.enums.ConfirmStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 等待付款
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 11:38 ]
 * @Description: [ 等待付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WaitPayTO extends BaseTO {

    /**
     * 月份
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "月份不能为空")
    @Min(value = 1, groups = {ADD.class, EDIT.class}, message = "月份必须大于等于1")
    @Max(value = 12, groups = {ADD.class, EDIT.class}, message = "月份必须小于等于12")
    private Integer months;

    /**
     * 计薪周期（开始）
     */
    private String startPaidCycle;

    /**
     * 计薪周期（结束）
     */
    private String endPaidCycle;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空",groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空",groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空",groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空",groups = {ADD.class, EDIT.class})
    private String jobs;

    /**
     * 出勤天数
     */
    private Double attendanceDay;

    /**
     * 员工工资
     */
    @NotNull(message = "员工工资不能为空",groups = {ADD.class, EDIT.class})
    private Double staffWage;

    /**
     * 电脑补助
     */
    @NotNull(message = "电脑补助不能为空",groups = {ADD.class, EDIT.class})
    private Double computerHelp;

    /**
     * 住宿补助
     */
    @NotNull(message = "住宿补助不能为空",groups = {ADD.class, EDIT.class})
    private Double stayHelp;

    /**
     * 工龄补助
     */
    @NotNull(message = "工龄补助不能为空",groups = {ADD.class, EDIT.class})
    private Double lengthHelp;

    /**
     * 高温补助
     */
    @NotNull(message = "高温补助不能为空",groups = {ADD.class, EDIT.class})
    private Double hyperthermiaHelp;

    /**
     * 社保补助
     */
    @NotNull(message = "社保补助不能为空",groups = {ADD.class, EDIT.class})
    private Double socialHelp;

    /**
     * 其他费用
     */
    @NotNull(message = "其他费用不能为空",groups = {ADD.class, EDIT.class})
    private Double otherFee;

    /**
     * 工资总额
     */
    @NotNull(message = "工资总额不能为空",groups = {ADD.class, EDIT.class})
    private Double totalWages;

    /**
     * 假期加班费
     */
    @NotNull(message = "假期加班费不能为空",groups = {ADD.class, EDIT.class})
    private Double holidayOvertimePay;

    /**
     * 社保扣款
     */
    @NotNull(message = "社保扣款不能为空",groups = {ADD.class, EDIT.class})
    private Double socialDeduction;

    /**
     * 水电费扣款
     */
    @NotNull(message = "水电费扣款不能为空",groups = {ADD.class, EDIT.class})
    private Double utilitiesDeduction;

    /**
     * 扣分合计
     */
    @NotNull(message = "扣分合计不能为空",groups = {ADD.class, EDIT.class})
    private Double deductionTotal;

    /**
     * 奖金处罚扣款
     */
    @NotNull(message = "奖金处罚扣款不能为空",groups = {ADD.class, EDIT.class})
    private Double bonusPenaltyDeduction;

    /**
     * 个税扣款
     */
    @NotNull(message = "个税扣款不能为空",groups = {ADD.class, EDIT.class})
    private Double incomeTaxDeduction;

    /**
     * 事病假扣款
     */
    @NotNull(message = "事病假扣款不能为空",groups = {ADD.class, EDIT.class})
    private Double whatSickLeaveDeduction;

    /**
     * 旷工扣款
     */
    @NotNull(message = "旷工扣款不能为空",groups = {ADD.class, EDIT.class})
    private Double absenteeismDeduction;

    /**
     * 实发工资
     */
    @NotNull(message = "实发工资不能为空",groups = {ADD.class, EDIT.class})
    private Double realWages;

    /**
     * 是否缴纳个人所得税
     */
    private Boolean incomeTax;

    /**
     * 本月第一次发工资
     */
    private Double firstSalary;

    /**
     * 本月第二次发工资
     */
    private Double secondSalary;

    /**
     * 本月需缴发票金额
     */
    private Double invoiceValue;

    /**
     * 累计已缴发票金额
     */
    private Double paidInvoiceAmount;

    /**
     * 是否确认第一次工资发放记录
     */
    private ConfirmStatus confirmFirstSalary;

    /**
     * 是否确认工资
     */
    private ConfirmStatus confirmSalary;

    /**
     * 第一次付款时间
     */
    private String firstPayTime;

    /**
     * 第二次付款时间
     */
    private String secondPayTime;

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public String getStartPaidCycle() {
        return startPaidCycle;
    }

    public void setStartPaidCycle(String startPaidCycle) {
        this.startPaidCycle = startPaidCycle;
    }

    public String getEndPaidCycle() {
        return endPaidCycle;
    }

    public void setEndPaidCycle(String endPaidCycle) {
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

    public String getFirstPayTime() {
        return firstPayTime;
    }

    public void setFirstPayTime(String firstPayTime) {
        this.firstPayTime = firstPayTime;
    }

    public String getSecondPayTime() {
        return secondPayTime;
    }

    public void setSecondPayTime(String secondPayTime) {
        this.secondPayTime = secondPayTime;
    }
}