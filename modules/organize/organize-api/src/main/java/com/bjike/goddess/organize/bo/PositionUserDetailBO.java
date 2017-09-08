package com.bjike.goddess.organize.bo;

import com.bjike.goddess.organize.enums.AgentType;
import com.bjike.goddess.organize.enums.WorkStatus;

/**
 * Created by ike on 17-9-6.
 */
public class PositionUserDetailBO {
    /**
     * 体系编号
     */
    private String hierarchyNumber;
    /**
     * 体系
     */
    private String hierarchy;
    /**
     * 所属地区
     */
    private String area;
    /**
     * 部门编号
     */
    private String departNumber;
    /**
     * 项目组/部门
     */
    private String department;
    /**
     * 轮岗层级
     */
    private String rotationLevel;
    /**
     * 岗位层级
     */
    private String arrangement;
    /**
     * 模块
     */
    private String module;
    /**
     * 岗位名称
     */
    private String position;
    /**
     * 岗位编号
     */
    private String positionNumber;
    /**
     * 担任状态
     */
    private WorkStatus workStatus;
    /**
     * 是否为代理岗位
     */
    private Boolean agent;
    /**
     * 代理类型
     */
    private AgentType agentType;

    public String getHierarchyNumber() {
        return hierarchyNumber;
    }

    public void setHierarchyNumber(String hierarchyNumber) {
        this.hierarchyNumber = hierarchyNumber;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartNumber() {
        return departNumber;
    }

    public void setDepartNumber(String departNumber) {
        this.departNumber = departNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRotationLevel() {
        return rotationLevel;
    }

    public void setRotationLevel(String rotationLevel) {
        this.rotationLevel = rotationLevel;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
    }

    public Boolean getAgent() {
        return agent;
    }

    public void setAgent(Boolean agent) {
        this.agent = agent;
    }

    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }
}
