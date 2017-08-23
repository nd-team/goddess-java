package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * 岗位说明书展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:38]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
public class PositionInstructionTO extends BaseTO {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空", groups = {ADD.class, EDIT.class})
    private String serialNumber;

    /**
     * 岗位详细id
     */
    @NotNull(message = "岗位id不能为空", groups = {ADD.class, EDIT.class})
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
     * 角度ID
     */
    @NotNull(message = "角度ID不能为空", groups = {ADD.class, EDIT.class})
    private String angleId;

    /**
     * 维度ID
     */
    @NotNull(message = "维度ID不能为空", groups = {ADD.class, EDIT.class})
    private String dimensionId;

    /**
     * 岗位说明书分类ID
     */
    @NotNull(message = "分类ID不能为空", groups = {ADD.class, EDIT.class})
    private String classifyId;

    /**
     * 操作类型id
     */
    private String[] operateIds;

    /**
     * 体现类别id
     */
    private String reflectId;

    /**
     * 工作描述
     */
    @NotNull(message = "工作描述不能为空", groups = {ADD.class, EDIT.class})
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

    public String getAngleId() {
        return angleId;
    }

    public void setAngleId(String angleId) {
        this.angleId = angleId;
    }

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

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

    public String getReflectId() {
        return reflectId;
    }

    public void setReflectId(String reflectId) {
        this.reflectId = reflectId;
    }

    public String[] getOperateIds() {
        return operateIds;
    }

    public void setOperateIds(String[] operateIds) {
        this.operateIds = operateIds;
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
}
