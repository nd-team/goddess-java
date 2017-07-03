package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.CashInvestBO;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.dto.CashInvestDTO;
import com.bjike.goddess.moneyside.entity.CashInvest;
import com.bjike.goddess.moneyside.to.CashInvestTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 投资条件-现金投资业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:40 ]
 * @Description: [ 投资条件-现金投资业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class CashInvestSerImpl extends ServiceImpl<CashInvest, CashInvestDTO> implements CashInvestSer {

    @Override
    public Long countCashInvest(CashInvestDTO cashInvestDTO) throws SerException {
        Long count = super.count(cashInvestDTO);
        return count;
    }

    @Override
    public CashInvestBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CashInvest cashInvest = super.findById(id);
        return BeanTransform.copyProperties(cashInvest, CashInvestBO.class);
    }

    @Override
    public List<CashInvestBO> findListCashInvest(CashInvestDTO cashInvestDTO) throws SerException {
        List<CashInvest> cashInvests = super.findByPage(cashInvestDTO);
        List<CashInvestBO> cashInvestBOS = BeanTransform.copyProperties(cashInvests, CreditorsInvestBO.class);
        return cashInvestBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CashInvestBO insertCashInvest(CashInvestTO cashInvestTO) throws SerException {
        CashInvest cashInvest = BeanTransform.copyProperties(cashInvestTO,CashInvest.class,true);
        cashInvest.setCreateTime(LocalDateTime.now());
        super.save(cashInvest);
        return BeanTransform.copyProperties(cashInvest,CashInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CashInvestBO editCashInvest(CashInvestTO cashInvestTO) throws SerException {
        if (StringUtils.isBlank(cashInvestTO.getId())) {
            throw new SerException("id不能为空");
        }
        CashInvest cashInvest = super.findById(cashInvestTO.getId());
        BeanTransform.copyProperties(cashInvestTO,cashInvest,true);
        cashInvest.setModifyTime(LocalDateTime.now());
        super.update(cashInvest);
        return BeanTransform.copyProperties(cashInvest,CashInvestBO.class);
    }

    @Override
    public void removeCashInvest(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}