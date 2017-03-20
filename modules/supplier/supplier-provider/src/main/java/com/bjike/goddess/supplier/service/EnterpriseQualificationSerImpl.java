package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.EnterpriseQualificationBO;
import com.bjike.goddess.supplier.dto.EnterpriseQualificationDTO;
import com.bjike.goddess.supplier.entity.EnterpriseQualification;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业资质业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.265 ]
 * @Description: [ 企业资质业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class EnterpriseQualificationSerImpl extends ServiceImpl<EnterpriseQualification, EnterpriseQualificationDTO> implements EnterpriseQualificationSer {

    @Override
    public List<EnterpriseQualificationBO> findByInformation(String info_id) throws SerException {
        EnterpriseQualificationDTO dto = new EnterpriseQualificationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<EnterpriseQualification> list = super.findByCis(dto, false);
        return BeanTransform.copyProperties(list, EnterpriseQualificationBO.class);
    }

}