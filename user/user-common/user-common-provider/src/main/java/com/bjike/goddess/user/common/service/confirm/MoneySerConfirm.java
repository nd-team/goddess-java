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
	public void add(String id, int money) throws SerException {
		System.out.printf("帐号:"+id+"加了 % 钱", money);
	}

	@Override
	@Transactional(rollbackFor = SerException.class)
	public void remove(String id, int money) throws SerException {
		System.out.printf("帐号:"+id+"减了 % 钱", money);
	}
}
