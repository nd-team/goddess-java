package com.bjike.goddess.user.service.rbac;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.SerAPI;
import com.bjike.goddess.user.dto.rbac.PermissionDTO;
import com.bjike.goddess.user.entity.rbac.Permission;
import com.bjike.goddess.user.bo.rbac.PermissionBO;
import com.bjike.goddess.user.bo.rbac.PermissionTreeBO;

import java.util.List;

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
     * 通过用户id查询其所拥有的所有权限资源
     *
     * @param userId
     * @return
     * @throws SerException
     */
    default List<PermissionBO> findByUserId(String userId) throws SerException {
        return null;
    }

    /**
     * 通过角色id查询其所拥有的所有权限资源
     *
     * @param roleId
     * @return
     * @throws SerException
     */
    default List<PermissionBO> findByRoleId(String roleId) throws SerException {
        return null;
    }

    /**
     * 逐层查询,逐层加载
     * @param id 组id
     * @return
     */
    default List<PermissionTreeBO> treeData(String id)throws SerException{
        return null;
    }

    default PermissionBO saveByBO(PermissionBO bo) throws SerException {
        return null;
    }
}
