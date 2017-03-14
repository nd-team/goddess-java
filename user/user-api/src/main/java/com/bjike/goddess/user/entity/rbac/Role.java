package com.bjike.goddess.user.entity.rbac;


import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 角色
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "rbac_role")
public class Role extends BaseEntity {
    /**
     * 角色名
     */
    @Column(unique = true, columnDefinition = "VARCHAR(255) COMMENT '角色名' ")
    private String name;
    /**
     * 描述
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '角色描述' ")
    private String description;
    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '角色状态'", nullable = false, insertable = false)
    private Status status;


    /**
     * 父角色
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", columnDefinition = "VARCHAR(36) COMMENT '角色上级' ")
    private Role parent;

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

    public Role getParent() {
        return parent;
    }

    public void setParent(Role parent) {
        this.parent = parent;
    }
}
