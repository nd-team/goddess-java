package com.bjike.goddess.user.register.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.user.common.entity.Money;

/**
 * Created by huanghuanlai on 2017/1/7.
 */
public interface ITestMoneySer {

    void addMoney(String account,Integer moneyCou) throws SerException;

}
