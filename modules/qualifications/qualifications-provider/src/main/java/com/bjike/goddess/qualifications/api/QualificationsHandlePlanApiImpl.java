package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.service.QualificationsHandlePlanSer;
import com.bjike.goddess.qualifications.to.QualificationsHandlePlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质办理计划业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("qualificationsHandlePlanApiImpl")
public class QualificationsHandlePlanApiImpl implements QualificationsHandlePlanAPI {

    @Autowired
    private QualificationsHandlePlanSer qualificationsHandlePlanSer;

    @Override
    public QualificationsHandlePlanBO save(QualificationsHandlePlanTO to) throws SerException {
        return qualificationsHandlePlanSer.save(to);
    }

    @Override
    public QualificationsHandlePlanBO update(QualificationsHandlePlanTO to) throws SerException {
        return qualificationsHandlePlanSer.update(to);
    }

    @Override
    public QualificationsHandlePlanBO delete(String id) throws SerException {
        return qualificationsHandlePlanSer.delete(id);
    }

    @Override
    public List<QualificationsHandlePlanBO> findByHandle(String handle_id) throws SerException {
        return qualificationsHandlePlanSer.findByHandle(handle_id);
    }
}