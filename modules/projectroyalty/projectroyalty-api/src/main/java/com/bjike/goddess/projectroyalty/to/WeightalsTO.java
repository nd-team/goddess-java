package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 项目提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-20 03:34 ]
 * @Description: [ 项目提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightalsTO extends BaseTO {

//    /**
//     * 确定项目提成分配比例时间
//     */
//    private String time;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组/部门不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 是否立项
     */
    @NotNull(message = "是否立项不能为空", groups = {ADD.class, EDIT.class})
    private Boolean isBuild;

    /**
     * 立项时间
     */
    @NotBlank(message = "立项时间不能为空", groups = {ADD.class, EDIT.class})
    private String buildTime;

    /**
     * 是否完工
     */
    @NotNull(message = "是否完工不能为空", groups = {ADD.class, EDIT.class})
    private Boolean isComplete;

    /**
     * 类型集合
     */
//    @NotNull(message = "类型集合不能为空", groups = {ADD.class, EDIT.class})
    private List<WeightalTypeTO> weightalTypeTOs;

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

    public List<WeightalTypeTO> getWeightalTypeTOs() {
        return weightalTypeTOs;
    }

    public void setWeightalTypeTOs(List<WeightalTypeTO> weightalTypeTOs) {
        this.weightalTypeTOs = weightalTypeTOs;
    }
}