package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.bo.rbac.GroupUserBO;
import com.bjike.goddess.user.entity.rbac.GroupUser;
import com.bjike.goddess.user.service.rbac.GroupRoleSer;
import com.bjike.goddess.user.service.rbac.GroupUserSer;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;
import com.bjike.goddess.user.to.rbac.GroupUserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("groupUserApiImpl")
public class GroupUserApiImpl implements GroupUserAPI {
    @Autowired
    private GroupUserSer groupUserSer;

    @Override
    public GroupUserBO saveByTO(GroupUserTO groupUserTO) throws SerException {
        return groupUserSer.saveByTO(groupUserTO);
    }
}
