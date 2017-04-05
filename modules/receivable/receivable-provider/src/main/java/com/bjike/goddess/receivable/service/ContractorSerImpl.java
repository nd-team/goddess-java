package com.bjike.goddess.receivable.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.to.ContractorTO;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 承包商列表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "receivableSerCache")
@Service
public class ContractorSerImpl extends ServiceImpl<Contractor, ContractorDTO> implements ContractorSer {
    @Cacheable
    @Override
    public List<ContractorBO> findListContractor(ContractorDTO contractorDTO) throws SerException {
        List<Contractor> contractors = super.findByCis(contractorDTO, true);
        return BeanTransform.copyProperties(contractors, ContractorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractorBO insertContractor(ContractorTO contractorTO) throws SerException {
        Contractor contractor = BeanTransform.copyProperties(contractorTO,Contractor.class);
        contractor.setCreateTime(LocalDateTime.now());
        super.save(contractor);
        return BeanTransform.copyProperties(contractor,ContractorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ContractorBO editContractor(ContractorTO contractorTO) throws SerException {
        if (!StringUtils.isEmpty(contractorTO.getId())) {
            Contractor contractor = super.findById(contractorTO.getId());
            BeanTransform.copyProperties(contractorTO, contractor, true);
            contractor.setModifyTime(LocalDateTime.now());
            super.update(contractor);
        } else {
            throw new SerException("更新ID不能为空!");
        }
        return BeanTransform.copyProperties(contractorTO, ContractorBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeContractor(String id) throws SerException {
        try {
            super.remove(id);
        }catch (SerException e){
            throw  new SerException(e.getMessage());
        }
    }
}