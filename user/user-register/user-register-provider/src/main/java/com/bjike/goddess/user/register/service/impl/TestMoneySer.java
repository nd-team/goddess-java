package com.bjike.goddess.user.register.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.entity.Money;
import com.bjike.goddess.user.common.service.IMoney;
import com.bjike.goddess.user.register.service.ITestMoneySer;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by huanghuanlai on 2017/1/7.
 */
@Service("testMoneySer")
@Compensable(interfaceClass = ITestMoneySer.class, confirmableKey = "testMoneySerConfirm", cancellableKey = "testMoneySerCancel")
public class TestMoneySer implements ITestMoneySer {

    @Resource(name = "remoteMoney")
    private IMoney iMoney;

    @Transactional(rollbackFor = SerException.class)
    public void addMoney(Money money,Integer moneyCou) throws SerException {
        iMoney.addMoney(money.getAccount(),moneyCou);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addAccount(String account,Integer moneyCou) throws SerException {
        iMoney.addAccount(account,moneyCou);
    }

}
