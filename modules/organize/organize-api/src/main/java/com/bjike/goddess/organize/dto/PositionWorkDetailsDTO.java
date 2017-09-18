package com.bjike.goddess.organize.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;

/**
 * 岗位工作明细表数据传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-12 01:41 ]
 * @Description: [ 岗位工作明细表数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PositionWorkDetailsDTO extends BaseDTO {


    /**
     * 角度
     */
    private String angle;

    /**
     * 维度
     */
    private String dimension;

    /**
     * 分类
     */
    private String classification;

    /**
     * 项目阶段
     */
    private String projectStage;

    /**
     * 功能（流程）
     */
    private String function;

    /**
     * 工作频率
     */
    private String frequency;

    /**
     * 工作时间节点
     */
    private String timeNode;

    /**
     * 模块名
     */
    private String modulesName;

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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
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

    public String getModulesName() {
        return modulesName;
    }

    public void setModulesName(String modulesName) {
        this.modulesName = modulesName;
    }
}