package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 请假管理
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-07 05:15 ]
 * @Description: [ 请假管理 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_vacate")
public class Vacate extends BaseEntity {

    /**
     * 员工编号
     */
    @Column(name = "employeeNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String employeeNumber;

    /**
     * 请假人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '请假人'")
    private String name;

    /**
     * 项目组/部门
     */
    @Column(name = "depart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String depart;

    /**
     * 职位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 请假类型
     */
    @Column(name = "vacateType", nullable = false, columnDefinition = "TINYINT(2) COMMENT '请假类型'")
    private VacateType vacateType;

    /**
     * 开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '开始时间'")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '结束时间'")
    private LocalDateTime endTime;

    /**
     * 请假时长(天数)
     */
    @Column(name = "time", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '请假时长(天数)'")
    private Double time;

    /**
     * 请假原因
     */
    @Column(name = "reason", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '请假原因'")
    private String reason;

    /**
     * 填单时间是否符合提前规范
     */
    @Column(name = "is_advance", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '填单时间是否符合提前规范'", insertable = false)
    private Boolean advance;

    /**
     * 是否符合时长
     */
    @Column(name = "is_conform", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否符合时长'", insertable = false)
    private Boolean conform;

    /**
     * 主送人
     */
    @Column(name = "main", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '主送人'")
    private String main;

    /**
     * 抄送人
     */
    @Column(name = "carbon", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '抄送人'")
    private String carbon;

    /**
     * 审核意见
     */
    @Column(name = "advice", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String advice;

    /**
     * 审核状态
     */
    @Column(name = "aduitStatus", nullable = false, columnDefinition = "TINYINT(2) COMMENT '审核状态'")
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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