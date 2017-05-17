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
    @Override
    public Long countContractor(ContractorDTO contractorDTO) throws SerException {
        Long count = super.count(contractorDTO);
        return count;
    }

    @Override
    public ContractorBO getOne(String id) throws SerException {
        Contractor contractor = super.findById(id);
        return BeanTransform.copyProperties(contractor,ContractorBO.class);
    }
    @Override
    public List<ContractorBO> findListContractor(ContractorDTO contractorDTO) throws SerException {
        List<Contractor> contractors = super.findByPage(contractorDTO);
        List<ContractorBO> contractorBOS = BeanTransform.copyProperties(contractors, ContractorBO.class);
        return contractorBOS;
    }

    @Override
    public ContractorBO insertContractor(ContractorTO contractorTO) throws SerException {
        Contractor contractor = BeanTransform.copyProperties(contractorTO,Contractor.class,true);
        contractor.setCreateTime(LocalDateTime.now());
        super.save(contractor);
        return BeanTransform.copyProperties(contractor,ContractorBO.class);
    }

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

    @Override
    public void removeContractor(String id) throws SerException {
        try {
            super.remove(id);
        }catch (SerException e){
            throw  new SerException(e.getMessage());
        }
    }
}