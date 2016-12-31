package com.bjike.goddess.user.common.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.IService;
import com.bjike.goddess.user.common.dto.RoleDto;
import com.bjike.goddess.user.common.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [角色业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IRoleSer extends IService<Role, RoleDto> {

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
