package com.bjike.goddess.user.common.entity;

import com.bjike.goddess.dbs.common.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-24 10:16]
 * @Description: [部门]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "user_department")
public class Department extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String name;//部门名
    private String description; //描述
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime; //创建时间

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Department parent; //上级部门

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="user_department_role",joinColumns={@JoinColumn(name="department_id",nullable = false)},
            inverseJoinColumns={@JoinColumn(name="role_id",nullable = false)})
    private List<Role> roleList; //部门拥有角色

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }
}
