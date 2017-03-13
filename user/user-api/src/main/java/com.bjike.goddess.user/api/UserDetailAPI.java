package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserDetailBO;

/**
 * 对外提供用户详情接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 15:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserDetailAPI {
    /**
     * 通过用户id查找用户详情
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default UserDetailBO findByUserId(String userId) throws SerException {
        return null;
    }
}
