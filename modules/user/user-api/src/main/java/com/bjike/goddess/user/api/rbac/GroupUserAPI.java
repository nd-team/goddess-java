package com.bjike.goddess.user.api.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.rbac.GroupRoleBO;
import com.bjike.goddess.user.bo.rbac.GroupUserBO;
import com.bjike.goddess.user.to.rbac.GroupRoleTO;
import com.bjike.goddess.user.to.rbac.GroupUserTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 13:54]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface GroupUserAPI {
    /**
     * 保存组用户
     *
     * @param groupUserTO
     * @return
     * @throws SerException
     */
    default GroupUserBO saveByTO(GroupUserTO groupUserTO) throws SerException {
        return null;
    }
}
