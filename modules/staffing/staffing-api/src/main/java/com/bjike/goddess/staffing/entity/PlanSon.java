package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 人数配置-计划子表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-29 10:58 ]
 * @Description: [ 人数配置-计划子表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_planson")
public class PlanSon extends BaseEntity {

    /**
     * 部门
     */
    @Column(name = "depart", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String depart;

    /**
     * 部门权重
     */
    @Column(name = "departWeight", columnDefinition = "VARCHAR(255)   COMMENT '部门权重'")
    private String departWeight;

    /**
     * 项目分类
     */
    @Column(name = "project", columnDefinition = "VARCHAR(255)   COMMENT '项目分类'")
    private String project;

    /**
     * 决策层人数
     */
    @Column(name = "policyNum", nullable = false, columnDefinition = "INT(11)   COMMENT '决策层人数'")
    private Integer policyNum;

    /**
     * 管理层人数
     */
    @Column(name = "manNum", nullable = false, columnDefinition = "INT(11)   COMMENT '管理层人数'")
    private Integer manNum;

    /**
     * 管理层占比
     */
    @Column(name = "manProportion", columnDefinition = "VARCHAR(255)   COMMENT '管理层占比'")
    private String manProportion;

    /**
     * 执行层人数
     */
    @Column(name = "executeNum", nullable = false, columnDefinition = "INT(11)    COMMENT '执行层人数'")
    private Integer executeNum;

    /**
     * 执行层占比
     */
    @Column(name = "executeProportion", columnDefinition = "VARCHAR(255)   COMMENT '执行层占比'")
    private String executeProportion;

    /**
     * 编制汇总
     */
    @Column(name = "compileCount", columnDefinition = "INT(11)   COMMENT '编制汇总'")
    private Integer compileCount;

    /**
     * 调配人数
     */
    @Column(name = "deployNum",columnDefinition = "INT(11)   COMMENT '调配人数'")
    private Integer deployNum;

    /**
     * 目前实际编制人数
     */
    @Column(name = "actualNum", columnDefinition = "INT(11)   COMMENT '目前实际编制人数'")
    private Integer actualNum;

    /**
     * 人数配置-计划信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "configurationPlan_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '人数配置-计划信息'")
    private ConfigurationPlan configurationPlan;

    public ConfigurationPlan getConfigurationPlan() {
        return configurationPlan;
    }

    public void setConfigurationPlan(ConfigurationPlan configurationPlan) {
        this.configurationPlan = configurationPlan;
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