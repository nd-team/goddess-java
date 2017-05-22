package com.bjike.goddess.regionalprogresscollect.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * 参考指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-17 02:56 ]
 * @Description: [ 参考指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ReferenceTargetTO extends BaseTO {

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
     * 计划每月任务量
     */
    @NotNull(message = "计划每月任务量不能为空", groups = {ADD.class, EDIT.class})
    private Double monthTask;

    /**
     * 计划每月人工
     */
    @NotNull(message = "计划每月人工不能为空", groups = {ADD.class, EDIT.class})
    private Double monthArtificial;

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

    public Double getMonthTask() {
        return monthTask;
    }

    public void setMonthTask(Double monthTask) {
        this.monthTask = monthTask;
    }

    public Double getMonthArtificial() {
        return monthArtificial;
    }

    public void setMonthArtificial(Double monthArtificial) {
        this.monthArtificial = monthArtificial;
    }

}