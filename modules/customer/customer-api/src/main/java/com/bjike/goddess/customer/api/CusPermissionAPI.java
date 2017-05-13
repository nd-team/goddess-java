package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CusPermissionBO;
import com.bjike.goddess.customer.dto.CusPermissionDTO;
import com.bjike.goddess.customer.to.CusPermissionTO;

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
public interface CusPermissionAPI {

    /**
     * 客户权限列表
     * @param cusPermissionDTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<CusPermissionBO> list(CusPermissionDTO cusPermissionDTO) throws SerException{return null;}


    /**
     * 一个客户权限
     * @param id 客户权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO getOneById(String id) throws SerException{return null;}

    /**
     * 添加客户权限
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO add(List<CusPermissionTO> cusPermissionTO) throws SerException{return null;}

    /**
     * 编辑客户权限
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO edit(CusPermissionTO cusPermissionTO) throws SerException{return null;}

    /**
     * 根据idFlag查询可以某种操作的客户权限
     * @param idFlag 客户权限idFlag
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO getCusPermission(String idFlag) throws SerException{return null;}
}