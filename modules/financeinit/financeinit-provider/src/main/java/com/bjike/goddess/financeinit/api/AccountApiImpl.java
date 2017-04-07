package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.AccountBO;
import com.bjike.goddess.financeinit.dto.AccountDTO;
import com.bjike.goddess.financeinit.service.AccountSer;
import com.bjike.goddess.financeinit.to.AccountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户来源业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-29 04:25 ]
 * @Description: [ 账户来源业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountApiImpl")
public class AccountApiImpl implements AccountAPI {

    @Autowired
    private AccountSer accountSer;

    @Override
    public List<AccountBO> listAccount(AccountDTO accountDTO) throws SerException {
        return accountSer.listAccount(accountDTO);
    }

    @Override
    public AccountBO addAccount(AccountTO accountTO) throws SerException {
        return accountSer.addAccount(accountTO);
    }

    @Override
    public AccountBO editAccount(AccountTO accountTO) throws SerException {
        return accountSer.editAccount(accountTO);
    }

    @Override
    public void deleteAccount(String id) throws SerException {
        accountSer.deleteAccount(id);
    }

    @Override
    public List<String> getSecondSubject(AccountDTO accountDTO) throws SerException {

        return accountSer.getSecondSubject(accountDTO );
    }

    @Override
    public List<String> getThirdSubject(AccountDTO accountDTO) throws SerException {
        return accountSer.getThirdSubject(accountDTO );
    }
}