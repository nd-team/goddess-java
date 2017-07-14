package com.bjike.goddess.datastore.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.datastore.bo.AccountPwdSpecificationBO;
import com.bjike.goddess.datastore.dto.AccountPwdSpecificationDTO;
import com.bjike.goddess.datastore.entity.AccountPwdSpecification;
import com.bjike.goddess.datastore.service.AccountPwdSpecificationSer;
import com.bjike.goddess.datastore.to.AccountPwdSpecificationTO;
import com.bjike.goddess.datastore.to.GuidePermissionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 数据存储账号密码规范业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-21 06:14 ]
 * @Description: [ 数据存储账号密码规范业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountPwdSpecificationApiImpl")
public class AccountPwdSpecificationApiImpl implements AccountPwdSpecificationAPI {
    @Autowired
    private AccountPwdSpecificationSer accountPwdSpecificationSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return accountPwdSpecificationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accountPwdSpecificationSer.guidePermission( guidePermissionTO );
    }

    @Override
    public Long countAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        return accountPwdSpecificationSer.countAccountPwdSpecification(accountPwdSpecificationDTO);
    }
    @Override
    public AccountPwdSpecificationBO getOne(String id) throws SerException {
        return accountPwdSpecificationSer.getOne(id);
    }

    @Override
    public List<AccountPwdSpecificationBO> findListAccountPwdSpecification(AccountPwdSpecificationDTO accountPwdSpecificationDTO) throws SerException {
        return accountPwdSpecificationSer.findListAccountPwdSpecification(accountPwdSpecificationDTO);
    }

    @Override
    public AccountPwdSpecificationBO insertAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        return accountPwdSpecificationSer.insertAccountPwdSpecification(accountPwdSpecificationTO);
    }

    @Override
    public AccountPwdSpecificationBO editAccountPwdSpecification(AccountPwdSpecificationTO accountPwdSpecificationTO) throws SerException {
        return accountPwdSpecificationSer.editAccountPwdSpecification(accountPwdSpecificationTO);
    }

    @Override
    public void removeAccountPwdSpecification(String id) throws SerException {
        accountPwdSpecificationSer.removeAccountPwdSpecification(id);
    }
}