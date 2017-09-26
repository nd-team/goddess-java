package com.bjike.goddess.task.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;

/**
 * 项目可见范围
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-18 16:31]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@javax.persistence.Table(name = "task_project_range")
public class ProjectRange extends BaseEntity {

    @OneToOne(optional = false, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pid", nullable = false, columnDefinition = "VARCHAR(36) comment '所属项目' ")
    @JSONField(serialize = false)
    private Project project;

    @Column( columnDefinition = "TEXT comment '可见人id' ")
    private String users;

    @Column( columnDefinition = "TEXT comment '可见部门id' ")
    private String departments;

    @Column(columnDefinition = "TEXT comment '可见项目组id' ")
    private String groups;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }
}
