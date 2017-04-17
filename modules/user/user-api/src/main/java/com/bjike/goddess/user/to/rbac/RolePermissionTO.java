package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 角色权限传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:55]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RolePermissionTO extends BaseTO {
    /**
     * 角色id
     */
    @NotBlank(message = "角色id不能为空", groups = ADD.class)
    private String roleId;
    /**
     * 权限id
     */
    @NotBlank(message = "权限id不能为空", groups = ADD.class)
    private String permissionId;

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
