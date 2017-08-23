package com.bjike.goddess.user.bo.rbac;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 16:39]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserRoleBO extends BaseBO {
    private String userId;
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
