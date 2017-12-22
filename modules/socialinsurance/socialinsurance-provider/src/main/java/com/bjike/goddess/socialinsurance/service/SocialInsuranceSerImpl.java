package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.socialinsurance.bo.SocialInsuranceBO;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceDTO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;
import com.bjike.goddess.socialinsurance.to.SocialInsuranceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 社会保险管理业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-21 04:23 ]
 * @Description: [ 社会保险管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "socialinsuranceSerCache")
@Service
public class SocialInsuranceSerImpl extends ServiceImpl<SocialInsurance, SocialInsuranceDTO> implements SocialInsuranceSer {

    @Override
    public Long count(SocialInsuranceDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public List<SocialInsuranceBO> list(SocialInsuranceDTO dto) throws SerException {
        List<SocialInsurance> entities = super.findByCis(dto);
        return BeanTransform.copyProperties(entities, SocialInsuranceBO.class);
    }

    @Override
    public SocialInsuranceBO getOne(String id) throws SerException {
        SocialInsurance entity = super.findById(id);
        return BeanTransform.copyProperties(entity, SocialInsuranceBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void add(SocialInsuranceTO to) throws SerException {
        SocialInsurance entity = BeanTransform.copyProperties(to, SocialInsurance.class);
        super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(SocialInsuranceTO to) throws SerException {
        SocialInsurance entity = super.findById(to.getId());
        if (null == entity) {
            throw new SerException("更新实体不存在");
        }
        SocialInsurance newEntity = BeanTransform.copyProperties(to, SocialInsurance.class);
        newEntity.setModifyTime(LocalDateTime.now());
        newEntity.setCreateTime(entity.getCreateTime());
        super.update(newEntity);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void importExcel(List<SocialInsuranceTO> tos) throws SerException {

    }

    @Override
    public byte[] exportExcel(SocialInsuranceDTO dto) throws SerException {
        return new byte[0];
    }
}