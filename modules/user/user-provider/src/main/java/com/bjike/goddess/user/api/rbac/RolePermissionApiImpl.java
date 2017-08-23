package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.bo.rbac.RolePermissionBO;
import com.bjike.goddess.user.service.rbac.GroupRoleSer;
import com.bjike.goddess.user.service.rbac.RolePermissionSer;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;
import com.bjike.goddess.user.to.rbac.RolePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("rolePermissionApiImpl")
public class RolePermissionApiImpl implements RolePermissionAPI {
    @Autowired
    private RolePermissionSer rolePermissionSer;

    @Override
    public RolePermissionBO saveByTO(RolePermissionTO rolePermissionTO) throws SerException {
        return rolePermissionSer.saveByTO(rolePermissionTO);
    }
}
