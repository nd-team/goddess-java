package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.supplier.bo.SupPermissionBO;
import com.bjike.goddess.supplier.dto.SupPermissionDTO;
import com.bjike.goddess.supplier.entity.SupPermission;
import com.bjike.goddess.supplier.to.SupPermissionTO;

import java.util.List;

/**
 * 供应商权限配置业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-27 10:43 ]
 * @Description: [ 供应商权限配置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupPermissionSer extends Ser<SupPermission, SupPermissionDTO> {

    /**
     * 供应商权限列表总条数
     */
    default Long countPermission(SupPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 供应商权限列表
     *
     * @param cusPermissionDTO 供应商权限数据
     * @return SupPermissionBO
     * @throws SerException
     */
    default List<SupPermissionBO> list(SupPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 一个供应商权限
     *
     * @param id 供应商权限id
     * @return SupPermissionBO
     * @throws SerException
     */
    default SupPermissionBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 供应商权限操作者
     *
     * @param id 供应商权限id
     * @return OpinionBO 返回数据
     * @throws SerException
     */
    default List<OpinionBO> listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加供应商权限
     *
     * @param cusPermissionTO 供应商权限数据
     * @return SupPermissionBO
     * @throws SerException
     */
    default SupPermissionBO add(List<SupPermissionTO> cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑供应商权限
     *
     * @param cusPermissionTO 供应商权限数据
     * @return SupPermissionBO
     * @throws SerException
     */
    default SupPermissionBO edit(SupPermissionTO cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 检查权限
     *
     * @param idFlag 辅助id
     * @return
     * @throws SerException
     */
    default Boolean getSupPermission(String idFlag) throws SerException {
        return null;
    }

}