package com.bjike.goddess.regionalprogresscollect.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 日指标业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:15 ]
 * @Description: [ 日指标业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayTargetBO extends BaseBO {

    /**
     * 参考指标
     */
    private String targetId;


    /**
     * 地区
     */
    private String area;

    /**
     * 类别
     */
    private String type;


    /**
     * 节点
     */
    private String node;

    /**
     * 计划每天任务量
     */
    private Double dayTask;

    /**
     * 计划每天人工
     */
    private Double dayArtificial;


    /**
     * 日期
     */
    private String targetDate;

    /**
     * 节点标准
     */
    private Double standard;

    /**
     * 计划任务数
     */
    private Double taskPlan;

    /**
     * 实际完工数
     */
    private Double taskActual;

    /**
     * 计划人工
     */
    private Double artificialPlan;

    /**
     * 实际人工
     */
    private Double artificialActual;

    /**
     * 计划任务量人工指标
     */
    private Double taskPlanTarget;

    /**
     * 计划任务量人工指标预警
     */
    private Double taskPlanWarn;

    /**
     * 计划人工完工任务量指标
     */
    private Double artificialPlanTarget;

    /**
     * 计划人工完工任务量指标预警
     */
    private Double artificialPlanWarn;

    /**
     * 实际完工任务量人工指标
     */
    private Double taskActualTarget;

    /**
     * 实际完工任务量人工指标预警
     */
    private Double taskActualWarn;

    /**
     * 实际人工完工任务量指标
     */
    private Double artificialActualTarget;

    /**
     * 实际人工完工任务量指标预警
     */
    private Double artificialActualWarn;

    /**
     * 未完成任务量
     */
    private Double undone;

    /**
     * 实际人工效率
     */
    private Double rate;

    /**
     * 效率差
     */
    private Double difference;


    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Double getDayTask() {
        return dayTask;
    }

    public void setDayTask(Double dayTask) {
        this.dayTask = dayTask;
    }

    public Double getDayArtificial() {
        return dayArtificial;
    }

    public void setDayArtificial(Double dayArtificial) {
        this.dayArtificial = dayArtificial;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
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