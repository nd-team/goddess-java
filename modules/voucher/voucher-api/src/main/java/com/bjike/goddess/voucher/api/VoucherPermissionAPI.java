package com.bjike.goddess.voucher.api;

import com.bjike.goddess.voucher.bo.VoucherPermissionBO;
import com.bjike.goddess.voucher.dto.VoucherPermissionDTO;
import com.bjike.goddess.voucher.to.VoucherPermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
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
public interface VoucherPermissionAPI {


    /**
     * 客户权限列表总条数
     */
    default Long countPermission(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }
    /**
     * 明细账权限总条数
     *
     * @param cusPermissionDTO
     * @return
     * @throws SerException
     */
    default Long countAccountPermission(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 明细账权限列表
     *
     * @param cusPermissionDTO
     * @return
     * @throws SerException
     */
    default List<VoucherPermissionBO> listAccount(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 科目汇总权限总条数
     *
     * @param cusPermissionDTO
     * @return
     * @throws SerException
     */
    default Long countSubjectPermission(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 科目汇总权限列表
     *
     * @param cusPermissionDTO
     * @return
     * @throws SerException
     */
    default List<VoucherPermissionBO> listSubject(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 客户权限列表
     *
     * @param cusPermissionDTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default List<VoucherPermissionBO> list(VoucherPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }


    /**
     * 一个客户权限
     *
     * @param id 客户权限id
     * @return CusPermissionBO
     * @throws SerException
     */
    default VoucherPermissionBO getOneById(String id) throws SerException {
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
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default VoucherPermissionBO add(List<VoucherPermissionTO> cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑客户权限
     *
     * @param cusPermissionTO 客户权限数据
     * @return CusPermissionBO
     * @throws SerException
     */
    default VoucherPermissionBO edit(VoucherPermissionTO cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询可以某种操作的客户权限
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean getCusPermission(String idFlag) throws SerException {
        return null;
    }

    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean busCusPermission(String idFlag) throws SerException {
        return null;
    }
    /**
     * 根据idFlag查询只有商务模块的才可以进行添加编辑删除操作
     *
     * @param idFlag 客户权限idFlag
     * @throws SerException
     */
    default Boolean accCusPermission(String idFlag) throws SerException {
        return null;
    }
}