package com.bjike.goddess.salaryconfirm.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.salaryconfirm.enums.Ratepaying;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 薪资核算确认
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SalaryconfirmTO extends BaseTO {

    /**
     * 年份
     */
    @NotNull(message = "年份不能为空", groups = {ADD.class, EDIT.class})
    private Integer year;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空", groups = {ADD.class, EDIT.class})
    private Integer month;

    /**
     * 计薪周期开始时间
     */
    @NotBlank(message = "计薪周期开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String salaryStart;

    /**
     * 计薪周期结束时间
     */
    @NotBlank(message = "计薪周期结束时间不能为空", groups = {ADD.class, EDIT.class})
    private String salaryEnd;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 员工编号
     */
    @NotBlank(message = "员工编号不能为空", groups = {ADD.class, EDIT.class})
    private String employeeNumber;

    /**
     * 部门
     */
    @NotBlank(message = "部门不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空", groups = {ADD.class, EDIT.class})
    private String position;

    /**
     * 出勤天数
     */
    @NotNull(message = "出勤天数不能为空", groups = {ADD.class, EDIT.class})
    private Double attendanceDays;

    /**
     * 员工工资
     */
    @NotNull(message = "员工工资不能为空", groups = {ADD.class, EDIT.class})
    private Double salary;

    /**
     * 电脑补助
     */
    @NotNull(message = "电脑补助不能为空", groups = {ADD.class, EDIT.class})
    private Double cpSubsidy;

    /**
     * 住宿补助
     */
    @NotNull(message = "住宿补助不能为空", groups = {ADD.class, EDIT.class})
    private Double dormitorySubsidy;

    /**
     * 工龄补助
     */
    @NotNull(message = "工龄补助不能为空", groups = {ADD.class, EDIT.class})
    private Double yearSubsidy;

    /**
     * 高温补助
     */
    @NotNull(message = "高温补助不能为空", groups = {ADD.class, EDIT.class})
    private Double hotSubsidy;

    /**
     * 社保补助
     */
    @NotNull(message = "社保补助不能为空", groups = {ADD.class, EDIT.class})
    private Double socialSubsidy;

    /**
     * 其他
     */
    @NotNull(message = "其他不能为空", groups = {ADD.class, EDIT.class})
    private Double anotherSubsidy;

    /**
     * 剩余加班天数
     */
    @NotNull(message = "剩余加班天数不能为空", groups = {ADD.class, EDIT.class})
    private Double overWorkDays;

    /**
     * 假期加班天数
     */
    @NotNull(message = "假期加班天数", groups = {ADD.class, EDIT.class})
    private Double holidayWorkDays;

    /**
     * 社保扣款
     */
    @NotNull(message = "社保扣款不能为空", groups = {ADD.class, EDIT.class})
    private Double socialConsume;

    /**
     * 水电扣款
     */
    @NotNull(message = "水电扣款不能为空", groups = {ADD.class, EDIT.class})
    private Double dormitoryConsume;

    /**
     * 扣分合计
     */
    @NotNull(message = "扣分合计不能为空", groups = {ADD.class, EDIT.class})
    private Integer deduction;

    /**
     * 旷工天数
     */
    @NotNull(message = "旷工天数不能为空", groups = {ADD.class, EDIT.class})
    private Double absenteeismDays;

    /**
     * 请假天数
     */
    @NotNull(message = "请假天数不能为空", groups = {ADD.class, EDIT.class})
    private Double vacationDays;

    /**
     * 是否缴纳个人所得税
     */
    @NotNull(message = "是否缴纳个人所得税不能为空", groups = {ADD.class, EDIT.class})
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

    public Double getHolidayWorkDays() {
        return holidayWorkDays;
    }

    public void setHolidayWorkDays(Double holidayWorkDays) {
        this.holidayWorkDays = holidayWorkDays;
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

    public Ratepaying getRatepaying() {
        return ratepaying;
    }

    public void setRatepaying(Ratepaying ratepaying) {
        this.ratepaying = ratepaying;
    }

    public Double getOverWorkDays() {
        return overWorkDays;
    }

    public void setOverWorkDays(Double overWorkDays) {
        this.overWorkDays = overWorkDays;
    }

    public Double getAbsenteeismDays() {
        return absenteeismDays;
    }

    public void setAbsenteeismDays(Double absenteeismDays) {
        this.absenteeismDays = absenteeismDays;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}