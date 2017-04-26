package com.bjike.goddess.organize.vo;

import com.bjike.goddess.common.api.type.Status;

/**
 * 部门详细展示对象
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DepartmentDetailVO {


    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 体系ID
     */
    private String hierarchyId;

    /**
     * 体系编号
     */
    private String hierarchyNumber;

    /**
     * 体系
     */
    private String hierarchyName;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 所属地区
     */
    private String area;

    /**
     * 成本分类
     */
    private String classify;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 显示编号
     */
    private String showNumber;

    /**
     * id
     */
    private String id;

    /**
     * 部门状态
     */
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(String hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getHierarchyNumber() {
        return hierarchyNumber;
    }

    public void setHierarchyNumber(String hierarchyNumber) {
        this.hierarchyNumber = hierarchyNumber;
    }

    public String getHierarchyName() {
        return hierarchyName;
    }

    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }
}
