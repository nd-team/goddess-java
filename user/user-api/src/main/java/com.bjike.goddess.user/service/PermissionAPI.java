package com.bjike.goddess.user.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.PermissionDTO;
import com.bjike.goddess.user.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * 权限认证业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface PermissionAPI extends SerAPI<Permission, PermissionDTO> {

    /**
     * 通过父节点查找其儿子节点(一级)
     *
     * @param parentId
     * @return
     * @throws SerException
     */
    default List<Permission> findChildByParentId(String parentId) throws SerException {
        return null;
    }

    /**
     * 通过用户id查询其所拥有的所有权限资源
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default Set<Permission> findAllByUserId(String userId) throws SerException {
        return null;
    }

    /**
     * 通过角色id查询其所拥有的所有权限资源
     *
     * @param roleId
     * @return
     * @throws SerException
     */
    default Set<Permission> findByRoleId(String roleId) throws SerException {
        return null;
    }


}
