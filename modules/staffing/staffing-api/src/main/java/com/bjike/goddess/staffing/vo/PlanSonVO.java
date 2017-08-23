package com.bjike.goddess.staffing.vo;

/**
 * 人数配置-计划子表表现层对象
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:58 ]
 * @Description: [ 人数配置-计划子表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PlanSonVO {

    /**
     * id
     */
    private String id;
    /**
     * 部门
     */
    private String depart;

    /**
     * 部门权重
     */
    private String departWeight;

    /**
     * 项目分类
     */
    private String project;

    /**
     * 决策层人数
     */
    private Integer policyNum;

    /**
     * 管理层人数
     */
    private Integer manNum;

    /**
     * 管理层占比
     */
    private String manProportion;

    /**
     * 执行层人数
     */
    private Integer executeNum;

    /**
     * 执行层占比
     */
    private String executeProportion;

    /**
     * 编制汇总
     */
    private Integer compileCount;

    /**
     * 调配人数
     */
    private Integer deployNum;

    /**
     * 目前实际编制人数
     */
    private Integer actualNum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDepartWeight() {
        return departWeight;
    }

    public void setDepartWeight(String departWeight) {
        this.departWeight = departWeight;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(Integer policyNum) {
        this.policyNum = policyNum;
    }

    public Integer getManNum() {
        return manNum;
    }

    public void setManNum(Integer manNum) {
        this.manNum = manNum;
    }

    public String getManProportion() {
        return manProportion;
    }

    public void setManProportion(String manProportion) {
        this.manProportion = manProportion;
    }

    public Integer getExecuteNum() {
        return executeNum;
    }

    public void setExecuteNum(Integer executeNum) {
        this.executeNum = executeNum;
    }

    public String getExecuteProportion() {
        return executeProportion;
    }

    public void setExecuteProportion(String executeProportion) {
        this.executeProportion = executeProportion;
    }

    public Integer getCompileCount() {
        return compileCount;
    }

    public void setCompileCount(Integer compileCount) {
        this.compileCount = compileCount;
    }

    public Integer getDeployNum() {
        return deployNum;
    }

    public void setDeployNum(Integer deployNum) {
        this.deployNum = deployNum;
    }

    public Integer getActualNum() {
        return actualNum;
    }

    public void setActualNum(Integer actualNum) {
        this.actualNum = actualNum;
    }
}