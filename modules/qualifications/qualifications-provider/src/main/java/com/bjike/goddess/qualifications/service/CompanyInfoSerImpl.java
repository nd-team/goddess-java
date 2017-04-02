package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.CompanyInfoBO;
import com.bjike.goddess.qualifications.dto.CompanyInfoDTO;
import com.bjike.goddess.qualifications.entity.CompanyInfo;
import com.bjike.goddess.qualifications.to.CompanyInfoTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 公司基本信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:38 ]
 * @Description: [ 公司基本信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class CompanyInfoSerImpl extends ServiceImpl<CompanyInfo, CompanyInfoDTO> implements CompanyInfoSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyInfoBO save(CompanyInfoTO to) throws SerException {
        CompanyInfo entity = BeanTransform.copyProperties(to, CompanyInfo.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, CompanyInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyInfoBO update(CompanyInfoTO to) throws SerException {
        CompanyInfo entity = BeanTransform.copyProperties(to, CompanyInfo.class), company = super.findById(to.getId());
        entity.setCreateTime(company.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, CompanyInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CompanyInfoBO delete(String id) throws SerException {
        CompanyInfo entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, CompanyInfoBO.class);
    }

    @Override
    public List<CompanyInfoBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), CompanyInfoBO.class);
    }
}