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
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Long counts(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        Long count = super.count(companyCapabilityDTO);
        return count;
    }

    @Override
    public CompanyCapabilityBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空哦");
        }
        CompanyCapability selfCapability = super.findById(id);
        return BeanTransform.copyProperties(selfCapability,CompanyCapabilityBO.class);

    }

    @Override
    public List<CompanyCapabilityBO> listCompanyCapability(CompanyCapabilityDTO companyCapabilityDTO) throws SerException {
        companyCapabilityDTO.getSorts().add("createTime=desc");
        List<CompanyCapability> list = super.findByPage(companyCapabilityDTO);

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
        if (StringUtils.isBlank(companyCapabilityTO.getId() )) {
            throw new SerException("id不能为空");
        }
        CompanyCapability temp = super.findById(companyCapabilityTO.getId());
        CompanyCapability companyCapability = BeanTransform.copyProperties(companyCapabilityTO,CompanyCapability.class,true);
        BeanUtils.copyProperties(companyCapability,temp,"id","createTime");
        temp.setModifyTime(LocalDateTime.now());
        super.update( temp );
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

    @Override
    public List<String> listAllCompanyName() throws SerException {
        String[] fields = new String[]{"company"};
        List<CompanyCapabilityBO> cooperBOS =super.findBySql("select company  from capability_companycapability group by company " , CompanyCapabilityBO.class, fields);

        List<String> name = cooperBOS.stream().map(CompanyCapabilityBO::getCompany).collect(Collectors.toList());
        return name;
    }


}