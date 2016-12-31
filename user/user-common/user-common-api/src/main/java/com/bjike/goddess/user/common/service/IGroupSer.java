package com.bjike.goddess.user.common.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.IService;
import com.bjike.goddess.user.common.dto.GroupDto;
import com.bjike.goddess.user.common.entity.Group;
import com.bjike.goddess.user.common.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-28 15:47]
 * @Description: [部门业务接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface IGroupSer extends IService<Group, GroupDto> {

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
