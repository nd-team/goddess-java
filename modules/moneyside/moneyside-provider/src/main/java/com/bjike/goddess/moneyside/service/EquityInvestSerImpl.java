package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.EquityInvestBO;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.dto.EquityInvestDTO;
import com.bjike.goddess.moneyside.entity.EquityInvest;
import com.bjike.goddess.moneyside.entity.InvestForm;
import com.bjike.goddess.moneyside.to.EquityInvestTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 投资条件-股权投资业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:38 ]
 * @Description: [ 投资条件-股权投资业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class EquityInvestSerImpl extends ServiceImpl<EquityInvest, EquityInvestDTO> implements EquityInvestSer {

    @Override
    public Long countEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        Long count = super.count(equityInvestDTO);
        return count;
    }

    @Override
    public EquityInvestBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        EquityInvest equityInvest = super.findById(id);
        return BeanTransform.copyProperties(equityInvest, EquityInvestBO.class);
    }

    @Override
    public List<EquityInvestBO> findListEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        List<EquityInvest> equityInvests = super.findByPage(equityInvestDTO);
        List<EquityInvestBO> equityInvestBOS = BeanTransform.copyProperties(equityInvests,EquityInvestBO.class);
        return equityInvestBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityInvestBO insertEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        EquityInvest equityInvest = BeanTransform.copyProperties(equityInvestTO,EquityInvest.class,true);
        equityInvest.setCreateTime(LocalDateTime.now());
        super.save(equityInvest);
        return BeanTransform.copyProperties(equityInvest,EquityInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EquityInvestBO editEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        if (StringUtils.isBlank(equityInvestTO.getId())) {
            throw new SerException("id不能为空");
        }
        EquityInvest equityInvest = super.findById(equityInvestTO.getId());
        BeanTransform.copyProperties(equityInvestTO,equityInvest,true);
        equityInvest.setModifyTime(LocalDateTime.now());
        super.update(equityInvest);
        return BeanTransform.copyProperties(equityInvest,EquityInvestBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeEquityInvest(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}