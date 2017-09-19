package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.task.enums.ExecStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 任务
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-19 09:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task")
public class Task extends BaseEntity {
    /**
     * 下达人
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '下达人' ")
    private String issuedUser;
    /**
     * 执行人
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '执行人' ")
    private String execUser;
    /**
     * 任务量
     */
    @Column(columnDefinition = "INT(8) COMMENT '任务量'",nullable = false)
    private Integer num;
    /**
     * 所需时长分钟
     */
    @Column(columnDefinition = "INT(10) COMMENT '所需时长分钟'",nullable = false)
    private Integer minute;
    /**
     * 任务开始时间
     */
    @Column(columnDefinition = "DATETIME  COMMENT '任务开始时间'", nullable = false)

    private LocalDateTime startTime;
    /**
     * 任务结束时间
     */
    @Column(columnDefinition = "DATETIME  COMMENT '任务结束时间'", nullable = false)

    private LocalDateTime endTime;
    /**
     * 任务备注
     */
    @Column(columnDefinition = "VARCHAR(255) comment '任务备注' ")
    private String remark;

    /**
     * 执行状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '执行状态' ", nullable = false, insertable = false)
    private ExecStatus execStatus;

    /**
     * 所属任务行
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", columnDefinition = "VARCHAR(36) COMMENT '所任务行' ",nullable = false)
    private Row row;
    /**
     * 所属表节点
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) comment '所属表节点' ")
    private String node;

    public String getIssuedUser() {
        return issuedUser;
    }

    public void setIssuedUser(String issuedUser) {
        this.issuedUser = issuedUser;
    }

    public String getExecUser() {
        return execUser;
    }

    public void setExecUser(String execUser) {
        this.execUser = execUser;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}
