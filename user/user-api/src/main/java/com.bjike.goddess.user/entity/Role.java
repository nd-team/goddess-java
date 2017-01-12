package com.bjike.goddess.user.entity;


import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户角色]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_role")
public class Role extends BaseEntity {
    @Column(unique = true)
    private String name;//角色名
    private String description;//描述
    private Status status;//状态
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime ; //创建时间

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Role parent;//父角色

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
     @JoinTable(name="user_role_permission",joinColumns={@JoinColumn(name="role_id",nullable = false)},
            inverseJoinColumns={@JoinColumn(name="permission_id",nullable = false)})
    private List<Permission> permissionList; //角色拥有的权限资源

    @ManyToMany(mappedBy="roleList",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Group> groupList;//所属用户组

    @ManyToMany(mappedBy="roleList",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Department> departmentList;//所属部门

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

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
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

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }
}
