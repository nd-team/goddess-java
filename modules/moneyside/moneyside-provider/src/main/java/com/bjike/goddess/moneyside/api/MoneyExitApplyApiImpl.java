package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApply;
import com.bjike.goddess.moneyside.service.MoneyExitApplySer;
import com.bjike.goddess.moneyside.to.MoneyExitApplyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金退出申请表业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:37 ]
 * @Description: [ 资金退出申请表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyExitApplyApiImpl")
public class MoneyExitApplyApiImpl implements MoneyExitApplyAPI {
    @Autowired
    private MoneyExitApplySer moneyExitApplySer;
    @Override
    public Long countMoneyExitApply(MoneyExitApplyDTO moneyExitApplyDTO) throws SerException {
        return moneyExitApplySer.countMoneyExitApply(moneyExitApplyDTO);
    }

    @Override
    public MoneyExitApplyBO getOne(String id) throws SerException {
        return moneyExitApplySer.getOne(id);
    }

    @Override
    public List<MoneyExitApplyBO> findListMoneyExitApply(MoneyExitApplyDTO moneyExitApplyDTO) throws SerException {
        return moneyExitApplySer.findListMoneyExitApply(moneyExitApplyDTO);
    }

    @Override
    public MoneyExitApplyBO insertMoneyExitApply(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        return moneyExitApplySer.insertMoneyExitApply(moneyExitApplyTO);
    }

    @Override
    public MoneyExitApplyBO editMoneyExitApply(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        return moneyExitApplySer.editMoneyExitApply(moneyExitApplyTO);
    }

    @Override
    public void removeMoneyExitApply(String id) throws SerException {
        moneyExitApplySer.removeMoneyExitApply(id);
    }
    @Override
    public MoneyExitApplyBO audit(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        return moneyExitApplySer.audit(moneyExitApplyTO);
    }
}