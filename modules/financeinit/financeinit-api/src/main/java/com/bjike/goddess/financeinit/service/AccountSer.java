package com.bjike.goddess.financeinit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.entity.Account;
import com.bjike.goddess.financeinit.dto.AccountDTO;
import com.bjike.goddess.financeinit.to.AccountTO;

import java.util.List;

/**
 * 账户来源业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountSer extends Ser<Account, AccountDTO> {

    /**
     * 账户来源列表
     * @return class AccountBO
     */
    default List<AccountBO> listAccount(AccountDTO accountDTO) throws SerException {return null;}
    /**
     *  添加
     * @param accountTO 账户来源信息
     * @return class AccountBO
     */
    default AccountBO addAccount(AccountTO accountTO) throws SerException { return null;}

    /**
     *  编辑
     * @param accountTO 账户来源信息
     * @return class AccountBO
     */
    default AccountBO editAccount(AccountTO accountTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteAccount(String id ) throws SerException {return;};

    /**
     * 通过一级查二级级别列表
     * @return class String
     */
    default List<String> getSecondSubject(AccountDTO accountDTO) throws SerException {return null;}



    /**
     * 通过一级和二级查三级级别列表
     * @return class String
     */
    default List<String> getThirdSubject(AccountDTO accountDTO) throws SerException {return null;}





}