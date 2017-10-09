package com.bjike.goddess.taskallotment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.taskallotment.enums.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 完成情况汇总设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:31 ]
 * @Description: [ 完成情况汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "taskallotment_finishcountemail")
public class FinishCountEmail extends BaseEntity {

    /**
     * 汇总表名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总表名称'")
    private String name;

    /**
     * 汇总对象
     */
    @Column(name = "countType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '汇总对象'")
    private CountType countType;

    /**
     * 汇总个人
     */
    @Column(name = "countPersons", columnDefinition = "TEXT   COMMENT '汇总个人'")
    private String countPersons;
    /**
     * 地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "depart", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String depart;

    /**
     * 项目
     */
    @Column(name = "project",  columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

    /**
     * 项目表
     */
    @Column(name = "tableName", columnDefinition = "VARCHAR(255)   COMMENT '项目表'")
    private String table;

    /**
     * 提醒间隔
     */
    @Column(name = "spacing", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '提醒间隔'")
    private Spacing spacing;

    /**
     * 提醒频率
     */
    @Column(name = "remind", nullable = false, columnDefinition = "INT(11)   COMMENT '提醒频率'")
    private Integer remind;

    /**
     * 设置时间
     */
    @Column(name = "setTime", nullable = false, columnDefinition = "DATETIME   COMMENT '设置时间'")
    private LocalDateTime setTime;

    /**
     * 上次发送时间
     */
    @Column(name = "lastTime", nullable = false, columnDefinition = "DATETIME   COMMENT '上次发送时间'")
    private LocalDateTime lastTime;

    /**
     * 通报对象
     */
    @Column(name = "forObject", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '通报对象'")
    private ForObject forObject;

    /**
     * 通报部门
     */
    @Column(name = "forDeparts", columnDefinition = "TEXT   COMMENT '通报部门'")
    private String forDeparts;

    /**
     * 通报个人
     */
    @Column(name = "forPersons", columnDefinition = "TEXT   COMMENT '通报个人'")
    private String forPersons;

    /**
     * 汇总频率
     */
    @Column(name = "countFrequency", columnDefinition = "TINYINT(2)   COMMENT '汇总频率'")
    private CountFrequency countFrequency;

    /**
     * 汇总开始时间
     */
    @Column(name = "startTime", columnDefinition = "DATE   COMMENT '汇总开始时间'")
    private LocalDate startTime;

    /**
     * 汇总结束时间
     */
    @Column(name = "endTime", columnDefinition = "DATE   COMMENT '汇总结束时间'")
    private LocalDate endTime;

    /**
     * 创建人
     */
    @Column(name = "creater", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creater;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getForDeparts() {
        return forDeparts;
    }

    public void setForDeparts(String forDeparts) {
        this.forDeparts = forDeparts;
    }

    public String getForPersons() {
        return forPersons;
    }

    public void setForPersons(String forPersons) {
        this.forPersons = forPersons;
    }

    public String getCountPersons() {
        return countPersons;
    }

    public void setCountPersons(String countPersons) {
        this.countPersons = countPersons;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public CountFrequency getCountFrequency() {
        return countFrequency;
    }

    public void setCountFrequency(CountFrequency countFrequency) {
        this.countFrequency = countFrequency;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Spacing getSpacing() {
        return spacing;
    }

    public void setSpacing(Spacing spacing) {
        this.spacing = spacing;
    }

    public Integer getRemind() {
        return remind;
    }

    public void setRemind(Integer remind) {
        this.remind = remind;
    }

    public LocalDateTime getSetTime() {
        return setTime;
    }

    public void setSetTime(LocalDateTime setTime) {
        this.setTime = setTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public ForObject getForObject() {
        return forObject;
    }

    public void setForObject(ForObject forObject) {
        this.forObject = forObject;
    }

    public CountType getCountType() {
        return countType;
    }

    public void setCountType(CountType countType) {
        this.countType = countType;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}