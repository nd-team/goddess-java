package com.bjike.goddess.task.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.enums.ExecStatus;

import javax.persistence.*;
import java.util.Set;

/**
 * 项目
 *
 * @Author: [liguiqin]
 * @Date: [2016-12-24 10:16]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_project")
public class Project extends BaseEntity {
    /**
     * 项目名
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) comment '项目名' ")
    private String name;
    /**
     * 地区
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(255) comment '地区' ")
    private String area;
    /**
     * 创建人
     */
    @Column(nullable = false, columnDefinition = "VARCHAR(36) comment '创建人' ")
    private String userId;
    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(255) comment '描述' ")
    private String description;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '项目状态' ", nullable = false, insertable = false)
    private Status status;
    /**
     * 执行状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '执行状态' ", nullable = false, insertable = false)
    private ExecStatus execStatus;

    /**
     * 表
     */
    @OneToMany(mappedBy = "project", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Table> tableSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public ExecStatus getExecStatus() {
        return execStatus;
    }

    public void setExecStatus(ExecStatus execStatus) {
        this.execStatus = execStatus;
    }

    public Set<Table> getTableSet() {
        return tableSet;
    }

    public void setTableSet(Set<Table> tableSet) {
        this.tableSet = tableSet;
    }
}
