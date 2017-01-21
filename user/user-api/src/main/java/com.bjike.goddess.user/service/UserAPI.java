package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.sto.UserSTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [用户业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface UserAPI extends SerAPI<User, UserDTO> {

    default List<UserSTO> list() throws SerException {
         return null;
     }
    default UserSTO add(User entity) throws SerException {
        return null;
    }
    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return
     * @throws SerException
     */
    default UserSTO findByUsername(String username) throws SerException {
        return null;
    }
    /**
     * 通过昵称查询用户
     * @param nickname 昵称
     * @return
     * @throws SerException
     */
    default UserSTO findByNickname(String nickname) throws SerException {
        return null;
    }

    /**
     * 通过手机号码查询用户
     * @param phone 手机号码
     * @return
     * @throws SerException
     */
    default UserSTO findByPhone(String phone) throws SerException {
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



}
