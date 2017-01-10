package com.bjike.goddess.user.register.service;

import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.user.common.service.IMoney;
import org.mengyun.tcctransaction.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huanghuanlai on 2017/1/7.
 */
@Service
public class TestMoneySer implements ITestMoneySer {

    @Autowired
    private IMoney iMoney;

    @Transactional(rollbackFor = SerException.class)
    @Compensable(confirmMethod = "addMoneyConfirm",cancelMethod = "addMoneyCancel")
    public void addMoney(String account,Integer moneyCou) throws SerException {
        iMoney.addMoney(null,account,moneyCou);//调用远程服务
    }

    @Transactional(rollbackFor = SerException.class)
    public void addMoneyConfirm(String account,Integer moneyCou) throws SerException {

    }

    @Transactional(rollbackFor = SerException.class)
    public void addMoneyCancel(String account,Integer moneyCou) throws SerException {

    }

}
