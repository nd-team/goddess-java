package com.bjike.goddess.housepay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.housepay.bo.CollectCompareBO;
import com.bjike.goddess.housepay.bo.MoneyReadyBO;
import com.bjike.goddess.housepay.dto.MoneyReadyDTO;
import com.bjike.goddess.housepay.entity.MoneyReady;
import com.bjike.goddess.housepay.service.MoneyReadySer;
import com.bjike.goddess.housepay.to.MoneyReadyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资金准备审核表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:32 ]
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