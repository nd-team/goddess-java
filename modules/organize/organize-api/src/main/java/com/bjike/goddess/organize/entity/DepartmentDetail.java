package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;

/**
 * 部门详细
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "organize_department_detail")
public class DepartmentDetail extends BaseEntity {

    /**
     * 编号
     */
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(24) COMMENT '编号'")
    private String serialNumber;

    /**
     * 体系
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "hierarchy_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '体系'")
    private Hierarchy hierarchy;

    /**
     * 项目组/部门
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) COMMENT '部门'", unique = true)
    private String department;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '部门状态' ", nullable = false, insertable = false)
    private Status status;

    /**
     * 所属地区
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '地区'")
    private String area;

    /**
     * 成本分类
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '成本分类'")
    private String classify;

    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '描述'")
    private String description;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
