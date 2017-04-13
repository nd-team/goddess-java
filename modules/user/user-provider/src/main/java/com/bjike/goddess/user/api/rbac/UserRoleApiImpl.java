package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.UserRoleBO;
import com.bjike.goddess.user.entity.rbac.UserRole;
import com.bjike.goddess.user.service.rbac.UserRoleSer;
import com.bjike.goddess.user.to.rbac.UserRoleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 16:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("userRoleApiImpl")
public class UserRoleApiImpl implements UserRoleAPI {
    @Autowired
    private UserRoleSer userRoleSer;
    @Override
    public UserRoleBO saveByTO(UserRoleTO userRoleTO) throws SerException {
        return userRoleSer.saveByTO(userRoleTO);
    }
}
