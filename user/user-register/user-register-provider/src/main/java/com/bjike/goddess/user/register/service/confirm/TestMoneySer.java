package com.bjike.goddess.user.register.service.confirm;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.entity.Money;
import com.bjike.goddess.user.common.service.IMoney;
import com.bjike.goddess.user.register.service.ITestMoneySer;
import org.bytesoft.compensable.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by huanghuanlai on 2017/1/7.
 */
@Service("testMoneySerConfirm")
public class TestMoneySer implements ITestMoneySer {

    @Transactional(rollbackFor = SerException.class)
    public void addMoney(Money money,Integer moneyCou) throws SerException {

    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addAccount(String account,Integer moneyCou) throws SerException {

    }

}
