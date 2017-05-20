package com.bjike.goddess.staffpay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffpay.bo.CollectCompareBO;
import com.bjike.goddess.staffpay.bo.MoneyReadyBO;
import com.bjike.goddess.staffpay.dto.MoneyReadyDTO;
import com.bjike.goddess.staffpay.service.MoneyReadySer;
import com.bjike.goddess.staffpay.to.MoneyReadyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金准备审核表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:03 ]
 * @Description: [ 资金准备审核表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyReadyApiImpl")
public class MoneyReadyApiImpl implements MoneyReadyAPI {

    @Autowired
    private MoneyReadySer moneyReadySer;

    @Override
    public Long countMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        return moneyReadySer.countMoneyReady(moneyReadyDTO);
    }

    @Override
    public MoneyReadyBO getOne(String id) throws SerException {
        return moneyReadySer.getOne(id);
    }

    @Override
    public List<MoneyReadyBO> findListMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        return moneyReadySer.findListMoneyReady(moneyReadyDTO);
    }

    @Override
    public MoneyReadyBO insertMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        return moneyReadySer.insertMoneyReady(moneyReadyTO);
    }

    @Override
    public MoneyReadyBO editMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        return moneyReadySer.editMoneyReady(moneyReadyTO);
    }

    @Override
    public void removeMoneyReady(String id) throws SerException {
        moneyReadySer.removeMoneyReady(id);
    }

    @Override
    public List<CollectCompareBO> collectCompare(Integer month) throws SerException {
        return moneyReadySer.collectCompare(month);
    }

}