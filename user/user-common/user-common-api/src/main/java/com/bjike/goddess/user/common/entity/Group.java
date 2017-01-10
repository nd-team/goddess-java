package com.bjike.goddess.user.common.entity;

import com.bjike.goddess.dbs.common.entity.BaseEntity;
import com.bjike.goddess.dbs.common.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 09:09]
 * @Description: [用户组]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Entity
@Table(name = "user_group")
public class Group extends BaseEntity {

    @Column(unique = true,nullable = false)
    private String name; //组名
    private String description; //描述
    private Status status; //状态
    @Column(columnDefinition = "dateTime")
    private LocalDateTime createTime; //创建时间

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="user_group_role",joinColumns={@JoinColumn(name="group_id",nullable = false)},
            inverseJoinColumns={@JoinColumn(name="role_id",nullable = false)})
    private List<Role> roleList; //用户组拥有角色

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
