package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CashInvestBO;
import com.bjike.goddess.moneyside.dto.CashInvestDTO;
import com.bjike.goddess.moneyside.service.CashInvestSer;
import com.bjike.goddess.moneyside.to.CashInvestTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 投资条件-现金投资业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:40 ]
 * @Description: [ 投资条件-现金投资业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cashInvestApiImpl")
public class CashInvestApiImpl implements CashInvestAPI {

    @Autowired
    private CashInvestSer cashInvestSer;

    @Override
    public Long countCashInvest(CashInvestDTO cashInvestDTO) throws SerException {
        return cashInvestSer.countCashInvest(cashInvestDTO);
    }

    @Override
    public CashInvestBO getOne(String id) throws SerException {
        return cashInvestSer.getOne(id);
    }

    @Override
    public List<CashInvestBO> findListCashInvest(CashInvestDTO cashInvestDTO) throws SerException {
        return cashInvestSer.findListCashInvest(cashInvestDTO);
    }

    @Override
    public CashInvestBO insertCashInvest(CashInvestTO cashInvestTO) throws SerException {
        return cashInvestSer.insertCashInvest(cashInvestTO);
    }

    @Override
    public CashInvestBO editCashInvest(CashInvestTO cashInvestTO) throws SerException {
        return cashInvestSer.editCashInvest(cashInvestTO);
    }

    @Override
    public void removeCashInvest(String id) throws SerException {
        cashInvestSer.removeCashInvest(id);
    }
}