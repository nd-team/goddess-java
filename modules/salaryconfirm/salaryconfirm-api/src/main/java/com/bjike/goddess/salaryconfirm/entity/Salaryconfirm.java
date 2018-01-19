package com.bjike.goddess.salaryconfirm.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.salaryconfirm.enums.FindType;
import com.bjike.goddess.salaryconfirm.enums.Ratepaying;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 薪资核算确认
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-16 03:22 ]
 * @Description: [ 薪资核算确认 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "salaryconfirm", uniqueConstraints = {@UniqueConstraint(columnNames = {"employeeNumber", "year", "month"}),
        @UniqueConstraint(columnNames = {"employeeNumber", "salaryStart", "salaryEnd"})})
public class Salaryconfirm extends BaseEntity {

    /**
     * 年份
     */
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;

    /**
     * 月份
     */
    @Column(name = "month", nullable = false, columnDefinition = "INT(11)   COMMENT '月份' default 0")
    private Integer month;

    /**
     * 计薪周期开始时间
     */
    @Column(name = "salaryStart", nullable = false, columnDefinition = "DATE   COMMENT '计薪周期开始时间'")
    private LocalDate salaryStart;

    /**
     * 计薪周期结束时间
     */
    @Column(name = "salaryEnd", nullable = false, columnDefinition = "DATE   COMMENT '计薪周期结束时间'")
    private LocalDate salaryEnd;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "employeeNumber", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '员工编号'")
    private String employeeNumber;

