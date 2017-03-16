package com.bjike.goddess.organize.to;

import com.bjike.goddess.common.api.to.BaseTO;

import javax.persistence.Entity;
import java.util.List;

/**
 * 岗位说明书展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:38]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Entity
public class PositionInstructionTO  extends BaseTO {

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
     * 角度ID
     */
    private String angle_id;

    /**
     * 角度
     */
    private String angleName;

    /**
     * 维度ID
     */
    private String dimension_id;

    /**
     * 维度
     */
    private String dimensionName;

    /**
     * 分类ID
     */
    private String classify_id;

    /**
     * 分类
     */
    private String classifyName;

    /**
     * 操作类型id
     */
    private List<String> operateIds;


    /**
     * 操作类型
     */
    private String operateNames;

    /**
     * 体现类别
     */
    private List<String> reflectIds;

    /**
     * 体现类别
     */
    private String reflectNames;

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
     * 创建时间
     */
    private String createTime;

    public String getAngle_id() {
        return angle_id;
    }

    public void setAngle_id(String angle_id) {
        this.angle_id = angle_id;
    }

    public String getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(String dimension_id) {
        this.dimension_id = dimension_id;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
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

    public String getAngleName() {
        return angleName;
    }

    public void setAngleName(String angleName) {
        this.angleName = angleName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public List<String> getOperateIds() {
        return operateIds;
    }

    public void setOperateIds(List<String> operateIds) {
        this.operateIds = operateIds;
    }

    public String getOperateNames() {
        return operateNames;
    }

    public void setOperateNames(String operateNames) {
        this.operateNames = operateNames;
    }

    public List<String> getReflectIds() {
        return reflectIds;
    }

    public void setReflectIds(List<String> reflectIds) {
        this.reflectIds = reflectIds;
    }

    public String getReflectNames() {
        return reflectNames;
    }

    public void setReflectNames(String reflectNames) {
        this.reflectNames = reflectNames;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
