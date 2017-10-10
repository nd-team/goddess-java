package com.bjike.goddess.attendance.bo;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 请假管理业务传输对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class VacateBO extends BaseBO {

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
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 请假时长
     */
    private Double time;

    /**
     * 请假原因
     */
    private String reason;

    /**
     * 填单时间是否符合提前规范
     */
    private Boolean advance;

    /**
     * 是否符合时长
     */
    private Boolean conform;

    /**
     * 主送人
     */
    private String main;

    /**
     * 抄送人
     */
    private String carbon;

    /**
     * 审核意见
     */
    private String advice;

    /**
     * 请假类型
     */
    private VacateType vacateType;

    /**
     * 审核状态
     */
    private AduitStatus aduitStatus;

    public VacateType getVacateType() {
        return vacateType;
    }

    public void setVacateType(VacateType vacateType) {
        this.vacateType = vacateType;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getAdvance() {
        return advance;
    }

    public void setAdvance(Boolean advance) {
        this.advance = advance;
    }

    public Boolean getConform() {
        return conform;
    }

    public void setConform(Boolean conform) {
        this.conform = conform;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}