package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.task.enums.DateType;
import com.bjike.goddess.task.enums.SortType;
import com.bjike.goddess.task.enums.TimeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 汇总方案
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-18 04:33 ]
 * @Description: [ 汇总方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "task_collectscheme")
public class CollectScheme extends BaseEntity {

    /**
     * 汇总方案
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '汇总方案'")
    private String name;

    /**
     * 选中汇总表
     */
    @Column(name = "tables", nullable = false, columnDefinition = "TEXT   COMMENT '选中汇总表(存id)'")
    private String tables;

    /**
     * 创建人
     */
    @Column(name = "creater", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String creater;

    /**
     * 汇总对象
     */
    @Column(name = "collectObject", nullable = false, columnDefinition = "TEXT   COMMENT '汇总对象'")
    private String collectObject;

    /**
     * 汇总频率
     */
    @Column(name = "collectType", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '汇总频率'")
    private DateType collectType;

    /**
     * 提醒频率
     */
    @Column(name = "remindType", nullable = false, columnDefinition = "TINYINT(2)  COMMENT '提醒频率'")
    private TimeType remindType;

    /**
     * 提醒间隔值
     */
    @Column(name = "remindVal",  columnDefinition = "INT(11)   COMMENT '提醒间隔值'")
    private Integer remindVal;

    /**
     * 发送时间
     */
    @Column(name = "sendTime",  columnDefinition = "DATETIME   COMMENT '发送时间'")
    private LocalDateTime sendTime;

    /**
     * 上次发送时间
     */
    @Column(name = "lastTime",  columnDefinition = "DATETIME   COMMENT '上次发送时间'")
    private LocalDateTime lastTime;

    /**
     * 是否启用
     */
    @Column(name = "is_enable", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否启用'")
    private Boolean enable;

    /**
     * 汇总表头排序
     */
    @Column(name = "sortType",  columnDefinition = "TINYINT(2)  COMMENT '汇总表头排序'")
    private SortType sortType;

    /**
     * 是否抄送本部门
     */
    @Column(name = "is_sendDepart", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否抄送本部门'")
    private Boolean sendDepart;

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCollectObject() {
        return collectObject;
    }

    public void setCollectObject(String collectObject) {
        this.collectObject = collectObject;
    }

    public DateType getCollectType() {
        return collectType;
    }

    public void setCollectType(DateType collectType) {
        this.collectType = collectType;
    }

    public TimeType getRemindType() {
        return remindType;
    }

    public void setRemindType(TimeType remindType) {
        this.remindType = remindType;
    }

    public Integer getRemindVal() {
        return remindVal;
    }

    public void setRemindVal(Integer remindVal) {
        this.remindVal = remindVal;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getSendDepart() {
        return sendDepart;
    }

    public void setSendDepart(Boolean sendDepart) {
        this.sendDepart = sendDepart;
    }
}