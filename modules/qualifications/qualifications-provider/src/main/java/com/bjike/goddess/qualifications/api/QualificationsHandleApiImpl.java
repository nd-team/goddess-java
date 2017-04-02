package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.*;
import com.bjike.goddess.qualifications.dto.QualificationsHandleDTO;
import com.bjike.goddess.qualifications.service.QualificationsHandleSer;
import com.bjike.goddess.qualifications.to.QualificationsHandleForeignTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质办理管理业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("qualificationsHandleApiImpl")
public class QualificationsHandleApiImpl implements QualificationsHandleAPI {

    @Autowired
    private QualificationsHandleSer qualificationsHandleSer;

    @Override
    public QualificationsHandleBO save(QualificationsHandleTO to) throws SerException {
        return qualificationsHandleSer.save(to);
    }

    @Override
    public QualificationsHandleBO update(QualificationsHandleTO to) throws SerException {
        return qualificationsHandleSer.update(to);
    }

    @Override
    public QualificationsHandleBO delete(String id) throws SerException {
        return qualificationsHandleSer.delete(id);
    }

    @Override
    public List<QualificationsHandleBO> findStatus() throws SerException {
        return qualificationsHandleSer.findStatus();
    }

    @Override
    public List<QualificationsHandleBO> maps(QualificationsHandleDTO dto) throws SerException {
        return qualificationsHandleSer.maps(dto);
    }

    @Override
    public QualificationsHandleBO findByType(String type) throws SerException {
        return qualificationsHandleSer.findByType(type);
    }

    @Override
    public List<PersonnelInformationBO> getPersonnel(String id) throws SerException {
        return qualificationsHandleSer.getPersonnel(id);
    }

    @Override
    public List<FinanceInfoBO> getFinance(String id) throws SerException {
        return qualificationsHandleSer.getFinance(id);
    }

    @Override
    public List<FacilityInformationBO> getFacility(String id) throws SerException {
        return qualificationsHandleSer.getFacility(id);
    }

    @Override
    public List<CompanyInfoBO> getCompany(String id) throws SerException {
        return qualificationsHandleSer.getCompany(id);
    }

    @Override
    public List<AuditMaterialBO> getAudit(String id) throws SerException {
        return qualificationsHandleSer.getAudit(id);
    }

    @Override
    public QualificationsHandleBO saveForeign(QualificationsHandleForeignTO to) throws SerException {
        return qualificationsHandleSer.saveForeign(to);
    }
}