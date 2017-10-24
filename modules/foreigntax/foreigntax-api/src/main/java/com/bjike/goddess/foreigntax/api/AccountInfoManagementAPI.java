package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.AccountInfoManagementBO;
import com.bjike.goddess.foreigntax.dto.AccountInfoManagementDTO;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.to.AccountInfoManagementTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;

import java.util.List;

/**
 * 外账资料管理业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:52 ]
 * @Description: [ 外账资料管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountInfoManagementAPI {
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
     * 外账资料管理列表总条数
     */
    default Long count(AccountInfoManagementDTO dto) throws SerException {
        return null;
    }
    /**
     * 一个外账资料管理
     *
     * @return class AccountInfoManagementBO
     */
    default AccountInfoManagementBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 外账资料管理
     *
     * @param dto 外账资料管理dto
     * @return class AccountInfoManagementBO
     * @throws SerException
     */
    default List<AccountInfoManagementBO> list(AccountInfoManagementDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加外账资料管理
     *
     * @param to 外账资料管理数据to
     * @return class AccountInfoManagementBO
     * @throws SerException
     */
    default AccountInfoManagementBO insert(AccountInfoManagementTO to) throws SerException {
        return null;
    }

    /**
     * 编辑外账资料管理
     *
     * @param to 外账资料管理数据to
     * @return class AccountInfoManagementBO
     * @throws SerException
     */
    default AccountInfoManagementBO edit(AccountInfoManagementTO to) throws SerException {
        return null;
    }

    /**
     * 根据id删除外账资料管理
     *
     * @param id
     * @throws SerException
     */
    default void remove(String id) throws SerException {

    }

}