package com.bjike.goddess.user.common.service.impl;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.user.common.dto.MoneyDto;
import com.bjike.goddess.user.common.entity.Money;
import com.bjike.goddess.user.common.service.IMoney;
import com.bjike.goddess.user.common.service.IUserSer;
import com.bjike.goddess.user.common.service.confirm.MoneySerConfirm;
import org.bytesoft.compensable.Compensable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huanghuanlai on 2017/1/6.
 */
@Service("moneySer")
@Compensable(interfaceClass = IMoney.class, confirmableKey = "moneySerConfirm", cancellableKey = "moneySerCancel")
public class MoneySer extends ServiceImpl<Money,MoneyDto> implements IMoney {

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void add(String id, int money) throws SerException {
        //正常对数据库进行增加操作
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id, int money) throws SerException {
        //正常对数据进行减少操作
    }
}
