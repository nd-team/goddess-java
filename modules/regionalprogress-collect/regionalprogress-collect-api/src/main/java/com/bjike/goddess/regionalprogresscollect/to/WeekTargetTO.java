package com.bjike.goddess.regionalprogresscollect.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.regionalprogresscollect.enums.CycleType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 周指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 03:13 ]
 * @Description: [ 周指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeekTargetTO extends BaseTO {

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
     * 年份
     */
    @NotNull(message = "年份不能为空", groups = {ADD.class, EDIT.class})
    private Integer year;

    /**
     * 月份
     */
    @NotNull(message = "月份不能为空", groups = {ADD.class, EDIT.class})
    private Integer month;

    /**
     * 周期
     */
    @NotNull(message = "周期不能为空", groups = {ADD.class, EDIT.class})
    private CycleType cycle;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public CycleType getCycle() {
        return cycle;
    }

    public void setCycle(CycleType cycle) {
        this.cycle = cycle;
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