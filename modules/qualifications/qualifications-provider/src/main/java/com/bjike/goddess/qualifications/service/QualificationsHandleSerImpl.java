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
import java.util.ArrayList;
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
        QualificationsHandle entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        if (null == entity.getCost())
            entity.setCost(0d);
        entity.setModifyTime(LocalDateTime.now());
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
        dto.getConditions().add(Restrict.ne("status", HandleStatus.SUCCESS));
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
        QualificationsHandle entity = new QualificationsHandle();
        if (null == entity)
            throw new SerException("该数据不存在");
        if (entity.getMaterialSet() != null)
            return BeanTransform.copyProperties(entity.getMaterialSet(), AuditMaterialBO.class);
        else
            return new ArrayList<>(0);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandleBO saveForeign(QualificationsHandleForeignTO to) throws SerException {
        QualificationsHandle entity = super.findById(to.getId());
        entity.setCompanySet(new HashSet<>(0));
        if (null != to.getCompanyIds())
            for (String id : to.getCompanyIds())
                entity.getCompanySet().add(companyInfoSer.findById(id));
        entity.setFacilitySet(new HashSet<>(0));
        if (null != to.getFacilityIds())
            for (String id : to.getFacilityIds())
                entity.getFacilitySet().add(facilityInformationSer.findById(id));
        entity.setFinanceSet(new HashSet<>(0));
        if (null != to.getFinanceIds())
            for (String id : to.getFinanceIds())
                entity.getFinanceSet().add(financeInfoSer.findById(id));
        entity.setMaterialSet(new HashSet<>(0));
        if (null != to.getMaterialIds())
            for (String id : to.getMaterialIds())
                entity.getMaterialSet().add(auditMaterialSer.findById(id));
        entity.setPersonnelSet(new HashSet<>(0));
        if (null != to.getPersonnelIds())
            for (String id : to.getPersonnelIds())
                entity.getPersonnelSet().add(personnelInformationSer.findById(id));
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandleBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public QualificationsHandleBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), QualificationsHandleBO.class);
    }
}