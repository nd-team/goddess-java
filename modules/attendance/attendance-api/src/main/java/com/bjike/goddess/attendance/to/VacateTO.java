package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.EndTime;
import com.bjike.goddess.attendance.enums.StartTime;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 请假管理
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateTO extends BaseTO {
    /**
     * 员工编号
     */
    private String employeeNumber;

    /**
     * 请假人
     */
    private String name;
    /**
     * 项目组/部门
     */
    private String depart;

    /**
     * 职位
     */
    private String position;
    /**
     * 请假类型
     */
    private VacateType vacateType;
    /**
     * 开始日期
     */
    private String startDate;
    /**
     * 结束日期
     */
    private String endDate;
    /**
     * 开始时间
     */
    private StartTime startTime;

    /**
     * 结束时间
     */
    private EndTime endTime;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 主送人
     */
    private String[] mains;

    /**
     * 抄送人
     */
    private String[] carbons;

    /**
     * 审核意见
     */
    private String advice;

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VacateType getVacateType() {
        return vacateType;
    }

    public void setVacateType(VacateType vacateType) {
        this.vacateType = vacateType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

    public EndTime getEndTime() {
        return endTime;
    }

    public void setEndTime(EndTime endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String[] getMains() {
        return mains;
    }

    public void setMains(String[] mains) {
        this.mains = mains;
    }

    public String[] getCarbons() {
        return carbons;
    }

    public void setCarbons(String[] carbons) {
        this.carbons = carbons;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}