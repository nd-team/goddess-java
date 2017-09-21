package com.bjike.goddess.taskallotment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.taskallotment.enums.Status;

import javax.persistence.*;


/**
 * 项目表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@javax.persistence.Table(name = "taskallotment_table")
public class Table extends BaseEntity {

    /**
     * 表名称
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '表名称'")
    private String name;

    /**
     * 项目信息
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id",nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '项目信息'")
    private Project project;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}