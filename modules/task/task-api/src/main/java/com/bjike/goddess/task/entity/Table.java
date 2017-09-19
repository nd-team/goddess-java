package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.enums.ExecStatus;

import javax.persistence.*;
import java.util.Set;

/**
 * 表
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_table")
public class Table extends BaseEntity{
    /**
     * 表名称
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '表名称' ")
    private String name;
    /**
     * 所属项目
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", columnDefinition = "VARCHAR(36) COMMENT '所属项目' ")
    private Project project;
    /**
     * 行列表
     */
    @OneToMany(mappedBy = "table", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Row> rowSet;
    /**
     * 行列表
     */
    @OneToMany(mappedBy = "table", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Field> fieldSet;

    /**
     * 创建人
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '创建人' ")
    private String userId;

    /**
     * 执行状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '执行状态' ", nullable = false, insertable = false)
    private ExecStatus execStatus;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '表状态' ", nullable = false, insertable = false)
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }

    public Set<Row> getRowSet() {
        return rowSet;
    }

    public void setRowSet(Set<Row> rowSet) {
        this.rowSet = rowSet;
    }

    public Set<Field> getFieldSet() {
        return fieldSet;
    }

    public void setFieldSet(Set<Field> fieldSet) {
        this.fieldSet = fieldSet;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
