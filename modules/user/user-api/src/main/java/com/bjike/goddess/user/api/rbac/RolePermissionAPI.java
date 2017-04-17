package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.bo.rbac.RolePermissionBO;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;
import com.bjike.goddess.user.to.rbac.RolePermissionTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 14:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RolePermissionAPI {
    /**
     * 保存角色权限
     *
     * @param rolePermissionTO
     * @return
     * @throws SerException
     */
    default RolePermissionBO saveByTO(RolePermissionTO rolePermissionTO) throws SerException {
        return null;
    }
}
