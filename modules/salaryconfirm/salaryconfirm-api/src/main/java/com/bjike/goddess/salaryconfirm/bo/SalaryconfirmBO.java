package com.bjike.goddess.salaryconfirm.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.salaryconfirm.enums.FindType;
import com.bjike.goddess.salaryconfirm.enums.Ratepaying;

import javax.persistence.Column;

/**
 * 薪资核算确认表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryconfirmBO extends BaseBO {

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 计薪周期开始时间
     */
    private String salaryStart;

    /**
     * 计薪周期结束时间
     */
    private String salaryEnd;

    /**
     * 地区
     */
    private String area;

    /**
     * 姓名
     */
    private String name;

    /**
     * 员工编号
     */
    private String employeeNumber;

    /**
     * 部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 出勤天数
     */
    private Double attendanceDays;

    /**
     * 员工工资
     */
    private Double salary;

    /**
     * 电脑补助
     */
    private Double cpSubsidy;

    /**
     * 住宿补助
     */
    private Double dormitorySubsidy;

    /**
     * 工龄补助
     */
    private Double yearSubsidy;

    /**
     * 高温补助
     */
    private Double hotSubsidy;

    /**
     * 社保补助
     */
    private Double socialSubsidy;

    /**
     * 其他
     */
    private Double anotherSubsidy;

    /**
     * 工资总额
     */
    private Double totalSalary;

    /**
     * 假期加班费
     */
    private Double holidaySalary;

    /**
     * 社保扣款
     */
    private Double socialConsume;

    /**
     * 水电扣款
     */
    private Double dormitoryConsume;

    /**
     * 扣分合计
     */
    private Integer deduction;

    /**
     * 奖励处罚扣款
     */
    private Double punishConsume;

    /**
     * 个税扣款
     */
    private Double taxConsume;

    /**
     * 事病假扣款
     */
    private Double vacationConsume;

    /**
     * 旷工扣款
     */
    private Double absenteeismConsume;

    /**
     * 实发工资
     */
    private Double actualSalary;

    /**
     * 是否缴纳个人所得税
     */
    private Ratepaying ratepaying;

    /**
     * 本月第一次发工资
     */
    private Double firstSalary;

    /**
     * 本月第二次发工资
     */
    private Double secondSalary;

    /**
     * 第一次是否发放
     */
    private Boolean firstConfirm;

    /**
     * 第二次是否发放
     */
    private Boolean secondConfirm;

    /**
     * 第一次确认时间
     */
    private String firstConfirmTime;

    /**
     * 第二次确认时间
     */
    private String secondConfirmTime;

    /**
     * 第一次付款时间
     */
    private String firstPayedTime;

    /**
     * 第二次付款时间
     */
    private String secondPayedTime;

    /**
     * 数据状态
     */
    private FindType findType;

    /**
     * 本月需缴发票金额
     */
    private Double needRatepaying;

    /**
     * 累计需缴纳发票金额
     */
    private Double totalRatepayed;

    /**
     * 累计上交发票金额
     */
    private Double totalSubmit;


    /**
     * 假期加班天数
     */
    private Double holidayWorkDays;

    /**
     * 旷工天数
     */
    private Double absenteeismDays;

    /**
     * 剩余加班天数
     */
    private Double overWorkDays;

    /**
     * 请假天数
     */
    private Double vacationDays;


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSalaryStart() {
        return salaryStart;
    }

    public void setSalaryStart(String salaryStart) {
        this.salaryStart = salaryStart;
    }

    public String getSalaryEnd() {
        return salaryEnd;
    }

    public void setSalaryEnd(String salaryEnd) {
        this.salaryEnd = salaryEnd;
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

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getAttendanceDays() {
        return attendanceDays;
    }

    public void setAttendanceDays(Double attendanceDays) {
        this.attendanceDays = attendanceDays;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCpSubsidy() {
        return cpSubsidy;
    }

    public void setCpSubsidy(Double cpSubsidy) {
        this.cpSubsidy = cpSubsidy;
    }

    public Double getDormitorySubsidy() {
        return dormitorySubsidy;
    }

    public void setDormitorySubsidy(Double dormitorySubsidy) {
        this.dormitorySubsidy = dormitorySubsidy;
    }

    public Double getYearSubsidy() {
        return yearSubsidy;
    }

    public void setYearSubsidy(Double yearSubsidy) {
        this.yearSubsidy = yearSubsidy;
    }

    public Double getHotSubsidy() {
        return hotSubsidy;
    }

    public void setHotSubsidy(Double hotSubsidy) {
        this.hotSubsidy = hotSubsidy;
    }

    public Double getSocialSubsidy() {
        return socialSubsidy;
    }

    public void setSocialSubsidy(Double socialSubsidy) {
        this.socialSubsidy = socialSubsidy;
    }

    public Double getAnotherSubsidy() {
        return anotherSubsidy;
    }

    public void setAnotherSubsidy(Double anotherSubsidy) {
        this.anotherSubsidy = anotherSubsidy;
    }

    public Double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Double getHolidaySalary() {
        return holidaySalary;
    }

    public void setHolidaySalary(Double holidaySalary) {
        this.holidaySalary = holidaySalary;
    }

    public Double getSocialConsume() {
        return socialConsume;
    }

    public void setSocialConsume(Double socialConsume) {
        this.socialConsume = socialConsume;
    }

    public Double getDormitoryConsume() {
        return dormitoryConsume;
    }

    public void setDormitoryConsume(Double dormitoryConsume) {
        this.dormitoryConsume = dormitoryConsume;
    }

    public Integer getDeduction() {
        return deduction;
    }

    public void setDeduction(Integer deduction) {
        this.deduction = deduction;
    }

    public Double getPunishConsume() {
        return punishConsume;
    }

    public void setPunishConsume(Double punishConsume) {
        this.punishConsume = punishConsume;
    }

    public Double getTaxConsume() {
        return taxConsume;
    }

    public void setTaxConsume(Double taxConsume) {
        this.taxConsume = taxConsume;
    }

    public Double getVacationConsume() {
        return vacationConsume;
    }

    public void setVacationConsume(Double vacationConsume) {
        this.vacationConsume = vacationConsume;
    }

    public Double getAbsenteeismConsume() {
        return absenteeismConsume;
    }

    public void setAbsenteeismConsume(Double absenteeismConsume) {
        this.absenteeismConsume = absenteeismConsume;
    }

    public Double getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(Double actualSalary) {
        this.actualSalary = actualSalary;
    }

    public Ratepaying getRatepaying() {
        return ratepaying;
    }

    public void setRatepaying(Ratepaying ratepaying) {
        this.ratepaying = ratepaying;
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

    public Boolean getFirstConfirm() {
        return firstConfirm;
    }

    public void setFirstConfirm(Boolean firstConfirm) {
        this.firstConfirm = firstConfirm;
    }

    public Boolean getSecondConfirm() {
        return secondConfirm;
    }

    public void setSecondConfirm(Boolean secondConfirm) {
        this.secondConfirm = secondConfirm;
    }

    public String getFirstConfirmTime() {
        return firstConfirmTime;
    }

    public void setFirstConfirmTime(String firstConfirmTime) {
        this.firstConfirmTime = firstConfirmTime;
    }

    public String getSecondConfirmTime() {
        return secondConfirmTime;
    }

    public void setSecondConfirmTime(String secondConfirmTime) {
        this.secondConfirmTime = secondConfirmTime;
    }

    public String getFirstPayedTime() {
        return firstPayedTime;
    }

    public void setFirstPayedTime(String firstPayedTime) {
        this.firstPayedTime = firstPayedTime;
    }

    public String getSecondPayedTime() {
        return secondPayedTime;
    }

    public void setSecondPayedTime(String secondPayedTime) {
        this.secondPayedTime = secondPayedTime;
    }

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
    }

    public Double getNeedRatepaying() {
        return needRatepaying;
    }

    public void setNeedRatepaying(Double needRatepaying) {
        this.needRatepaying = needRatepaying;
    }

    public Double getTotalRatepayed() {
        return totalRatepayed;
    }

    public void setTotalRatepayed(Double totalRatepayed) {
        this.totalRatepayed = totalRatepayed;
    }

    public Double getTotalSubmit() {
        return totalSubmit;
    }

    public void setTotalSubmit(Double totalSubmit) {
        this.totalSubmit = totalSubmit;
    }

    public Double getHolidayWorkDays() {
        return holidayWorkDays;
    }

    public void setHolidayWorkDays(Double holidayWorkDays) {
        this.holidayWorkDays = holidayWorkDays;
    }

    public Double getAbsenteeismDays() {
        return absenteeismDays;
    }

    public void setAbsenteeismDays(Double absenteeismDays) {
        this.absenteeismDays = absenteeismDays;
    }

    public Double getOverWorkDays() {
        return overWorkDays;
    }

    public void setOverWorkDays(Double overWorkDays) {
        this.overWorkDays = overWorkDays;
    }

    public Double getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Double vacationDays) {
        this.vacationDays = vacationDays;
    }
}