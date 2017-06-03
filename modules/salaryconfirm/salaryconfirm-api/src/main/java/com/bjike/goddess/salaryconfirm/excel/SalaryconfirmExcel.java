package com.bjike.goddess.salaryconfirm.excel;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.utils.excel.ExcelHeader;
import com.bjike.goddess.salaryconfirm.enums.Ratepaying;

import java.time.LocalDate;


/**
 * 薪资核算确认
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryconfirmExcel extends BaseEntity {

    /**
     * 年份
     */
    @ExcelHeader(name = "年份", notNull = true)
    private Integer year;

    /**
     * 月份
     */
    @ExcelHeader(name = "月份", notNull = true)
    private Integer month;

    /**
     * 计薪周期开始时间
     */
    @ExcelHeader(name = "计薪周期开始时间", notNull = true)
    private LocalDate salaryStart;

    /**
     * 计薪周期结束时间
     */
    @ExcelHeader(name = "计薪周期结束时间", notNull = true)
    private LocalDate salaryEnd;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区", notNull = true)
    private String area;

    /**
     * 姓名
     */
    @ExcelHeader(name = "姓名", notNull = true)
    private String name;

    /**
     * 员工编号
     */
    @ExcelHeader(name = "员工编号", notNull = true)
    private String employeeNumber;

    /**
     * 部门
     */
    @ExcelHeader(name = "部门", notNull = true)
    private String department;

    /**
     * 岗位
     */
    @ExcelHeader(name = "岗位", notNull = true)
    private String position;

    /**
     * 出勤天数
     */
    @ExcelHeader(name = "出勤天数", notNull = true)
    private Double attendanceDays;

    /**
     * 员工工资
     */
    @ExcelHeader(name = "员工工资", notNull = true)
    private Double salary;

    /**
     * 电脑补助
     */
    @ExcelHeader(name = "电脑补助", notNull = true)
    private Double cpSubsidy;

    /**
     * 住宿补助
     */
    @ExcelHeader(name = "住宿补助", notNull = true)
    private Double dormitorySubsidy;

    /**
     * 工龄补助
     */
    @ExcelHeader(name = "工龄补助", notNull = true)
    private Double yearSubsidy;

    /**
     * 高温补助
     */
    @ExcelHeader(name = "高温补助", notNull = true)
    private Double hotSubsidy;

    /**
     * 社保补助
     */
    @ExcelHeader(name = "社保补助", notNull = true)
    private Double socialSubsidy;

    /**
     * 其他
     */
    @ExcelHeader(name = "其他", notNull = true)
    private Double anotherSubsidy;

    /**
     * 剩余加班天数
     */
    @ExcelHeader(name = "剩余加班天数", notNull = true)
    private Double overWorkDays;

    /**
     * 假期加班天数
     */
    @ExcelHeader(name = "假期加班天数", notNull = true)
    private Double holidayWorkDays;

    /**
     * 假期加班费
     */
    @ExcelHeader(name = "假期加班费")
    private Double holidaySalary;

    /**
     * 社保扣款
     */
    @ExcelHeader(name = "社保扣款", notNull = true)
    private Double socialConsume;

    /**
     * 水电扣款
     */
    @ExcelHeader(name = "水电扣款", notNull = true)
    private Double dormitoryConsume;

    /**
     * 扣分合计
     */
    @ExcelHeader(name = "扣分合计", notNull = true)
    private Integer deduction;

    /**
     * 请假天数
     */
    @ExcelHeader(name = "请假天数", notNull = true)
    private Double vacationDays;

    /**
     * 旷工天数
     */
    @ExcelHeader(name = "旷工天数", notNull = true)
    private Double absenteeismDays;


    /**
     * 是否缴纳个人所得税
     */
    @ExcelHeader(name = "是否缴纳个人所得税", notNull = true)
    private Ratepaying ratepaying;

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

    public LocalDate getSalaryStart() {
        return salaryStart;
    }

    public void setSalaryStart(LocalDate salaryStart) {
        this.salaryStart = salaryStart;
    }

    public LocalDate getSalaryEnd() {
        return salaryEnd;
    }

    public void setSalaryEnd(LocalDate salaryEnd) {
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

    public Double getOverWorkDays() {
        return overWorkDays;
    }

    public void setOverWorkDays(Double overWorkDays) {
        this.overWorkDays = overWorkDays;
    }

    public Double getHolidayWorkDays() {
        return holidayWorkDays;
    }

    public void setHolidayWorkDays(Double holidayWorkDays) {
        this.holidayWorkDays = holidayWorkDays;
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

    public Double getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(Double vacationDays) {
        this.vacationDays = vacationDays;
    }

    public Double getAbsenteeismDays() {
        return absenteeismDays;
    }

    public void setAbsenteeismDays(Double absenteeismDays) {
        this.absenteeismDays = absenteeismDays;
    }

    public Ratepaying getRatepaying() {
        return ratepaying;
    }

    public void setRatepaying(Ratepaying ratepaying) {
        this.ratepaying = ratepaying;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}