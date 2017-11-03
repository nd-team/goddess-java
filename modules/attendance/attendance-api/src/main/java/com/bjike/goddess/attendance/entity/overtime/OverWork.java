package com.bjike.goddess.attendance.entity.overtime;

import com.bjike.goddess.attendance.enums.AuditStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 加班
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-10-10 10:32 ]
 * @Description: [ 加班 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_overwork")
public class OverWork extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 加班录入人
     */
    @Column(name = "entryer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '加班录入人'")
    private String entryer;
    /**
     * 任务下达人
     */
    @Column(name = "tasker", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '任务下达人'")
    private String tasker;

    /**
     * 加班人员
     */
    @Column(name = "overWorker", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '加班人员'")
    private String overWorker;

    /**
     * 加班类型
     */
    @Column(name = "overType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '加班类型'")
    private String overType;

    /**
     * 部门
     */
    @Column(name = "depart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String depart;

    /**
     * 职位
     */
    @Column(name = "position", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '职位'")
    private String position;

    /**
     * 开始时间
     */
    @Column(name = "overStartTime", nullable = false, columnDefinition = "DATETIME   COMMENT '开始时间'")
    private LocalDateTime overStartTime;

    /**
     * 结束时间
     */
    @Column(name = "overEndTime", nullable = false, columnDefinition = "DATETIME   COMMENT '结束时间'")
    private LocalDateTime overEndTime;

    /**
     * 加班时长
     */
    @Column(name = "overLong", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '加班时长'")
    private Double overLong;

    /**
     * 是否午休
     */
    @Column(name = "is_noonBreakOr", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '是否午休'", insertable = false)
    private Boolean noonBreakOr;

    /**
     * 工作内容
     */
    @Column(name = "workContent", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '工作内容'")
    private String workContent;

    /**
     * 完成情况
     */
    @Column(name = "completeCon", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '完成情况'")
    private String completeCon;

    /**
     * 可休天数
     */
    @Column(name = "relaxDay", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '可休天数'")
    private Double relaxDay;

    /**
     * 负责人(审批人)
     */
    @Column(name = "charger", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '负责人(审批人)'")
    private String charger;

    /**
     * 审核意见
     */
    @Column(name = "auditAdvice",  columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String auditAdvice;
    /**
     * 审核时间
     */
    @Column(name = "auditTime",  columnDefinition = "DATETIME   COMMENT '审核时间'")
    private LocalDateTime auditTime;

    /**
     * 审核状态
     */
    @Column(name = "auditStatus", nullable = false, columnDefinition = "INT(2)   COMMENT '审核状态' ")
    private AuditStatus auditStatus;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTasker() {
        return tasker;
    }

    public void setTasker(String tasker) {
        this.tasker = tasker;
    }

    public String getEntryer() {
        return entryer;
    }

    public void setEntryer(String entryer) {
        this.entryer = entryer;
    }

    public String getOverWorker() {
        return overWorker;
    }

    public void setOverWorker(String overWorker) {
        this.overWorker = overWorker;
    }

    public String getOverType() {
        return overType;
    }

    public void setOverType(String overType) {
        this.overType = overType;
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

    public LocalDateTime getOverStartTime() {
        return overStartTime;
    }

    public void setOverStartTime(LocalDateTime overStartTime) {
        this.overStartTime = overStartTime;
    }

    public LocalDateTime getOverEndTime() {
        return overEndTime;
    }

    public void setOverEndTime(LocalDateTime overEndTime) {
        this.overEndTime = overEndTime;
    }

    public Double getOverLong() {
        return overLong;
    }

    public void setOverLong(Double overLong) {
        this.overLong = overLong;
    }

    public Boolean getNoonBreakOr() {
        return noonBreakOr;
    }

    public void setNoonBreakOr(Boolean noonBreakOr) {
        this.noonBreakOr = noonBreakOr;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getCompleteCon() {
        return completeCon;
    }

    public void setCompleteCon(String completeCon) {
        this.completeCon = completeCon;
    }

    public Double getRelaxDay() {
        return relaxDay;
    }

    public void setRelaxDay(Double relaxDay) {
        this.relaxDay = relaxDay;
    }

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getAuditAdvice() {
        return auditAdvice;
    }

    public void setAuditAdvice(String auditAdvice) {
        this.auditAdvice = auditAdvice;
    }

    public AuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }
}