package com.bjike.goddess.user.vo.rbac;

/**
 * 角色权限值实体
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 14:00]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RolePermissionVO {
    /**
     * 角色权限id
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限id
     */
    private String permissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
