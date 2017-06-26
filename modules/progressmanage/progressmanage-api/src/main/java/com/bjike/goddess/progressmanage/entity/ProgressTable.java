package com.bjike.goddess.progressmanage.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;


/**
 * 进度表
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-09 04:46 ]
 * @Description: [ 进度表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "progressmanage_progresstable", uniqueConstraints = {@UniqueConstraint(columnNames = {"tabName","project_id"})})
public class ProgressTable extends BaseEntity {

    /**
     * 所属项目
     */
    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL )
    @JoinColumn(name = "project_id",nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '所属项目'")
    private ProjectInfo project;

    /**
     * 表名
     */
    @Column(name = "tabName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '表名'")
    private String tabName;

    /**
     * 创建人
     */
    @Column(name = "createUser", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String createUser;

    /**
     * 修改人
     */
    @Column(name = "updateUser", columnDefinition = "VARCHAR(255)   COMMENT '修改人'")
    private String updateUser;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, insertable = false, columnDefinition = "TINYINT(2) DEFAULT 0  COMMENT '状态'")
    private Status status;


    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}