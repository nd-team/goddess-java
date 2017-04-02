package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.*;
import com.bjike.goddess.qualifications.dto.QualificationsHandleDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.enums.HandleStatus;
import com.bjike.goddess.qualifications.to.QualificationsHandleForeignTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

/**
 * 资质办理管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsHandleSerImpl extends ServiceImpl<QualificationsHandle, QualificationsHandleDTO> implements QualificationsHandleSer {

    @Autowired
    private PersonnelInformationSer personnelInformationSer;
    @Autowired
    private AuditMaterialSer auditMaterialSer;
    @Autowired
    private CompanyInfoSer companyInfoSer;
    @Autowired
    private FacilityInformationSer facilityInformationSer;
    @Autowired
    private FinanceInfoSer financeInfoSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandleBO save(QualificationsHandleTO to) throws SerException {
        QualificationsHandle entity = BeanTransform.copyProperties(to, QualificationsHandle.class);
        if (null == entity.getCost())
            entity.setCost(0d);
        entity.setStatus(HandleStatus.NONE);
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandleBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandleBO update(QualificationsHandleTO to) throws SerException {
        QualificationsHandle entity = BeanTransform.copyProperties(to, QualificationsHandle.class), handle = super.findById(to.getId());
        if (null == entity.getCost())
            entity.setCost(0d);
        entity.setStatus(HandleStatus.NONE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandleBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandleBO delete(String id) throws SerException {
        QualificationsHandle entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandleBO.class);
    }

    @Override
    public List<QualificationsHandleBO> findStatus() throws SerException {
        QualificationsHandleDTO dto = new QualificationsHandleDTO();
        dto.getConditions().add(Restrict.ne(STATUS, HandleStatus.SUCCESS));
        List<QualificationsHandle> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsHandleBO.class);
    }

    @Override
    public List<QualificationsHandleBO> maps(QualificationsHandleDTO dto) throws SerException {
        List<QualificationsHandle> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QualificationsHandleBO.class);
    }

    @Override
    public QualificationsHandleBO findByType(String type) throws SerException {
        QualificationsHandleDTO dto = new QualificationsHandleDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        QualificationsHandle entity = super.findOne(dto);
        return BeanTransform.copyProperties(entity, QualificationsHandleBO.class);
    }

    @Override
    public List<PersonnelInformationBO> getPersonnel(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id).getPersonnelSet(), PersonnelInformationBO.class);
    }

    @Override
    public List<FinanceInfoBO> getFinance(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id).getFinanceSet(), FinanceInfoBO.class);
    }

    @Override
    public List<FacilityInformationBO> getFacility(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id).getFacilitySet(), FacilityInformationBO.class);
    }

    @Override
    public List<CompanyInfoBO> getCompany(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id).getCompanySet(), CompanyInfoBO.class);
    }

    @Override
    public List<AuditMaterialBO> getAudit(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id).getMaterialSet(), AuditMaterialBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandleBO saveForeign(QualificationsHandleForeignTO to) throws SerException {
        QualificationsHandle entity = super.findById(to.getId());
        entity.setCompanySet(new HashSet<>(0));
        if (null != to.getCompany_ids())
            for (String id : to.getCompany_ids())
                entity.getCompanySet().add(companyInfoSer.findById(id));
        entity.setFacilitySet(new HashSet<>(0));
        if (null != to.getFacility_ids())
            for (String id : to.getFacility_ids())
                entity.getFacilitySet().add(facilityInformationSer.findById(id));
        entity.setFinanceSet(new HashSet<>(0));
        if (null != to.getFinance_ids())
            for (String id : to.getFinance_ids())
                entity.getFinanceSet().add(financeInfoSer.findById(id));
        entity.setMaterialSet(new HashSet<>(0));
        if (null != to.getMaterial_ids())
            for (String id : to.getMaterial_ids())
                entity.getMaterialSet().add(auditMaterialSer.findById(id));
        entity.setPersonnelSet(new HashSet<>(0));
        if (null != to.getPersonnel_ids())
            for (String id : to.getPersonnel_ids())
                entity.getPersonnelSet().add(personnelInformationSer.findById(id));
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandleBO.class);
    }
}