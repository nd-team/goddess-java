package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserLoginLogBO;
import com.bjike.goddess.user.to.UserLoginLogTO;

import java.util.List;

/**
 * 注册日志接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 16:38]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserLoginLogAPI {
    /**
     * 保存登录日志
     *
     * @param loginLogTO
     * @throws SerException
     */
    default void save(UserLoginLogTO loginLogTO) throws SerException {

    }

    ;

    /**
     * 获取用户登录日志，默认前5条（最多保存也是5条）
     *
     * @return
     */
    default List<UserLoginLogBO> findByUserId(String userId) throws SerException {
        return null;
    }

}
