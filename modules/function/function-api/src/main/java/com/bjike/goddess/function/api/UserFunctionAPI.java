package com.bjike.goddess.function.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.function.to.UserFunctionTO;

/**
 * 用户功能业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-22 01:56 ]
 * @Description: [ 用户功能业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface UserFunctionAPI {

    /**
     * 添加用户功能
     *
     * @param userFunctionTO
     */
    default void add(UserFunctionTO userFunctionTO) throws SerException {

    }

    /**
     * 删除用户功能
     *
     * @param functionId
     */
    default void delete(String functionId) throws SerException {

    }

}