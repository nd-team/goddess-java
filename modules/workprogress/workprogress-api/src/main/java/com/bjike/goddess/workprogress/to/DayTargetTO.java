package com.bjike.goddess.workprogress.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 日指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:15 ]
 * @Description: [ 日指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DayTargetTO extends BaseTO {

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 类别
     */
    @NotBlank(message = "类别不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 节点
     */
    @NotBlank(message = "节点不能为空", groups = {ADD.class, EDIT.class})
    private String node;

    /**
     * 日期
     */
    @NotBlank(message = "日期不能为空", groups = {ADD.class, EDIT.class})
    private String targetDate;

    /**
     * 计划任务数
     */
    @NotNull(message = "计划任务数不能为空", groups = {ADD.class, EDIT.class})
    private Double taskPlan;

    /**
     * 实际完工数
     */
    @NotNull(message = "实际完工数不能为空", groups = {ADD.class, EDIT.class})
    private Double taskActual;

    /**
     * 计划人工
     */
    @NotNull(message = "计划人工不能为空", groups = {ADD.class, EDIT.class})
    private Double artificialPlan;

    /**
     * 实际人工
     */
    @NotNull(message = "实际人工不能为空", groups = {ADD.class, EDIT.class})
    private Double artificialActual;

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

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
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

}