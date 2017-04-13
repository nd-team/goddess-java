package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.UserRoleBO;
import com.bjike.goddess.user.to.rbac.UserRoleTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserRoleAPI {
    /**
     * 保存用户角色
     *
     * @param userRoleTO
     * @return
     * @throws SerException
     */
    default UserRoleBO saveByTO(UserRoleTO userRoleTO) throws SerException {
        return null;
    }
}
