package com.bjike.goddess.datastore.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.datastore.bo.AccountPwdSpecificationBO;
import com.bjike.goddess.datastore.dto.AccountPwdSpecificationDTO;
import com.bjike.goddess.datastore.to.AccountPwdSpecificationTO;

import java.util.List;

/**
 * 数据存储账号密码规范业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储账号密码规范业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountPwdSpecificationAPI {
    /**
     * 数据存储-账号密码规范列表总条数
     */
    default Long countAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 数据存储-账号密码规范
     *
     * @param accountPwdSpecificationDTO 数据存储-账号密码规范dto
     * @return class AccountPwdSpecificationBO
     * @throws SerException
     */
    default List<AccountPwdSpecificationBO> findListAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        return null;
    }

    /**
     * 添加数据存储-账号密码规范
     *
     * @param accountPwdSpecificationTO 数据存储-账号密码规范数据to
     * @return class AccountPwdSpecificationBO
     * @throws SerException
     */
    default AccountPwdSpecificationBO insertAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 编辑数据存储-账号密码规范
     *
     * @param accountPwdSpecificationTO 数据存储-账号密码规范数据to
     * @return class AccountPwdSpecificationBO
     * @throws SerException
     */
    default AccountPwdSpecificationBO editAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除数据存储-账号密码规范
     *
     * @param id
     * @throws SerException
     */
    default void removeAccountPwdSpecification(String id) throws SerException {

    }

}