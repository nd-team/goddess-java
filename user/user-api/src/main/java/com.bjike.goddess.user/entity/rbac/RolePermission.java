package com.bjike.goddess.user.entity.rbac;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;

/**
 * 角色资源
 *
 * @Author: [liguiqin]
 * @Date: [2017-02-28 09:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "rbac_role_permission")
public class RolePermission extends BaseEntity {
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '资源id' ")
    private Permission permission;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false, columnDefinition = "VARCHAR(36) COMMENT '角色id' ")
    private Role role;

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
