package com.bjike.goddess.regionalprogresscollect.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.regionalprogresscollect.enums.CycleType;

import javax.persistence.*;


/**
 * 周指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 周指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "regionalprogresscollect_week_target")
public class WeekTarget extends BaseEntity {

    /**
     * 参考指标
     */
    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "target_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '参考指标'")
    private ReferenceTarget target;

    /**
     * 周期
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "TINYINT(2) DEFAULT 1   COMMENT '周期'")
    private CycleType cycle;

    /**
     * 节点标准
     */
    @Column(name = "standard", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '节点标准'")
    private Double standard;

    /**
     * 计划任务数
     */
    @Column(name = "taskPlan", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划任务数'")
    private Double taskPlan;

    /**
     * 实际完工数
     */
    @Column(name = "taskActual", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际完工数'")
    private Double taskActual;

    /**
     * 计划人工
     */
    @Column(name = "artificialPlan", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划人工'")
    private Double artificialPlan;

    /**
     * 实际人工
     */
    @Column(name = "artificialActual", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工'")
    private Double artificialActual;

    /**
     * 计划任务量人工指标
     */
    @Column(name = "taskPlanTarget", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划任务量人工指标'")
    private Double taskPlanTarget;

    /**
     * 计划任务量人工指标预警
     */
    @Column(name = "taskPlanWarn", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划任务量人工指标预警'")
    private Double taskPlanWarn;

    /**
     * 计划人工完工任务量指标
     */
    @Column(name = "artificialPlanTarget", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划人工完工任务量指标'")
    private Double artificialPlanTarget;

    /**
     * 计划人工完工任务量指标预警
     */
    @Column(name = "artificialPlanWarn", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划人工完工任务量指标预警'")
    private Double artificialPlanWarn;

    /**
     * 实际完工任务量人工指标
     */
    @Column(name = "taskActualTarget", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际完工任务量人工指标'")
    private Double taskActualTarget;

    /**
     * 实际完工任务量人工指标预警
     */
    @Column(name = "taskActualWarn", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际完工任务量人工指标预警'")
    private Double taskActualWarn;

    /**
     * 实际人工完工任务量指标
     */
    @Column(name = "artificialActualTarget", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工完工任务量指标'")
    private Double artificialActualTarget;

    /**
     * 实际人工完工任务量指标预警
     */
    @Column(name = "artificialActualWarn", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工完工任务量指标预警'")
    private Double artificialActualWarn;

    /**
     * 未完成任务量
     */
    @Column(name = "undone", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '未完成任务量'")
    private Double undone;

    /**
     * 实际人工效率
     */
    @Column(name = "rate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '实际人工效率'")
    private Double rate;

    /**
     * 效率差
     */
    @Column(name = "difference", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '效率差'")
    private Double difference;


    public ReferenceTarget getTarget() {
        return target;
    }

    public void setTarget(ReferenceTarget target) {
        this.target = target;
    }

    public CycleType getCycle() {
        return cycle;
    }

    public void setCycle(CycleType cycle) {
        this.cycle = cycle;
    }

    public Double getStandard() {
        return standard;
    }

    public void setStandard(Double standard) {
        this.standard = standard;
    }

    public Double getTaskPlan() {
        return taskPlan;
    }

    public void setTaskPlan(Double taskPlan) {
        this.taskPlan = taskPlan;
    }

    public Double getTaskActual() {
        return taskActual;
    }

    public void setTaskActual(Double taskActual) {
        this.taskActual = taskActual;
    }

    public Double getArtificialPlan() {
        return artificialPlan;
    }

    public void setArtificialPlan(Double artificialPlan) {
        this.artificialPlan = artificialPlan;
    }

    public Double getArtificialActual() {
        return artificialActual;
    }

    public void setArtificialActual(Double artificialActual) {
        this.artificialActual = artificialActual;
    }

    public Double getTaskPlanTarget() {
        return taskPlanTarget;
    }

    public void setTaskPlanTarget(Double taskPlanTarget) {
        this.taskPlanTarget = taskPlanTarget;
    }

    public Double getTaskPlanWarn() {
        return taskPlanWarn;
    }

    public void setTaskPlanWarn(Double taskPlanWarn) {
        this.taskPlanWarn = taskPlanWarn;
    }

    public Double getArtificialPlanTarget() {
        return artificialPlanTarget;
    }

    public void setArtificialPlanTarget(Double artificialPlanTarget) {
        this.artificialPlanTarget = artificialPlanTarget;
    }

    public Double getArtificialPlanWarn() {
        return artificialPlanWarn;
    }

    public void setArtificialPlanWarn(Double artificialPlanWarn) {
        this.artificialPlanWarn = artificialPlanWarn;
    }

    public Double getTaskActualTarget() {
        return taskActualTarget;
    }

    public void setTaskActualTarget(Double taskActualTarget) {
        this.taskActualTarget = taskActualTarget;
    }

    public Double getTaskActualWarn() {
        return taskActualWarn;
    }

    public void setTaskActualWarn(Double taskActualWarn) {
        this.taskActualWarn = taskActualWarn;
    }

    public Double getArtificialActualTarget() {
        return artificialActualTarget;
    }

    public void setArtificialActualTarget(Double artificialActualTarget) {
        this.artificialActualTarget = artificialActualTarget;
    }

    public Double getArtificialActualWarn() {
        return artificialActualWarn;
    }

    public void setArtificialActualWarn(Double artificialActualWarn) {
        this.artificialActualWarn = artificialActualWarn;
    }

    public Double getUndone() {
        return undone;
    }

    public void setUndone(Double undone) {
        this.undone = undone;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(Double difference) {
        this.difference = difference;
    }
}