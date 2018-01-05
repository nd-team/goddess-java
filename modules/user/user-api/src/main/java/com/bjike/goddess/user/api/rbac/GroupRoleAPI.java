package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupRoleAPI {
    /**
     * 保存组
     *
     * @param groupRoleTO
     * @return
     * @throws SerException
     */
    default GroupRoleBO saveByTO(GroupRoleTO groupRoleTO) throws SerException {
        return null;
    }
}
