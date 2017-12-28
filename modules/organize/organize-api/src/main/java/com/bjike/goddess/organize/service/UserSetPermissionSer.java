package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.UserSetPermissionBO;
import com.bjike.goddess.organize.dto.UserSetPermissionDTO;
import com.bjike.goddess.organize.entity.UserSetPermission;
import com.bjike.goddess.organize.to.UserSetPermissionTO;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;

/**
 * 客户权限配置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 客户权限配置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface UserSetPermissionSer extends Ser<UserSetPermission, UserSetPermissionDTO> {

    /**
     * admin权限
     */
    default UserBO adminPermission( ) throws SerException {
        return null;
    }
    /**
     * 客户权限列表总条数
     */
    default Long countPermission(UserSetPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 客户权限列表
     * @param cusPermissionDTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<UserSetPermissionBO> list(UserSetPermissionDTO cusPermissionDTO) throws SerException{return null;}

    /**
     * 一个客户权限
     * @param id 客户权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default UserSetPermissionBO getOneById(String id) throws SerException{return null;}


    /**
     * 客户权限操作者
     *
     * @param id 客户权限id
     * @return OpinionBO 返回数据
     * @throws SerException
     */
    default List<OpinionBO>  listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加客户权限
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default UserSetPermissionBO add(List<UserSetPermissionTO> cusPermissionTO) throws SerException{return null;}

    /**
     * 编辑客户权限
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default UserSetPermissionBO edit(UserSetPermissionTO cusPermissionTO) throws SerException{return null;}

    /**
     * 根据idFlag查询可以某种操作的客户权限
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean getCusPermission(String idFlag) throws SerException{return null;}

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean busCusPermission(String idFlag) throws SerException {
        return null;
    }

    /**
     * 根据用户id查询是否有操作设置的权限
     *
     * @throws SerException
     */
    default Boolean checkSetPermission( ) throws SerException {
        return null;
    }

    /**
     * 查询用户是否有操作所有模块设置的权限
     *
     * @throws SerException
     */
    default Boolean checkSetPermission22( ) throws SerException {
        return null;
    }
}