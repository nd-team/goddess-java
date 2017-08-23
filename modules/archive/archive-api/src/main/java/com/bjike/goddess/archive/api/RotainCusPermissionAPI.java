package com.bjike.goddess.archive.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.archive.bo.RotainCusPermissionBO;
import com.bjike.goddess.archive.dto.RotainCusPermissionDTO;
import com.bjike.goddess.archive.to.RotainCusPermissionTO;
import com.bjike.goddess.organize.bo.OpinionBO;

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
public interface RotainCusPermissionAPI {


    /**
     * 客户权限列表总条数
     */
    default Long countPermission(RotainCusPermissionDTO rotainCusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 客户权限列表
     *
     * @param rotainCusPermissionDTO 客户权限数据
     * @return RotainCusPermissionBO
     * @throws SerException
     */
    default List<RotainCusPermissionBO> list(RotainCusPermissionDTO rotainCusPermissionDTO) throws SerException {
        return null;
    }


    /**
     * 一个客户权限
     *
     * @param id 客户权限id
     * @return RotainCusPermissionBO
     * @throws SerException
     */
    default RotainCusPermissionBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 客户权限操作者
     *
     * @throws SerException
     */
    default List<OpinionBO>  listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加客户权限
     *
     * @param rotainCusPermissionTO 客户权限数据
     * @return RotainCusPermissionBO
     * @throws SerException
     */
    default RotainCusPermissionBO add(List<RotainCusPermissionTO> rotainCusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑客户权限
     *
     * @param rotainCusPermissionTO 客户权限数据
     * @return RotainCusPermissionBO
     * @throws SerException
     */
    default RotainCusPermissionBO edit(RotainCusPermissionTO rotainCusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询可以某种操作的客户权限
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean getRotainCusPermission(String idFlag) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean busRotainCusPermission(String idFlag) throws SerException {
        return null;
    }
}