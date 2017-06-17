package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.bo.EquityInvestBO;
import com.bjike.goddess.moneyside.dto.CreditorsInvestDTO;
import com.bjike.goddess.moneyside.entity.CreditorsInvest;
import com.bjike.goddess.moneyside.entity.EquityInvest;
import com.bjike.goddess.moneyside.to.CreditorsInvestTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 投资条件-债权投资业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:33 ]
 * @Description: [ 投资条件-债权投资业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class CreditorsInvestSerImpl extends ServiceImpl<CreditorsInvest, CreditorsInvestDTO> implements CreditorsInvestSer {

    @Override
    public Long countCreditorsInvest(CreditorsInvestDTO creditorsInvestDTO) throws SerException {
        Long count = super.count(creditorsInvestDTO);
        return count;
    }

    @Override
    public CreditorsInvestBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        CreditorsInvest creditorsInvest = super.findById(id);
        return BeanTransform.copyProperties(creditorsInvest, CreditorsInvestBO.class);
    }

    @Override
    public List<CreditorsInvestBO> findListCreditorsInvest(CreditorsInvestDTO creditorsInvestDTO) throws SerException {
        List<CreditorsInvest> creditorsInvests = super.findByPage(creditorsInvestDTO);
        List<CreditorsInvestBO> creditorsInvestBOS = BeanTransform.copyProperties(creditorsInvests, CreditorsInvestBO.class);
        return creditorsInvestBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CreditorsInvestBO insertCreditorsInvest(CreditorsInvestTO creditorsInvestTO) throws SerException {
        CreditorsInvest creditorsInvest = BeanTransform.copyProperties(creditorsInvestTO,CreditorsInvest.class,true);
        creditorsInvest.setCreateTime(LocalDateTime.now());
        super.save(creditorsInvest);
        return BeanTransform.copyProperties(creditorsInvest,CreditorsInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CreditorsInvestBO editCreditorsInvest(CreditorsInvestTO creditorsInvestTO) throws SerException {
        if (StringUtils.isBlank(creditorsInvestTO.getId())) {
            throw new SerException("id不能为空");
        }
        CreditorsInvest creditorsInvest = super.findById(creditorsInvestTO.getId());
        BeanTransform.copyProperties(creditorsInvestTO,creditorsInvest,true);
        creditorsInvest.setModifyTime(LocalDateTime.now());
        super.update(creditorsInvest);
        return BeanTransform.copyProperties(creditorsInvest,CreditorsInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeCreditorsInvest(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}