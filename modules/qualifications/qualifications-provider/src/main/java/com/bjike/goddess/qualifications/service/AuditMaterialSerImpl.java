package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.dto.AuditMaterialDTO;
import com.bjike.goddess.qualifications.entity.AuditMaterial;
import com.bjike.goddess.qualifications.to.AuditMaterialTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 审核资料业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class AuditMaterialSerImpl extends ServiceImpl<AuditMaterial, AuditMaterialDTO> implements AuditMaterialSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuditMaterialBO save(AuditMaterialTO to) throws SerException {
        AuditMaterial entity = BeanTransform.copyProperties(to, AuditMaterial.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, AuditMaterialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuditMaterialBO update(AuditMaterialTO to) throws SerException {
        AuditMaterial entity = BeanTransform.copyProperties(to, AuditMaterial.class), material = super.findById(to.getId());
        entity.setCreateTime(material.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, AuditMaterialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AuditMaterialBO delete(String id) throws SerException {
        AuditMaterial entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, AuditMaterialBO.class);
    }

    @Override
    public List<AuditMaterialBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), AuditMaterialBO.class);
    }

}