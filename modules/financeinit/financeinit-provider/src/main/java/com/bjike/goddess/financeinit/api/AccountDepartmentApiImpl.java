package com.bjike.goddess.financeinit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.AccountDepartmentBO;
import com.bjike.goddess.financeinit.dto.AccountDepartmentDTO;
import com.bjike.goddess.financeinit.entity.AccountDepartment;
import com.bjike.goddess.financeinit.service.AccountDepartmentSer;
import com.bjike.goddess.financeinit.to.AccountDepartmentTO;
import com.bjike.goddess.financeinit.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 核算部门业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-10 02:43 ]
 * @Description: [ 核算部门业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountDepartmentApiImpl")
public class AccountDepartmentApiImpl implements AccountDepartmentAPI {
    @Autowired
    private AccountDepartmentSer accountDepartmentSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return accountDepartmentSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accountDepartmentSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        return accountDepartmentSer.countDepart(accountDepartmentDTO);
    }

    @Override
    public AccountDepartmentBO getOneById(String id) throws SerException {
        return accountDepartmentSer.getOneById(id);
    }

    @Override
    public List<AccountDepartmentBO> listDepart(AccountDepartmentDTO accountDepartmentDTO) throws SerException {
        return accountDepartmentSer.listDepart(accountDepartmentDTO);
    }

    @Override
    public AccountDepartmentBO addDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
        return accountDepartmentSer.addDepart(accountDepartmentTO);
    }

    @Override
    public AccountDepartmentBO editDepart(AccountDepartmentTO accountDepartmentTO) throws SerException {
        return accountDepartmentSer.editDepart(accountDepartmentTO);
    }

    @Override
    public void deleteDepart(String id) throws SerException {
        accountDepartmentSer.deleteDepart(id);
    }
}