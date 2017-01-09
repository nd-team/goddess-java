package com.bjike.goddess.user.register.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.entity.Money;

/**
 * Created by huanghuanlai on 2017/1/7.
 */
public interface ITestMoneySer {

    void addMoney(Money money,Integer moneyCou) throws SerException;

    void addAccount(String account,Integer moneyCou) throws SerException;

}
