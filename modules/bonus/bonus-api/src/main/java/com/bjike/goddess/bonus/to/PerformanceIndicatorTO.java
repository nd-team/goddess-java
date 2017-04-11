package com.bjike.goddess.bonus.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 绩效指标
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PerformanceIndicatorTO extends BaseTO {

    /**
     * 项目名称
     */
    @NotNull(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 项目类别
     */
    @NotNull(message = "项目类别不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 项目名称
     */
    @NotNull(message = "项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String name;

    /**
     * 指标描述
     */
    @NotNull(message = "指标描述不能为空", groups = {ADD.class, EDIT.class})
    private String description;

    /**
     * 计分方式
     */
    @NotNull(message = "计分方式不能为空", groups = {ADD.class, EDIT.class})
    private String way;

    /**
     * 参考目标值
     */
    @NotNull(message = "参考目标值不能为空", groups = {ADD.class, EDIT.class})
    private String target;

    /**
     * 获取数据的数据源字段编号
     */
    private String serialNumber;

    /**
     * 获取数据的数据源字段
     */
    private String field;

    /**
     * 数据源字段路径
     */
    private String route;

    /**
     * 系统或人工
     */
    @NotNull(message = "系统或人工不能为空", groups = {ADD.class, EDIT.class})
    private Boolean difference;

    /**
     * 状态
     */
    private Boolean status;


    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Boolean getDifference() {
        return difference;
    }

    public void setDifference(Boolean difference) {
        this.difference = difference;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}