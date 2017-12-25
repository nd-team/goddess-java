package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.service.rbac.GroupRoleSer;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("groupRoleApiImpl")
public class GroupRoleApiImpl implements GroupRoleAPI {
    @Autowired
    private GroupRoleSer groupRoleSer;

    @Override
    public GroupRoleBO saveByTO(GroupRoleTO groupRoleTO) throws SerException {
        return groupRoleSer.saveByTO(groupRoleTO);
    }
}
