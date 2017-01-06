package com.bjike.goddess.user.common.service.cancel;

import com.bjike.goddess.dbs.jpa.exception.SerException;
import com.bjike.goddess.dbs.jpa.service.ServiceImpl;
import com.bjike.goddess.user.common.dto.MoneyDto;
import com.bjike.goddess.user.common.entity.Money;
import com.bjike.goddess.user.common.service.IMoney;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("moneySerCancel")
public class MoneySerCancel extends ServiceImpl<Money,MoneyDto> implements IMoney {

	@Override
	@Transactional(rollbackFor = SerException.class)
	public void add(String id, int money) throws SerException {
		//此处要对增加的金额进行数据减少
	}

	@Override
	@Transactional(rollbackFor = SerException.class)
	public void remove(String id, int money) throws SerException {
		//此处要对删除的金额进行数据增加
	}

}
