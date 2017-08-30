package com.bjike.goddess.account.api;

import com.bjike.goddess.account.bo.AccountCollectBO;
import com.bjike.goddess.account.bo.AccountInfoBO;
import com.bjike.goddess.account.dto.AccountInfoDTO;
import com.bjike.goddess.account.excel.SonPermissionObject;
import com.bjike.goddess.account.service.AccountInfoSer;
import com.bjike.goddess.account.to.AccountInfoTO;
import com.bjike.goddess.account.to.GuidePermissionTO;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return accountInfoSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accountInfoSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countAccountInfo(AccountInfoDTO accountInfoDTO) throws SerException {
        return accountInfoSer.countAccountInfo(accountInfoDTO);
    }

    @Override
    public AccountInfoBO getOne(String id) throws SerException {
        return accountInfoSer.getOne(id);
    }
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
        accountInfoSer.removeAccountInfo(id);
    }
    @Override
    public List<AccountCollectBO> subCollect(AccountInfoDTO dto) throws SerException {
        return accountInfoSer.subCollect(dto);
    }
    @Override
    public List<AccountCollectBO> areaCollect(AccountInfoDTO dto) throws SerException {
        return accountInfoSer.areaCollect(dto);
    }
    @Override
    public List<AccountCollectBO> groupCollect(AccountInfoDTO dto) throws SerException {
        return accountInfoSer.groupCollect(dto);
    }

    @Override
    public List<AccountCollectBO> nameCollect(AccountInfoDTO dto) throws SerException {
        return accountInfoSer.nameCollect(dto);
    }

    @Override
    public List<String> getArea() throws SerException {
        return accountInfoSer.getArea();
    }
    @Override
    public List<String> getProjectName() throws SerException {
        return accountInfoSer.getProjectName();
    }
    @Override
    public List<String> getProjectGroup() throws SerException {
        return accountInfoSer.getProjectGroup();
    }
    @Override
    public List<String> listFirstSubject() throws SerException {
        return accountInfoSer.listFirstSubject();
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        return accountInfoSer.listSubByFirst(firstSub);
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return accountInfoSer.listTubByFirst(firstSub,secondSub);
    }


}