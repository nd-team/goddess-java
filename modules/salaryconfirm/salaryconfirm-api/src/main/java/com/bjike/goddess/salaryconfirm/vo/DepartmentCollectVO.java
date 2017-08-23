package com.bjike.goddess.salaryconfirm.vo;

/**
 * 地区汇总对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-17 05:47 ]
 * @Description: [ 地区汇总对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DepartmentCollectVO {
    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 部门
     */
    private String department;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
}
