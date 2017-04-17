package com.bjike.goddess.user.to.rbac;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户角色传输对象
 *
 * @Author: [liguiqin]
 * @Date: [2017-04-13 16:41]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class UserRoleTO extends BaseTO {
    /**
     * 用户id
     */
    @NotBlank(message = "用户id不能为空", groups = ADD.class)
    private String userId;
    /**
     * 角色id
     */
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
