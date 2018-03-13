package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-01 11:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface UserSer {

    /**
     * 获取用户所属公司的编号
     *
     * @return class UserBO
     * @version v1
     */
    String getSystemId() throws SerException;
}
