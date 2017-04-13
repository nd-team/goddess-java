package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 16:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserRoleTO implements Serializable {

    @NotBlank(message = "用户id不能为空", groups = ADD.class)
    private String userId;

    @NotBlank(message = "角色id不能为空", groups = ADD.class)
    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
