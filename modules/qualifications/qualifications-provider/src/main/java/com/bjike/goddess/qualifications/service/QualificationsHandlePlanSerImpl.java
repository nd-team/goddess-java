package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsHandlePlanBO;
import com.bjike.goddess.qualifications.dto.QualificationsHandlePlanDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandlePlan;
import com.bjike.goddess.qualifications.to.QualificationsHandlePlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资质办理计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:46 ]
 * @Description: [ 资质办理计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsHandlePlanSerImpl extends ServiceImpl<QualificationsHandlePlan, QualificationsHandlePlanDTO> implements QualificationsHandlePlanSer {

    @Autowired
    private QualificationsHandleSer handleSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandlePlanBO save(QualificationsHandlePlanTO to) throws SerException {
        QualificationsHandlePlan entity = BeanTransform.copyProperties(to, QualificationsHandlePlan.class, true);
        entity.setHandle(handleSer.findById(to.getHandle_id()));
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandlePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandlePlanBO update(QualificationsHandlePlanTO to) throws SerException {
        QualificationsHandlePlan entity = BeanTransform.copyProperties(to, QualificationsHandlePlan.class, true), plan = super.findById(to.getId());
        entity.setModifyTime(LocalDateTime.now());
        entity.setCreateTime(plan.getCreateTime());
        entity.setHandle(handleSer.findById(to.getHandle_id()));
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandlePlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsHandlePlanBO delete(String id) throws SerException {
        QualificationsHandlePlan entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsHandlePlanBO.class);
    }

    @Override
    public List<QualificationsHandlePlanBO> findByHandle(String handle_id) throws SerException {
        QualificationsHandlePlanDTO dto = new QualificationsHandlePlanDTO();
        dto.getConditions().add(Restrict.eq("handle.id", handle_id));
        List<QualificationsHandlePlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsHandlePlanBO.class);
    }
}