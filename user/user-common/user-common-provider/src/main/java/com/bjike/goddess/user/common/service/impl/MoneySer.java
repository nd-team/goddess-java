package com.bjike.goddess.user.common.service.impl;

import com.bjike.goddess.dbs.jpa.dto.Restrict;
import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.user.common.dto.MoneyDto;
import com.bjike.goddess.user.common.entity.Money;
import com.bjike.goddess.user.common.service.IMoney;
import com.bjike.goddess.user.common.service.IUserSer;
import com.bjike.goddess.user.common.service.confirm.MoneySerConfirm;
import org.bytesoft.compensable.Compensable;
import org.bytesoft.compensable.CompensableContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by huanghuanlai on 2017/1/6.
 */
@Service("moneySer")
@Compensable(interfaceClass = IMoney.class, confirmableKey = "moneySerConfirm", cancellableKey = "moneySerCancel")
public class MoneySer extends ServiceImpl<Money, MoneyDto> implements IMoney {

    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addMoney(String account, Integer money) throws SerException {
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeMoney(String account, Integer money) throws SerException {
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

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void addAccount(String account,Integer moneyCou) throws SerException {
        MoneyDto moneyDto = new MoneyDto();
        moneyDto.getConditions().add(Restrict.eq("account", account));
        Money newMoney = super.findOne(moneyDto);
        if(null!=newMoney){
            throw new SerException(account+" 帐户已存在");
        }
        Money money = new Money();
        money.setAccount(account);
        money.setMoney(moneyCou);
//        this.jdbcTemplate.update("insert into demo_money(id,account,money) values(?,?,?)", UUID.randomUUID().toString(), account, moneyCou);
        save(money);
    }
}
