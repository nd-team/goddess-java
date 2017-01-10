package com.bjike.goddess.user.register.service;


import com.bjike.goddess.dbs.common.exception.SerException;

/**
 * Created by huanghuanlai on 2017/1/7.
 */
public interface ITestMoneySer {

    void addMoney(String account,Integer moneyCou) throws SerException;

}
