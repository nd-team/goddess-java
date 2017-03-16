package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 岗位说明书历史记录传输对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 14:08]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class PositionInstructionsLogTO extends BaseBO {

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 岗位id
     */
    private String positionId;

    /**
     * 岗位编号
     */
    private String positionNumber;

    /**
     * 岗位
     */
    private String positionName;

    /**
     * 岗位层级
     */
    private String arrangement;

    /**
     * 所属体系
     */
    private String hierarchy;

    /**
     * 所属部门
     */
    private String department;

    /**
     * 所属人员池
     */
    private String pool;

    /**
     * 编制人数
     */
    private Integer staff;

    /**
     * 直接上层
     */
    private String parent;

    /**
     * 直接下级
     */
    private String children;

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
    private String classify;

    /**
     * 操作类型id
     */
    private List<String> operateIds;


    /**
     * 操作类型
     */
    private String operates;

    /**
     * 体现类别
     */
    private List<String> reflectIds;

    /**
     * 体现类别
     */
    private String reflects;

    /**
     * 工作描述
     */
    private String description;

    /**
     * 对应功能
     */
    private String function;

    /**
     * 工作时间/频率
     */
    private String frequency;

    /**
     * 工作时间节点
     */
    private String timeNode;

    /**
     * 输出结果
     */
    private String outcome;

    /**
     * 记录时间
     */
    private String logTime;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionNumber() {
        return positionNumber;
    }

    public void setPositionNumber(String positionNumber) {
        this.positionNumber = positionNumber;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

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

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public List<String> getOperateIds() {
        return operateIds;
    }

    public void setOperateIds(List<String> operateIds) {
        this.operateIds = operateIds;
    }

    public String getOperates() {
        return operates;
    }

    public void setOperates(String operates) {
        this.operates = operates;
    }

    public List<String> getReflectIds() {
        return reflectIds;
    }

    public void setReflectIds(List<String> reflectIds) {
        this.reflectIds = reflectIds;
    }

    public String getReflects() {
        return reflects;
    }

    public void setReflects(String reflects) {
        this.reflects = reflects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }
}
