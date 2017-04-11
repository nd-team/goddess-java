package com.bjike.goddess.account.api;

import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 明细账信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountInfoAPI {
    /**
     * 获取明细账信息
     *
     * @param accountInfoDTO 明细账信息dto
     * @return class accountInfoBO
     * @throws SerException
     */
    default List<AccountInfoBO> findListAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @throws SerException
     */
    default AccountInfoBO insertAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑明细账信息
     *
     * @param accountInfoTO 明细账信息数据to
     * @return class accountInfoBO
     * @throws SerException
     */
    default AccountInfoBO editAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除明细账信息
     *
     * @param id
     * @throws SerException
     */
    default void removeAccountInfo(String id) throws SerException {

    }
    /**
     * 汇总
     * @return 汇总
     */
    List<AccountInfoBO> collectAccountInfo(String area,String projectName,String projectGroup) throws SerException;



}