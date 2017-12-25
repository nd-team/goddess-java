package com.bjike.goddess.receivable.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.receivable.bo.CusPermissionBO;
import com.bjike.goddess.receivable.dto.CusPermissionDTO;
import com.bjike.goddess.receivable.entity.CusPermission;
import com.bjike.goddess.receivable.to.CusPermissionTO;
import com.bjike.goddess.organize.bo.OpinionBO;

import java.util.List;

/**
 * 回款管理权限配置业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:43 ]
 * @Description: [ 回款管理权限配置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CusPermissionSer extends Ser<CusPermission, CusPermissionDTO> {

    /**
     * 回款管理权限列表总条数
     */
    default Long countPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 回款管理权限列表
     *
     * @param cusPermissionDTO 回款管理权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<CusPermissionBO> list(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 一个回款管理权限
     *
     * @param id 回款管理权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 回款管理权限操作者
     *
     * @param id 回款管理权限id
     * @return OpinionBO 返回数据
     * @throws SerException
     */
    default List<OpinionBO> listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加回款管理权限
     *
     * @param cusPermissionTO 回款管理权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO add(List<CusPermissionTO> cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑回款管理权限
     *
     * @param cusPermissionTO 回款管理权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO edit(CusPermissionTO cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询可以某种操作的回款管理权限
     *
     * @param idFlag 回款管理权限idFlag
     * @throws SerException
     */
    default Boolean getCusPermission(String idFlag) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 回款管理权限idFlag
     * @throws SerException
     */
    default Boolean busCusPermission(String idFlag) throws SerException {
        return null;
    }


}