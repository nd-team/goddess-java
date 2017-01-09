package com.bjike.goddess.user.common.service.confirm;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.user.common.dto.MoneyDto;
import com.bjike.goddess.user.common.entity.Money;
import com.bjike.goddess.user.common.service.IMoney;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("moneySerConfirm")
public class MoneySerConfirm extends ServiceImpl<Money,MoneyDto> implements IMoney {

	@Override
	@Transactional(rollbackFor = SerException.class)
	public void addMoney(String account, Integer money) throws SerException {
		System.out.printf("帐号:"+account+"加了 "+money+" 钱");
	}

	@Override
	@Transactional(rollbackFor = SerException.class)
	public void removeMoney(String account, Integer money) throws SerException {
		System.out.printf("帐号:"+account+"减了 "+money+" 钱");
	}

	@Override
	@Transactional(rollbackFor = SerException.class)
	public void addAccount(String account,Integer moneyCou) throws SerException {
		System.out.printf("帐号:"+account+"新增, 余额: "+moneyCou);
	}
}
