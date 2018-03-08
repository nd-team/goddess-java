package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserBO;

/**
 * 用户操作业务
 * @Author: [caiwenxian]
 * @Date: [2018-03-01 09:48]
 * @Description: [ ]
 * @Version: [1.0.0]
 */
public interface UserServiceAPI {

    /**
     * 获取用户所属公司的编号
     *
     * @return class UserBO
     * @version v1
     */
    String getSystemId() throws SerException;
}
