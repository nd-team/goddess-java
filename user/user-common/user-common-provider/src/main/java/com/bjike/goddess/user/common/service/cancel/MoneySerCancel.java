package com.bjike.goddess.user.common.service.cancel;

import com.bjike.goddess.dbs.jpa.dto.Restrict;
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
	public void addMoney(String account, Integer money) throws SerException {
		//此处要对增加的金额进行数据恢复
		MoneyDto moneyDto = new MoneyDto();
		moneyDto.getConditions().add(Restrict.eq("account", account));
		Money newMoney = super.findOne(moneyDto);
		if(null==newMoney){
			throw new SerException(account+" 帐户不存在");
		}
		newMoney.setMoney(newMoney.getMoney() - money);
		update(newMoney);
	}

	@Override
	@Transactional(rollbackFor = SerException.class)
	public void removeMoney(String account, Integer money) throws SerException {
		//此处要对减少的金额进行数据恢复
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
	public void addAccount(String account,Integer moneyCou) throws SerException {
		//此处要对新增的帐户进行数据恢复
		MoneyDto moneyDto = new MoneyDto();
		moneyDto.getConditions().add(Restrict.eq("account", account));
		Money oldMoney = super.findOne(moneyDto);
		if(null==oldMoney){
			throw new SerException(account+" 帐户不存在");
		}
		remove(oldMoney);
	}

}
