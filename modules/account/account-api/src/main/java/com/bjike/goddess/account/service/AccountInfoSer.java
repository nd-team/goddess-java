package com.bjike.goddess.account.service;

import com.bjike.goddess.account.bo.AccountCollectBO;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.entity.AccountInfo;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

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
public interface AccountInfoSer extends Ser<AccountInfo, AccountInfoDTO> {
    /**
     * 明细账信息列表总条数
     */
    default Long countAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个明细账信息
     *
     * @return class AccountInfoBO
     */
    default AccountInfoBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 明细账信息
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
     * @return class accountInfoBO
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
     *
     * @param areas
     * @return AccountCollectBO
     */
    default List<AccountCollectBO> collectAccountInfo(String[] areas) throws SerException{
        return null;
    }

    /**
     * 地区
     *
     * @return class String
     */
    default List<String> getArea() throws SerException {
        return null;
    }

}