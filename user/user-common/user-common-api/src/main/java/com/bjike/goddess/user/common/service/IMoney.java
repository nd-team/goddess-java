package com.bjike.goddess.user.common.service;

import com.bjike.goddess.dbs.common.exception.SerException;
import com.bjike.goddess.dbs.common.service.IService;
import com.bjike.goddess.user.common.dto.MoneyDto;
import com.bjike.goddess.user.common.entity.Money;
import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * Created by huanghuanlai on 2017/1/6.
 */
public interface IMoney extends IService<Money,MoneyDto>{

    void addMoney(TransactionContext transactionContext, String account, Integer money) throws SerException;

}
