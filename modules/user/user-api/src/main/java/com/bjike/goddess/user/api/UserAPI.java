package com.bjike.goddess.user.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.dto.UserDTO;
import com.bjike.goddess.user.entity.User;
import com.bjike.goddess.user.to.UserTO;
import org.mengyun.tcctransaction.api.TransactionContext;

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
     * 获取公钥
     *
     * @return
     * @throws SerException
     */
    default String publicKey() throws SerException {
        return null;
    }

    /**
     * 获取私钥
     *
     * @return
     * @throws SerException
     */
    default String privateKey() throws SerException {
        return null;
    }

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
     * 获取当前用户系统号
     *
     * @param userToken 用户令牌
     * @return
     * @throws SerException
     */
    default String currentSysNO(String userToken) throws SerException {
        return null;
    }

    /**
     * 获取当前用户系统号
     *
     * @return
     * @throws SerException
     */
    default String currentSysNO() throws SerException {
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
    default UserBO findOne(UserDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加用户
     *
     * @param userTO
     * @return
     * @throws SerException
     */
    default UserBO add(TransactionContext context,UserTO userTO) throws SerException {
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
     * 修改密码
     * tanghaixiang
     * @param userTO
     * @return
     * @throws SerException
     */
    default void updatePassword(UserTO userTO) throws SerException {
    }

    /**
     * 忘记密码修改密码
     * chenyang
     * @param userTO
     * @return
     * @throws SerException
     */
    default void updatePasswords(UserTO userTO) throws SerException {
    }

    /**
     * 修改手机号码
     * chenyang
     * @param userTO
     * @return
     * @throws SerException
     */
    default void updatePhone(UserTO userTO) throws SerException {
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
     *
     * @param groups
     * @return
     * @throws SerException
     */
    default List<UserBO> findByGroup(String... groups) throws SerException {
        return null;
    }

    /**
     * 查询所有用户
     *
     * @return
     * @throws SerException
     */
    default List<UserBO> findAllUser() throws SerException {
        return null;
    }

    /**
     * 条件分页查询用户
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<UserBO> findUserByPage(UserDTO dto) throws SerException {
        return null;

    }

    /**
     * 员工入职注册修改用户
     *
     * @param userTO
     * @return UserBO
     * @throws SerException
     */
    default UserBO updateUser(UserTO userTO) throws SerException {
        return null;
    }
    /**
     * 员工入职注册删除用户
     *
     * @param id
     * @return UserBO
     * @throws SerException
     */
    default void deleteUser(String id) throws SerException {
    }

    /**
     * 员工入职获取最大员工编号
     *
     * @return UserBO
     * @throws SerException
     */
    default String maxUserEmpNumber( ) throws SerException {
        return null;
    }

    /**
     * 移动端获取下一个员工编号(个人注册的下一个编号)
     * lijuntao
     *
     * @param empNum 企业人(邀请人编号)
     * @throws SerException
     */
    default String nextEmpNumber(String empNum) throws SerException {
        return null;
    }

    /**
     * chenjunhao
     * 通过用户id查找用户名
     *
     * @param id
     * @return
     * @throws SerException
     */
    String findNameById(String id) throws SerException;

    /**
     * 获取部门人员
     *@param department id或者部门名
     * @return UserBO
     * @throws SerException
     */
    default List<UserBO> findByDept(String ... department  ) throws SerException {
        return null;
    }
}
