package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.to.UserLoginTO;

/**
 * 对外提供用户详情业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 15:24]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserLoginAPI {


    /**
     * 登录
     *
     * @param loginTO 用户登陆数据传输对象
     * @return token 登录用户唯一令牌
     * @throws SerException
     */
    String login(UserLoginTO loginTO) throws SerException;

    /**
     * 移动端登录
     *
     * @param loginTO 用户登陆数据传输对象
     * @return token 登录用户唯一令牌
     * @throws SerException
     */
    String logins(UserLoginTO loginTO) throws SerException;

    /**
     * 注销登陆
     *
     * @param token 登录用户唯一令牌
     * @return
     * @throws SerException
     */
    Boolean signOut(String token) throws SerException;

}
