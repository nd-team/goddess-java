package com.bjike.goddess.user.bo.rbac;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 组业务传输
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-01 11:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class GroupRoleBO extends BaseBO {
    private String groupId;
    private String roleId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
