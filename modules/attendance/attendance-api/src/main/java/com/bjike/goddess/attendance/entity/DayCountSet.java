package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.CountFrequency;
import com.bjike.goddess.attendance.enums.RemindFrequency;
import com.bjike.goddess.attendance.enums.TotalType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 日报汇总设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:20 ]
 * @Description: [ 日报汇总设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_daycountset")
public class DayCountSet extends BaseEntity {

    /**
     * 汇总表名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总表名称'")
    private String name;

    /**
     * 创建人
     */
    @Column(name = "creater", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creater;

    /**
     * 项目组
     */
    @Column(name = "depart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String depart;

    /**
     * 设置发送时间
     */
    @Column(name = "sendTime", nullable = false, columnDefinition = "DATETIME   COMMENT '设置发送时间'")
    private LocalDateTime sendTime;

    /**
     * 上次发送时间
     */
    @Column(name = "lastTime", nullable = false, columnDefinition = "DATETIME   COMMENT '上次发送时间'")
    private LocalDateTime lastTime;

    /**
     * 统计类型
     */
    @Column(name = "totalType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '统计类型'")
    private TotalType totalType;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 通报对象
     */
    @Column(name = "sendObject", nullable = false, columnDefinition = "TEXT   COMMENT '通报对象'")
    private String sendObject;

    /**
     * 汇总对象
     */
    @Column(name = "collectObject", columnDefinition = "TEXT   COMMENT '汇总对象'")
    private String collectObject;

    /**
     * 是否发送至本部门全部人
     */
    @Column(name = "is_all", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否发送至本部门全部人'")
    private Boolean all;

    /**
     * 提醒频率
     */
    @Column(name = "remindFrequency", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '提醒频率'")
    private RemindFrequency remindFrequency;

    /**
     * 提醒间隔值
     */
    @Column(name = "remindVal", nullable = false, columnDefinition = "INT(11)   COMMENT '提醒间隔值'")
    private Integer remindVal;

    /**
     * 汇总频率
     */
    @Column(name = "countFrequency", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '汇总频率'")
    private CountFrequency countFrequency;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public TotalType getTotalType() {
        return totalType;
    }

    public void setTotalType(TotalType totalType) {
        this.totalType = totalType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public String getCollectObject() {
        return collectObject;
    }

    public void setCollectObject(String collectObject) {
        this.collectObject = collectObject;
    }

    public Boolean getAll() {
        return all;
    }

    public void setAll(Boolean all) {
        this.all = all;
    }

    public RemindFrequency getRemindFrequency() {
        return remindFrequency;
    }

    public void setRemindFrequency(RemindFrequency remindFrequency) {
        this.remindFrequency = remindFrequency;
    }

    public Integer getRemindVal() {
        return remindVal;
    }

    public void setRemindVal(Integer remindVal) {
        this.remindVal = remindVal;
    }

    public CountFrequency getCountFrequency() {
        return countFrequency;
    }

    public void setCountFrequency(CountFrequency countFrequency) {
        this.countFrequency = countFrequency;
    }
}