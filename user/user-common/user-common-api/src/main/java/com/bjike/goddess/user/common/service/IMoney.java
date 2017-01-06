package com.bjike.goddess.user.common.service;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.IService;
import com.bjike.goddess.user.common.dto.MoneyDto;
import com.bjike.goddess.user.common.entity.Money;

/**
 * Created by huanghuanlai on 2017/1/6.
 */
public interface IMoney extends IService<Money,MoneyDto>{

    void add(String id,int money) throws SerException;

    void remove(String id,int money) throws SerException;

}
