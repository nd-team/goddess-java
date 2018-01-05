package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.AccountIncomeBO;
import com.bjike.goddess.fundcheck.enums.GuideAddrStatus;
import com.bjike.goddess.fundcheck.service.AccountIncomeSer;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账务收入业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:52 ]
 * @Description: [ 账务收入业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountIncomeApiImpl")
public class AccountIncomeApiImpl implements AccountIncomeAPI {

    @Autowired
    private AccountIncomeSer accountIncomeSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return accountIncomeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accountIncomeSer.guidePermission( guidePermissionTO );
    }
    @Override
    public List<AccountIncomeBO> collect(String startTime,String endTime) throws SerException {
        return accountIncomeSer.collect(startTime,endTime);
    }
}