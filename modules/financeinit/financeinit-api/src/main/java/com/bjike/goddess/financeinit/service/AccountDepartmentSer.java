package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.AccountDepartmentBO;
import com.bjike.goddess.financeinit.dto.AccountDepartmentDTO;
import com.bjike.goddess.financeinit.entity.AccountDepartment;
import com.bjike.goddess.financeinit.to.AccountDepartmentTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;

import java.util.List;

/**
 * 核算部门业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:43 ]
 * @Description: [ 核算部门业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountDepartmentSer extends Ser<AccountDepartment, AccountDepartmentDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 核算部门列表总条数
     */
    default Long countDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取核算部门
     *
     * @return class AccountDepartmentBO
     */
    default AccountDepartmentBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 核算部门列表
     *
     * @return class AccountDepartmentBO
     */
    default List<AccountDepartmentBO> listDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param accountDepartmentTO 核算部门
     * @return class AccountDepartmentBO
     */
    default AccountDepartmentBO addDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param accountDepartmentTO 核算部门
     * @return class AccountDepartmentBO
     */
    default AccountDepartmentBO editDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 核算部门id
     */
    default void deleteDepart(String id) throws SerException {
        return;
    }
}