package com.bjike.goddess.foreigntax.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.foreigntax.bo.AccountInfoManagementBO;
import com.bjike.goddess.foreigntax.dto.AccountInfoManagementDTO;
import com.bjike.goddess.foreigntax.entity.AccountInfoManagement;
import com.bjike.goddess.foreigntax.excel.SonPermissionObject;
import com.bjike.goddess.foreigntax.service.AccountInfoManagementSer;
import com.bjike.goddess.foreigntax.to.AccountInfoManagementTO;
import com.bjike.goddess.foreigntax.to.GuidePermissionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 外账资料管理业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-19 01:52 ]
 * @Description: [ 外账资料管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountInfoManagementApiImpl")
public class AccountInfoManagementApiImpl implements AccountInfoManagementAPI {
    @Autowired
    private AccountInfoManagementSer accountInfoManagementSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return accountInfoManagementSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accountInfoManagementSer.guidePermission( guidePermissionTO );
    }
    @Override
    public Long countAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        return accountInfoManagementSer.countAccountInfoManagement(accountInfoManagementDTO);
    }
    @Override
    public AccountInfoManagementBO getOne(String id) throws SerException {
        return accountInfoManagementSer.getOne(id);
    }

    @Override
    public List<AccountInfoManagementBO> findListAccountInfoManagement(AccountInfoManagementDTO accountInfoManagementDTO) throws SerException {
        return accountInfoManagementSer.findListAccountInfoManagement(accountInfoManagementDTO);
    }

    @Override
    public AccountInfoManagementBO insertAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        return accountInfoManagementSer.insertAccountInfoManagement(accountInfoManagementTO);
    }

    @Override
    public AccountInfoManagementBO editAccountInfoManagement(AccountInfoManagementTO accountInfoManagementTO) throws SerException {
        return accountInfoManagementSer.editAccountInfoManagement(accountInfoManagementTO);
    }

    @Override
    public void removeAccountInfoManagement(String id) throws SerException {
        accountInfoManagementSer.removeAccountInfoManagement(id);
    }

}