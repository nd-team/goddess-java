package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsGatherBO;
import com.bjike.goddess.qualifications.dto.QualificationsGatherDTO;
import com.bjike.goddess.qualifications.entity.QualificationsGather;
import com.bjike.goddess.qualifications.to.QualificationsGatherTO;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资质办理信息采集业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:33 ]
 * @Description: [ 资质办理信息采集业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsGatherSerImpl extends ServiceImpl<QualificationsGather, QualificationsGatherDTO> implements QualificationsGatherSer {

    @Autowired
    private QualificationsHandleSer handleSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsGatherBO save(QualificationsGatherTO to) throws SerException {
        QualificationsGather entity = BeanTransform.copyProperties(to, QualificationsGather.class, true);
        if (null == handleSer.findByType(to.getType())) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(to.getType());
            handleTO.setCost(to.getCost());
            handleSer.save(handleTO);
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsGatherBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsGatherBO update(QualificationsGatherTO to) throws SerException {
        QualificationsGather entity = BeanTransform.copyProperties(to, QualificationsGather.class, true), gather = super.findById(entity.getId());
        entity.setCreateTime(gather.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        if (null == handleSer.findByType(to.getType())) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(to.getType());
            handleTO.setCost(to.getCost());
            handleSer.save(handleTO);
        }
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsGatherBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsGatherBO delete(String id) throws SerException {
        QualificationsGather entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsGatherBO.class);
    }

    @Override
    public List<QualificationsGatherBO> findByType(String type) throws SerException {
        QualificationsGatherDTO dto = new QualificationsGatherDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<QualificationsGather> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsGatherBO.class);
    }

    @Override
    public List<QualificationsGatherBO> maps(QualificationsGatherDTO dto) throws SerException {
        List<QualificationsGather> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QualificationsGatherBO.class);
    }
}