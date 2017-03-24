package com.bjike.goddess.organize.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * 工作范围信息设置
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "organize_work_range")
public class WorkRange extends BaseEntity {

    /**
     * 方向
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '方向'")
    private String direction;

    /**
     * 科目
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '科目'")
    private String project;

    /**
     * 专业分类
     */
    @Column(columnDefinition = "VARCHAR(50) COMMENT '专业分类'")
    private String classify;

    /**
     * 工作范围
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '工作范围'")
    private String workRange;

    /**
     * 工作界面(节点)
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '工作界面(节点)'")
    private String node;

    /**
     * 使用状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '状态'", nullable = false, insertable = false)
    private Status status;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "DATETIME COMMENT '创建时间'", nullable = false)
    private LocalDateTime createTime;

    /**
     * 对应部门
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "organize_work_range_department",
            joinColumns = {@JoinColumn(name = "range_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '工作范围'")},
            inverseJoinColumns = {@JoinColumn(nullable = false, name = "department_id", columnDefinition = "VARCHAR(36) COMMENT '部门详细'")})
    private Set<DepartmentDetail> departments = new HashSet<>(0);

    public Set<DepartmentDetail> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentDetail> departments) {
        this.departments = departments;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getWorkRange() {
        return workRange;
    }

    public void setWorkRange(String workRange) {
        this.workRange = workRange;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
