package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.dto.InvestFormDTO;
import com.bjike.goddess.moneyside.entity.InvestForm;
import com.bjike.goddess.moneyside.to.InvestFormTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 投资形式业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:28 ]
 * @Description: [ 投资形式业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class InvestFormSerImpl extends ServiceImpl<InvestForm, InvestFormDTO> implements InvestFormSer {


    @Override
    public Long countInvestForm(InvestFormDTO investFormDTO) throws SerException {
        Long count = super.count(investFormDTO);
        return count;
    }

    @Override
    public InvestFormBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        InvestForm investForm = super.findById(id);
        return BeanTransform.copyProperties(investForm,InvestFormBO.class);
    }

    @Override
    public List<InvestFormBO> findListInvestForm(InvestFormDTO investFormDTO) throws SerException {
        List<InvestForm> investForms = super.findByPage(investFormDTO);
        List<InvestFormBO> investFormBOS = BeanTransform.copyProperties(investForms,InvestFormBO.class);
        return investFormBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvestFormBO insertInvestForm(InvestFormTO investFormTO) throws SerException {
        InvestForm investForm = BeanTransform.copyProperties(investFormTO,InvestForm.class,true);
        investForm.setCreateTime(LocalDateTime.now());
        super.save(investForm);
        return BeanTransform.copyProperties(investForm,InvestFormBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InvestFormBO editInvestForm(InvestFormTO investFormTO) throws SerException {
        if(StringUtils.isBlank(investFormTO.getId())){
            throw new SerException("id不能为空");
        }
        InvestForm investForm = super.findById(investFormTO.getId());
        BeanTransform.copyProperties(investFormTO,investForm,true);
        investForm.setModifyTime(LocalDateTime.now());
        super.update(investForm);
        return BeanTransform.copyProperties(investForm,InvestFormBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeInvestForm(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
}