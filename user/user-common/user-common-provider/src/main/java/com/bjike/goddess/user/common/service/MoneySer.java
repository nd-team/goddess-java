package com.bjike.goddess.user.common.service;

import com.bjike.goddess.dbs.jpa.dto.Restrict;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.user.common.dto.MoneyDto;
import com.bjike.goddess.user.common.entity.Money;
import com.bjike.goddess.user.common.service.IMoney;
import org.mengyun.tcctransaction.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by huanghuanlai on 2017/1/6.
 */
@Service("moneySer")
public class MoneySer extends ServiceImpl<Money, MoneyDto> implements IMoney {

    @Override
    @Compensable(confirmMethod = "addMoneyConfirm", cancelMethod = "addMoneyCancel")
    @Transactional(rollbackFor = SerException.class)
    public void addMoney(TransactionContext transactionContext, String account, Integer money) throws SerException {
        //正常对数据库进行增加操作
        MoneyDto moneyDto = new MoneyDto();
        moneyDto.getConditions().add(Restrict.eq("account", account));
        Money newMoney = super.findOne(moneyDto);
        if(null==newMoney){
            throw new SerException(account+" 帐户不存在");
        }
        newMoney.setMoney(newMoney.getMoney() + money);
        update(newMoney);
    }

    @Transactional(rollbackFor = SerException.class)
    public void addMoneyConfirm(TransactionContext transactionContext, String account, Integer money) throws SerException {

    }

    @Transactional(rollbackFor = SerException.class)
    public void addMoneyCancel(TransactionContext transactionContext,String account, Integer money) throws SerException {
        //正常对数据进行减少操作
        MoneyDto moneyDto = new MoneyDto();
        moneyDto.getConditions().add(Restrict.eq("account", account));
        Money newMoney = super.findOne(moneyDto);
        if(null==newMoney){
            throw new SerException(account+" 帐户不存在");
        }
        if(newMoney.getMoney()>money){
            newMoney.setMoney(newMoney.getMoney() - money);
        }else{
            throw new SerException("帐号金额不足.");
        }
        update(newMoney);
    }

}
