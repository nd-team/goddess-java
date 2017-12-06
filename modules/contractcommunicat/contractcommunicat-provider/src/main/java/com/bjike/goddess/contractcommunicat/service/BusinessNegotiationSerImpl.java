package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.bo.BusinessNegotiationBO;
import com.bjike.goddess.contractcommunicat.dto.BusinessNegotiationDTO;
import com.bjike.goddess.contractcommunicat.entity.BusinessNegotiation;
import com.bjike.goddess.contractcommunicat.to.BusinessNegotiationTO;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 商务洽谈业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-28 11:24 ]
 * @Description: [ 商务洽谈业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class BusinessNegotiationSerImpl extends ServiceImpl<BusinessNegotiation, BusinessNegotiationDTO> implements BusinessNegotiationSer {
    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public BusinessNegotiationBO getOne(String id) throws SerException {
        BusinessNegotiation businessNegotiation = super.findById(id);
        BusinessNegotiationBO businessNegotiationBO = BeanTransform.copyProperties(businessNegotiation,BusinessNegotiationBO.class);
        return businessNegotiationBO;
    }

    @Override
    public Long count(BusinessNegotiationDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<BusinessNegotiationBO> list(BusinessNegotiationDTO dto) throws SerException {
        List<BusinessNegotiation> businessNegotiations = super.findByCis(dto);
        List<BusinessNegotiationBO> businessNegotiationBOS = BeanTransform.copyProperties(businessNegotiations,BusinessNegotiationBO.class);
        return businessNegotiationBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessNegotiationBO insert(BusinessNegotiationTO to) throws SerException {
        BusinessNegotiation businessNegotiation = BeanTransform.copyProperties(to,BusinessNegotiation.class,true);
        businessNegotiation.setCreateTime(LocalDateTime.now());
        super.save(businessNegotiation);
        BusinessNegotiationBO bo = BeanTransform.copyProperties(businessNegotiation,BusinessNegotiationBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public BusinessNegotiationBO edit(BusinessNegotiationTO to) throws SerException {
        if(StringUtils.isNotBlank(to.getId())){
            BusinessNegotiation businessNegotiation = super.findById(to.getId());
            LocalDateTime createTime =businessNegotiation.getCreateTime();
            businessNegotiation = BeanTransform.copyProperties(to,BusinessNegotiation.class,true);
            businessNegotiation.setCreateTime(createTime);
            businessNegotiation.setModifyTime(LocalDateTime.now());
            BusinessNegotiationBO bo = BeanTransform.copyProperties(businessNegotiation,BusinessNegotiationBO.class);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if(StringUtils.isNotBlank(id)){
            super.remove(id);
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public BusinessNegotiationBO importExcel(List<BusinessNegotiationTO> businessNegotiationTOS) throws SerException {
        List<BusinessNegotiation> businessNegotiations = new ArrayList<>(businessNegotiationTOS.size());
        for(BusinessNegotiationTO to:businessNegotiationTOS){
            BusinessNegotiation entity = BeanTransform.copyProperties(to,BusinessNegotiation.class,true);
            businessNegotiations.add(entity);
        }
        super.save(businessNegotiations);
        BusinessNegotiationBO bo = BeanTransform.copyProperties(new BusinessNegotiation(),BusinessNegotiationBO.class);
        return bo;
    }

    @Override
    public byte[] exportExcel(BusinessNegotiationDTO dto) throws SerException {
        return new byte[0];
    }

    @Override
    public byte[] templateExcel() throws SerException {
        return new byte[0];
    }
}