package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CompanyCapabilityBO;
import com.bjike.goddess.capability.to.CompanyCapabilityTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.capability.dto.CompanyCapabilityDTO;
import com.bjike.goddess.capability.entity.CompanyCapability;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司能力展示业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-23 04:08 ]
 * @Description: [ 公司能力展示业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "capabilitySerCache")
@Service
public class CompanyCapabilitySerImpl extends ServiceImpl<CompanyCapability, CompanyCapabilityDTO> implements CompanyCapabilitySer {

    
    @Override
    public List<CompanyCapabilityBO> listCompanyCapability(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        List<CompanyCapability> list = super.findByCis(companyCapabilityDTO, true);

        return BeanTransform.copyProperties(list, CompanyCapabilityBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyCapabilityBO addCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        CompanyCapability companyCapability = BeanTransform.copyProperties(companyCapabilityTO,CompanyCapability.class,true);
        companyCapability.setCreateTime(LocalDateTime.now());
        super.save( companyCapability );
        return BeanTransform.copyProperties(companyCapability, CompanyCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyCapabilityBO editCompanyCapability(CompanyCapabilityTO companyCapabilityTO) throws SerException {
        CompanyCapability companyCapability = BeanTransform.copyProperties(companyCapabilityTO,CompanyCapability.class,true);
        companyCapability.setModifyTime(LocalDateTime.now());
        super.update( companyCapability );
        return BeanTransform.copyProperties(companyCapability, CompanyCapabilityBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCompanyCapability(String id) throws SerException {
        super.remove( id );
    }

    @Override
    public List<CompanyCapabilityBO> listCompanyCapabilityByName(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        List<CompanyCapabilityBO> companyCapabilityBOS = new ArrayList<>();
        if(StringUtils.isNotBlank(companyCapabilityDTO.getCompany()) ){
            companyCapabilityDTO.getConditions().add( Restrict.like("company",companyCapabilityDTO.getCompany()) );
            List<CompanyCapability> companyCapabilityList =  super.findByPage( companyCapabilityDTO );
            companyCapabilityBOS = BeanTransform.copyProperties( companyCapabilityList ,CompanyCapabilityBO.class );
        }
        return companyCapabilityBOS;
    }
}