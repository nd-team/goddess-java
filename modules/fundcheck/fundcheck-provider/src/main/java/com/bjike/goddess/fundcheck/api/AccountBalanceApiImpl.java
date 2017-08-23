package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.AccountBalanceBO;
import com.bjike.goddess.fundcheck.entity.AccountBalance;
import com.bjike.goddess.fundcheck.service.AccountBalanceSer;
import com.bjike.goddess.fundcheck.to.AccountBalanceCollectTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账上余额业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:08 ]
 * @Description: [ 账上余额业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountBalanceApiImpl")
public class AccountBalanceApiImpl implements AccountBalanceAPI {
    @Autowired
    private AccountBalanceSer accountBalanceSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return accountBalanceSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accountBalanceSer.guidePermission( guidePermissionTO );
    }
    @Override
    public List<AccountBalanceBO> collect(AccountBalanceCollectTO to) throws SerException {
        return accountBalanceSer.collect(to);
    }
}