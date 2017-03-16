package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * 岗位说明书历史记录
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "organize_position_instruction_log")
public class PositionInstructionsLog extends BaseEntity {

    /**
     * 岗位
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id",columnDefinition = "VARCHAR(36) COMMENT ''")
    private PositionDetail position;

    /**
     * 编号
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '编号'")
    private String serialNumber;

    /**
     * 角度
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '角度'")
    private String angle;

    /**
     * 维度
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '维度'")
    private String dimension;

    /**
     * 操作类型
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '操作类型'")
    private String operates;

    /**
     * 体现类别
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '体现类别'")
    private String reflects;


    /**
     * 工作描述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '工作描述'")
    private String description;

    /**
     * 对应功能
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '对应功能'")
    private String function;

    /**
     * 工作时间/频率
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作时间/频率'")
    private String frequency;

    /**
     * 工作时间节点
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '工作时间节点'")
    private String timeNode;

    /**
     * 输出结果
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '输出结果'")
    private String outcome;

    /**
     * 记录时间
     */
    @Column(columnDefinition = "datetime",nullable = false)
    private LocalDateTime logTime;

    public PositionDetail getPosition() {
        return position;
    }

    public void setPosition(PositionDetail position) {
        this.position = position;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getOperates() {
        return operates;
    }

    public void setOperates(String operates) {
        this.operates = operates;
    }

    public String getReflects() {
        return reflects;
    }

    public void setReflects(String reflects) {
        this.reflects = reflects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }
}
