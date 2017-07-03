package com.bjike.goddess.moneyside.service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.IncomeDistributionBO;
import com.bjike.goddess.moneyside.dto.IncomeDistributionDTO;
import com.bjike.goddess.moneyside.entity.IncomeDistribution;
import com.bjike.goddess.moneyside.to.IncomeDistributionTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 收益比例分配业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class IncomeDistributionSerImpl extends ServiceImpl<IncomeDistribution, IncomeDistributionDTO> implements IncomeDistributionSer {
    @Override
    public Long countIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        Long count = super.count(incomeDistributionDTO);
        return count;
    }

    @Override
    public IncomeDistributionBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        IncomeDistribution incomeDistribution = super.findById(id);
        return BeanTransform.copyProperties(incomeDistribution,IncomeDistributionBO.class);
    }

    @Override
    public List<IncomeDistributionBO> findListIncomeDistribution(IncomeDistributionDTO incomeDistributionDTO) throws SerException {
        List<IncomeDistribution> incomeDistributions = super.findByPage(incomeDistributionDTO);
        List<IncomeDistributionBO> incomeDistributionBOS = BeanTransform.copyProperties(incomeDistributions,IncomeDistributionBO.class);
        return incomeDistributionBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeDistributionBO insertIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        IncomeDistribution incomeDistribution = BeanTransform.copyProperties(incomeDistributionTO,IncomeDistribution.class,true);
        incomeDistribution.setCreateTime(LocalDateTime.now());
        super.save(incomeDistribution);
        return BeanTransform.copyProperties(incomeDistribution,IncomeDistributionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public IncomeDistributionBO editIncomeDistribution(IncomeDistributionTO incomeDistributionTO) throws SerException {
        if (StringUtils.isBlank(incomeDistributionTO.getId())) {
            throw new SerException("id不能为空");
        }
        IncomeDistribution incomeDistribution = super.findById(incomeDistributionTO.getId());
        BeanTransform.copyProperties(incomeDistribution,incomeDistribution,true);
        incomeDistribution.setModifyTime(LocalDateTime.now());
        return BeanTransform.copyProperties(incomeDistribution,IncomeDistributionBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeIncomeDistribution(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}