package com.bjike.goddess.user.login.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.IService;
import com.bjike.goddess.user.common.dto.UserDto;
import com.bjike.goddess.user.common.entity.User;
import com.bjike.goddess.user.login.dto.UserLoginDto;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: [用户登陆业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserLoginSer extends IService<User, UserDto> {

    /**
     * 验证是否已登陆
     *
     * @param token 登录用户唯一令牌
     * @return
     * @throws SerException
     */
    Boolean verify(String token) throws SerException;

    /**
     * 登录
     *
     * @param dto 用户登陆数据传输对象
     * @return token 登录用户唯一令牌
     * @throws SerException
     */
    String login(UserLoginDto dto) throws SerException;

    /**
     * 注销登陆
     *
     * @param token 登录用户唯一令牌
     * @return
     * @throws SerException
     */
    Boolean loginOut(String token) throws SerException;


}
