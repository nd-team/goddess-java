package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyConfirmedBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyConfirmedDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApplyConfirmed;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资金退出申请确认表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:57 ]
 * @Description: [ 资金退出申请确认表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class MoneyExitApplyConfirmedSerImpl extends ServiceImpl<MoneyExitApplyConfirmed, MoneyExitApplyConfirmedDTO> implements MoneyExitApplyConfirmedSer {
    @Override
    public Long countMoneyExitApplyConfirmed(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO) throws SerException {
        Long count = super.count(moneyExitApplyConfirmedDTO);
        return count;
    }

    @Override
    public MoneyExitApplyConfirmedBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        MoneyExitApplyConfirmed moneyExitApplyConfirmed = super.findById(id);
        return BeanTransform.copyProperties(moneyExitApplyConfirmed,MoneyExitApplyConfirmedBO.class);
    }

    @Override
    public List<MoneyExitApplyConfirmedBO> findListMoneyExitApplyConfirmed(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO) throws SerException {
        List<MoneyExitApplyConfirmed> moneyExitApplyConfirmeds = super.findByPage(moneyExitApplyConfirmedDTO);
        List<MoneyExitApplyConfirmedBO> moneyExitApplyConfirmedBOS = BeanTransform.copyProperties(moneyExitApplyConfirmeds,MoneyExitApplyConfirmedBO.class);
        return moneyExitApplyConfirmedBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMoneyExitApplyConfirmed(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);

    }
}