package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.lendreimbursement.bo.LendPermissionBO;
import com.bjike.goddess.lendreimbursement.dto.LendPermissionDTO;
import com.bjike.goddess.lendreimbursement.entity.LendPermission;
import com.bjike.goddess.lendreimbursement.to.LendPermissionTO;
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
public interface LendPermissionSer extends Ser<LendPermission, LendPermissionDTO> {

    /**
     * 客户权限列表总条数
     */
    default Long countPermission(LendPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 客户权限列表
     * @param cusPermissionDTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<LendPermissionBO> list(LendPermissionDTO cusPermissionDTO) throws SerException{return null;}

    /**
     * 一个客户权限
     * @param id 客户权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default LendPermissionBO getOneById(String id) throws SerException{return null;}


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
    default LendPermissionBO add(List<LendPermissionTO> cusPermissionTO) throws SerException{return null;}

    /**
     * 编辑客户权限
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default LendPermissionBO edit(LendPermissionTO cusPermissionTO) throws SerException{return null;}

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


}