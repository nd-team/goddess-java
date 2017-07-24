package com.bjike.goddess.supplier.service;

import com.bjike.goddess.supplier.bo.SupCusPermissionBO;
import com.bjike.goddess.supplier.dto.SupCusPermissionDTO;
import com.bjike.goddess.supplier.entity.SupCusPermission;
import com.bjike.goddess.supplier.to.SupCusPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
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
public interface SupCusPermissionSer extends Ser<SupCusPermission, SupCusPermissionDTO> {

    /**
     * 客户权限列表总条数
     */
    default Long countPermission(SupCusPermissionDTO supCusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 客户权限列表
     * @param supCusPermissionDTO 客户权限数据
     * @return SupCusPermissionBO
     * @throws SerException
     */
    default List<SupCusPermissionBO> list(SupCusPermissionDTO supCusPermissionDTO) throws SerException{return null;}

    /**
     * 一个客户权限
     * @param id 客户权限id
     * @return SupCusPermissionBO
     * @throws SerException
     */
    default SupCusPermissionBO getOneById(String id) throws SerException{return null;}


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
     * @param supCusPermissionTO 客户权限数据
     * @return SupCusPermissionBO
     * @throws SerException
     */
    default SupCusPermissionBO add(List<SupCusPermissionTO> supCusPermissionTO) throws SerException{return null;}

    /**
     * 编辑客户权限
     * @param supCusPermissionTO 客户权限数据
     * @return SupCusPermissionBO
     * @throws SerException
     */
    default SupCusPermissionBO edit(SupCusPermissionTO supCusPermissionTO) throws SerException{return null;}

    /**
     * 根据idFlag查询可以某种操作的客户权限
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean getSupCusPermission(String idFlag) throws SerException{return null;}

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean busSupCusPermission(String idFlag) throws SerException {
        return null;
    }


}