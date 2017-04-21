package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.foreigntax.bo.AccountInfoManagementBO;
import com.bjike.goddess.foreigntax.dto.AccountInfoManagementDTO;
import com.bjike.goddess.foreigntax.to.AccountInfoManagementTO;

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
     * 外账资料管理列表总条数
     */
    default Long countAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        return null;
    }

    /**
     * 外账资料管理
     *
     * @param accountInfoManagementDTO 外账资料管理dto
     * @return class AccountInfoManagementBO
     * @throws SerException
     */
    default List<AccountInfoManagementBO> findListAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        return null;
    }

    /**
     * 添加外账资料管理
     *
     * @param accountInfoManagementTO 外账资料管理数据to
     * @return class AccountInfoManagementBO
     * @throws SerException
     */
    default AccountInfoManagementBO insertAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        return null;
    }

    /**
     * 编辑外账资料管理
     *
     * @param accountInfoManagementTO 外账资料管理数据to
     * @return class AccountInfoManagementBO
     * @throws SerException
     */
    default AccountInfoManagementBO editAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除外账资料管理
     *
     * @param id
     * @throws SerException
     */
    default void removeAccountInfoManagement(String id) throws SerException {

    }

    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }

    /**
     * 下载
     */
    default void download() throws SerException {
        return;

    }

}