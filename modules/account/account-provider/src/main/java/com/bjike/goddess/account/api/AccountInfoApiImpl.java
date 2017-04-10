package com.bjike.goddess.account.api;

import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.service.AccountInfoSer;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 明细账信息业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-06 11:25 ]
 * @Description: [ 明细账信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountInfoApiImpl")
public class AccountInfoApiImpl implements AccountInfoAPI {
    @Autowired
    private AccountInfoSer accountInfoSer;
    @Override
    public List<AccountInfoBO> findListAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        return accountInfoSer.findListAccountInfo(accountInfoDTO);
    }

    @Override
    public AccountInfoBO insertAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        accountInfoTO.setDate(DateUtil.dateToString(LocalDate.now()));
        return accountInfoSer.insertAccountInfo(accountInfoTO);
    }

    @Override
    public AccountInfoBO editAccountInfo(AccountInfoTO accountInfoTO) throws SerException {
        return accountInfoSer.editAccountInfo(accountInfoTO);
    }

    @Override
    public void removeAccountInfo(String id) throws SerException {
        accountInfoSer.remove(id);
    }
    @Override
    public List<AccountInfoBO> collectAccountInfo(String area,String projectName,String projectGroup) throws SerException{
        return accountInfoSer.collectAccountInfo(area, projectName, projectGroup);
    }


}