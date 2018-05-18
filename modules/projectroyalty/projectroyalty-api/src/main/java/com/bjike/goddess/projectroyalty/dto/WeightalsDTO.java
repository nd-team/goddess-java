package com.bjike.goddess.projectroyalty.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.projectroyalty.enums.Type;

/**
 * 项目提成权重分配表数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-20 03:34 ]
 * @Description: [ 项目提成权重分配表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightalsDTO extends BaseDTO {
    /**
     * 确定项目提成分配比例时间
     */
    private String time;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 内部项目名称
     */
    private String project;

    /**
     * 是否立项
     */
    private Boolean isBuild;

    /**
     * 立项时间
     */
    private String buildTime;

    /**
     * 是否完工
     */
    private Boolean isComplete;

//    /**
//     * 类型
//     */
//    private Type type;
//
//    /**
//     * 完工时间（月）
//     */
//    private Integer month;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Boolean getBuild() {
        return isBuild;
    }

    public void setBuild(Boolean build) {
        isBuild = build;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }


//    public Integer getMonth() {
//        return month;
//    }
//
//    public void setMonth(Integer month) {
//        this.month = month;
//    }
//
//    public Type getType() {
//
//        return type;
//    }
//
//    public void setType(Type type) {
//        this.type = type;
//    }
}