package com.bjike.goddess.businessprojectmanage.service;

import com.bjike.goddess.businessprojectmanage.bo.CusPermissionBO;
import com.bjike.goddess.businessprojectmanage.excel.SonPermissionObject;
import com.bjike.goddess.businessprojectmanage.to.CusPermissionTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.businessprojectmanage.entity.CusPermission;
import com.bjike.goddess.businessprojectmanage.dto.CusPermissionDTO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.user.bo.UserBO;

import java.util.List;

/**
 * 客户权限设置业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-12 05:56 ]
 * @Description: [ 客户权限设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CusPermissionSer extends Ser<CusPermission, CusPermissionDTO> {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }


    /**
     * 客户权限列表总条数
     */
    default Long countPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 客户权限列表
     *
     * @param cusPermissionDTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<CusPermissionBO> list(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 商务合同权限列表总条数
     */
    default Long busCountPermission(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 商务合同权限列表
     *
     * @param cusPermissionDTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<CusPermissionBO> busList(CusPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 一个客户权限
     *
     * @param id 客户权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 客户权限操作者
     *
     * @param id 客户权限id
     * @return OpinionBO 返回数据
     * @throws SerException
     */
    default List<OpinionBO> listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加客户权限
     *
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO add(List<CusPermissionTO> cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑客户权限
     *
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default CusPermissionBO edit(CusPermissionTO cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询可以某种操作的客户权限
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean getCusPermission(String idFlag, UserBO user) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean busCusPermission(String idFlag, UserBO user) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean modCusPermission(String idFlag, UserBO user) throws SerException {
        return null;
    }
}