package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.dto.ext.UserLoginDTO;
import com.bjike.goddess.user.entity.User;

/**
 * 用户登陆业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-24 09:37]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserLoginAPI extends SerAPI<User, UserDTO> {

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
    String login(UserLoginDTO dto) throws SerException;

    /**
     * 注销登陆
     *
     * @param token 登录用户唯一令牌
     * @return
     * @throws SerException
     */
    Boolean loginOut(String token) throws SerException;


}
