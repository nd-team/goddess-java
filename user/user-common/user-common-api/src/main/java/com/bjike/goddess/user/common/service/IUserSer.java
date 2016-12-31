package com.bjike.goddess.user.common.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.IService;
import com.bjike.goddess.user.common.dto.UserDto;
import com.bjike.goddess.user.common.entity.User;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IUserSer extends IService<User, UserDto> {


    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return
     * @throws SerException
     */
    default User findByUsername(String username) throws SerException {
        return null;
    }
    /**
     * 通过昵称查询用户
     * @param nickname 昵称
     * @return
     * @throws SerException
     */
    default User findByNickname(String nickname) throws SerException {
        return null;
    }

    /**
     * 通过手机号码查询用户
     * @param phone 手机号码
     * @return
     * @throws SerException
     */
    default User findByPhone(String phone) throws SerException {
        return null;
    }

    /**
     * 验证账号（邮箱/电话号码/用户名）
     * cause by findPassword
     *
     * @param accountNumber 账号
     * @return
     * @throws SerException
     */
    default User findByAccountNumber(String accountNumber) throws SerException {
        return null;
    }

    /**
     * 账号安全，验证用户邮箱
     * @param email
     * @return
     * @throws SerException
     */
    default Boolean verifyEmail(String email)throws  SerException{
        return false;
    }

}
