package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.AccountSpendBO;
import com.bjike.goddess.fundcheck.service.AccountSpendSer;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账务支出业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:02 ]
 * @Description: [ 账务支出业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("accountSpendApiImpl")
public class AccountSpendApiImpl implements AccountSpendAPI {
    @Autowired
    private AccountSpendSer accountSpendSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return accountSpendSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return accountSpendSer.guidePermission( guidePermissionTO );
    }
    @Override
    public List<AccountSpendBO> collect(String startTime,String endTime) throws SerException {
        return accountSpendSer.collect(startTime, endTime);
    }
}