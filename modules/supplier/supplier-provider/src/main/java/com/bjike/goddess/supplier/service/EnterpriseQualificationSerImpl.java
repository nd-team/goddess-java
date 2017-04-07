package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.EnterpriseQualificationBO;
import com.bjike.goddess.supplier.dto.EnterpriseQualificationDTO;
import com.bjike.goddess.supplier.entity.EnterpriseQualification;
import com.bjike.goddess.supplier.to.ContactSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    private SupplierInformationSer supplierInformationSer;

    /**
     * 转换企业资质传输对象
     *
     * @param entity 企业资质实体对象
     * @return
     */
    private EnterpriseQualificationBO transformBO(EnterpriseQualification entity) {
        EnterpriseQualificationBO bo = BeanTransform.copyProperties(entity, EnterpriseQualificationBO.class);
        bo.setInformation_id(entity.getInformation().getId());
        return bo;
    }

    @Override
    public List<EnterpriseQualificationBO> findByInformation(String info_id) throws SerException {
        EnterpriseQualificationDTO dto = new EnterpriseQualificationDTO();
        dto.getConditions().add(Restrict.eq("information.id", info_id));
        List<EnterpriseQualification> list = super.findByCis(dto);
        List<EnterpriseQualificationBO> bos = new ArrayList<>(0);
        for (EnterpriseQualification entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EnterpriseQualificationBO save(ContactSituationTO to) throws SerException {
        EnterpriseQualification entity = BeanTransform.copyProperties(to, EnterpriseQualification.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformation_id()));
        super.save(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EnterpriseQualificationBO update(ContactSituationTO to) throws SerException {
        EnterpriseQualification entity = BeanTransform.copyProperties(to, EnterpriseQualification.class);
        entity.setInformation(supplierInformationSer.findById(to.getInformation_id()));
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EnterpriseQualificationBO delete(String id) throws SerException {
        EnterpriseQualification entity = super.findById(id);
        super.remove(entity);
        return this.transformBO(entity);
    }
}