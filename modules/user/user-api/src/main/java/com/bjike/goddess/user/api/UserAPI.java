package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.to.UserTO;

import java.util.List;

/**
 * 对外提供用户业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-03-11 13:56]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserAPI {
    /**
     * 获取当前用户
     *
     * @return
     * @throws SerException
     */
    default UserBO currentUser() throws SerException {
        return null;
    }

    /**
     * 获取当前用户
     *
     * @param userToken 用户令牌
     * @return
     * @throws SerException
     */
    default UserBO currentUser(String userToken) throws SerException {
        return null;
    }

    /**
     * 条件查询用户
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<UserBO> findByCis(UserDTO dto) throws SerException {
        return null;

    }

    /**
     * 获取一个用户对象
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<UserBO> findOne(UserDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加用户
     *
     * @param userTO
     * @return
     * @throws SerException
     */
    default UserBO add(UserTO userTO) throws SerException {
        return null;
    }

    /**
     * 更新用户
     *
     * @param userTO
     * @return
     * @throws SerException
     */
    default void update(UserTO userTO) throws SerException {
    }


    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return
     * @throws SerException
     */
    default UserBO findByUsername(String username) throws SerException {
        return null;
    }

    /**
     * 通过昵称查询用户
     *
     * @param nickname 昵称
     * @return
     * @throws SerException
     */
    default UserBO findByNickname(String nickname) throws SerException {
        return null;
    }

    /**
     * 通过手机号码查询用户
     *
     * @param phone 手机号码
     * @return
     * @throws SerException
     */
    default UserBO findByPhone(String phone) throws SerException {
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
    default UserBO findByAccountNumber(String accountNumber) throws SerException {
        return null;
    }
    /**
     * 通过组查寻用户
     * @param groups
     * @return
     * @throws SerException
     */
    default List<UserBO> findByGroup(String ...  groups) throws SerException{
        return null;
    }
}
