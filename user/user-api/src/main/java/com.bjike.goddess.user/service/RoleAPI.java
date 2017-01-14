package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.RoleDTO;
import com.bjike.goddess.user.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [角色业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RoleAPI extends SerAPI<Role, RoleDTO> {

    /**
     * 通过父节点查找其儿子节点(一级)
     *
     * @param parentId
     * @return
     * @throws SerException
     */
    default List<Role> findChildByParentId(String parentId) throws SerException {
        return null;
    }


    /**
     * 通过当前角色id查询其下的所有子孙节点
     *
     * @param roleId
     * @return
     * @throws SerException
     */
    default Set<Role> findChildByRoleId(String roleId) throws SerException {
        return null;
    }

    /**
     * 通过用户id查询其所有角色
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default Set<Role> findRoleByUserId(String userId) throws SerException {
        return null;
    }


}