    /**
     * 部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 出勤天数
     */
    @Column(name = "attendanceDays", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '出勤天数'")
    private Double attendanceDays;

    /**
     * 员工工资
     */
    @Column(name = "salary", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '员工工资'")
    private Double salary;

    /**
     * 电脑补助
     */
    @Column(name = "cpSubsidy", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '电脑补助'")
    private Double cpSubsidy;

    /**
     * 住宿补助
     */
    @Column(name = "dormitorySubsidy", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '住宿补助'")
    private Double dormitorySubsidy;

    /**
     * 工龄补助
     */
    @Column(name = "yearSubsidy", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '工龄补助'")
    private Double yearSubsidy;

    /**
     * 高温补助
     */
    @Column(name = "hotSubsidy", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '高温补助'")
    private Double hotSubsidy;

    /**
     * 社保补助
     */
    @Column(name = "socialSubsidy", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '社保补助'")
    private Double socialSubsidy;

    /**
     * 其他
     */
    @Column(name = "anotherSubsidy", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '其他'")
    private Double anotherSubsidy;

    /**
     * 工资总额
     */
    @Column(name = "totalSalary", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '工资总额'")
    private Double totalSalary;

    /**
     * 应税工资
     */
    @Column(name = "taxableSalary", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '应税工资'")
    private Double taxableSalary;

    /**
     * 剩余加班天数
     */
    @Column(name = "overWorkDays", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '剩余加班天数' default 0")
    private Double overWorkDays;

    /**
     * 假期加班天数
     */
    @Column(name = "holidayWorkDays", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '假期加班费' default 0")
    private Double holidayWorkDays;

    /**
     * 假期加班费
     */
    @Column(name = "holidaySalary", columnDefinition = "DECIMAL(10,2)   COMMENT '假期加班费'")
    private Double holidaySalary;

    /**
     * 社保扣款
     */
    @Column(name = "socialConsume", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '社保扣款'")
    private Double socialConsume;

    /**
     * 水电扣款
     */
    @Column(name = "dormitoryConsume", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '水电扣款'")
    private Double dormitoryConsume;

    /**
     * 扣分合计
     */
    @Column(name = "deduction", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '扣分合计'")
    private Integer deduction;

    /**
     * 奖励处罚扣款
     */
    @Column(name = "punishConsume", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '奖励处罚扣款'")
    private Double punishConsume;

    /**
     * 个税扣款
     */
    @Column(name = "taxConsume", columnDefinition = "DECIMAL(10,2)   COMMENT '个税扣款'")
    private Double taxConsume;

    /**
     * 请假天数
     */
    @Column(name = "vacationDays", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '请假天数' default 0")
    private Double vacationDays;

    /**
     * 事病假扣款
     */
    @Column(name = "vacationConsume", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '事病假扣款'")
    private Double vacationConsume;

    /**
     * 旷工天数
     */
    @Column(name = "absenteeismDays", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '旷工天数' default 0")
    private Double absenteeismDays;

    /**
     * 旷工扣款
     */
    @Column(name = "absenteeismConsume", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '旷工扣款'")
    private Double absenteeismConsume;

    /**
     * 实发工资
     */
    @Column(name = "actualSalary", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实发工资'")
    private Double actualSalary;

    /**
     * 是否缴纳个人所得税
     */
    @Column(name = "is_ratepaying", nullable = false, columnDefinition = "TINYINT(2) COMMENT '是否缴纳个人所得税'")
    private Ratepaying ratepaying;

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
     * 第一次是否发放
     */
    @Column(name = "is_firstConfirm", columnDefinition = "TINYINT(1) COMMENT '第一次是否发放'")
    private Boolean firstConfirm;

    /**
     * 第二次是否发放
     */
    @Column(name = "is_secondConfirm", columnDefinition = "TINYINT(1)  COMMENT '第二次是否发放'")
    private Boolean secondConfirm;

    /**
     * 第一次确认时间
     */
    @Column(name = "firstConfirmTime", columnDefinition = "DATETIME COMMENT '第一次确认时间'")
    private LocalDateTime firstConfirmTime;

    /**
     * 第二次确认时间
     */
    @Column(name = "secondConfirmTime", columnDefinition = "DATETIME  COMMENT '第二次确认时间'")
    private LocalDateTime secondConfirmTime;

    /**
     * 第一次付款时间
     */
    @Column(name = "firstPayedTime", columnDefinition = "DATETIME COMMENT '第一次付款时间'")
    private LocalDateTime firstPayedTime;

    /**
     * 第二次付款时间
     */
    @Column(name = "secondPayedTime", columnDefinition = "DATETIME  COMMENT '第二次付款时间'")
    private LocalDateTime secondPayedTime;

    /**
     * 本月需缴发票金额
     */
    @Column(name = "needRatepaying", columnDefinition = "DECIMAL(10,2)   COMMENT '本月需缴发票金额'")
    private Double needRatepaying;

    /**
     * 数据状态
     */
    @Column(name = "findType", columnDefinition = "TINYINT(2)   COMMENT '数据状态'")
    private FindType findType;

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

    public Double getNeedRatepaying() {
        return needRatepaying;
    }

    public void setNeedRatepaying(Double needRatepaying) {
        this.needRatepaying = needRatepaying;
    }

    public Double getHolidayWorkDays() {
        return holidayWorkDays;
    }

    public void setHolidayWorkDays(Double holidayWorkDays) {
        this.holidayWorkDays = holidayWorkDays;
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

    public Double getOverWorkDays() {
        return overWorkDays;
    }

    public void setOverWorkDays(Double overWorkDays) {
        this.overWorkDays = overWorkDays;
    }

    public Double getTaxableSalary() {
        return taxableSalary;
    }

    public void setTaxableSalary(Double taxableSalary) {
        this.taxableSalary = taxableSalary;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public FindType getFindType() {
        return findType;
    }

    public void setFindType(FindType findType) {
        this.findType = findType;
    }

    public LocalDateTime getFirstConfirmTime() {
        return firstConfirmTime;
    }

    public void setFirstConfirmTime(LocalDateTime firstConfirmTime) {
        this.firstConfirmTime = firstConfirmTime;
    }

    public LocalDateTime getSecondConfirmTime() {
        return secondConfirmTime;
    }

    public void setSecondConfirmTime(LocalDateTime secondConfirmTime) {
        this.secondConfirmTime = secondConfirmTime;
    }

    public LocalDateTime getFirstPayedTime() {
        return firstPayedTime;
    }

    public void setFirstPayedTime(LocalDateTime firstPayedTime) {
        this.firstPayedTime = firstPayedTime;
    }

    public LocalDateTime getSecondPayedTime() {
        return secondPayedTime;
    }

    public void setSecondPayedTime(LocalDateTime secondPayedTime) {
        this.secondPayedTime = secondPayedTime;
    }
}