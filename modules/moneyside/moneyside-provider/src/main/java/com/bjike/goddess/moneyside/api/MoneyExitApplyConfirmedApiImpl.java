package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyConfirmedBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyConfirmedDTO;
import com.bjike.goddess.moneyside.service.MoneyExitApplyConfirmedSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金退出申请确认表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:57 ]
 * @Description: [ 资金退出申请确认表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyExitApplyConfirmedApiImpl")
public class MoneyExitApplyConfirmedApiImpl implements MoneyExitApplyConfirmedAPI {
    @Autowired
    private MoneyExitApplyConfirmedSer moneyExitApplyConfirmedSer;

    @Override
    public Long countMoneyExitApplyConfirmed(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO) throws SerException {
        return moneyExitApplyConfirmedSer.countMoneyExitApplyConfirmed(moneyExitApplyConfirmedDTO);
    }

    @Override
    public MoneyExitApplyConfirmedBO getOne(String id) throws SerException {
        return moneyExitApplyConfirmedSer.getOne(id);
    }

    @Override
    public List<MoneyExitApplyConfirmedBO> findListMoneyExitApplyConfirmed(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO) throws SerException {
        return moneyExitApplyConfirmedSer.findListMoneyExitApplyConfirmed(moneyExitApplyConfirmedDTO);
    }

    @Override
    public void removeMoneyExitApplyConfirmed(String id) throws SerException {
        moneyExitApplyConfirmedSer.removeMoneyExitApplyConfirmed(id);
    }
}