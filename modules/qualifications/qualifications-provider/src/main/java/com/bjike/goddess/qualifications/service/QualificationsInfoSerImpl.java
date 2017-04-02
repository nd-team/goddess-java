package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsHandleBO;
import com.bjike.goddess.qualifications.bo.QualificationsInfoBO;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.entity.QualificationsInfo;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoStatusTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资质信息管理业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:15 ]
 * @Description: [ 资质信息管理业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsInfoSerImpl extends ServiceImpl<QualificationsInfo, QualificationsInfoDTO> implements QualificationsInfoSer {

    @Autowired
    private QualificationsHandleSer handleSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO save(QualificationsInfoTO to) throws SerException {
        QualificationsInfo entity = BeanTransform.copyProperties(to, QualificationsInfo.class, true);
        QualificationsHandleBO handle = handleSer.findByType(entity.getType());
        if (null == handle) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(entity.getType());
            handleSer.save(handleTO);
        }
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO update(QualificationsInfoTO to) throws SerException {
        QualificationsInfo entity = BeanTransform.copyProperties(to, QualificationsInfo.class, true), info = super.findById(to.getId());
        entity.setModifyTime(LocalDateTime.now());
        entity.setCreateTime(info.getCreateTime());
        QualificationsHandleBO handle = handleSer.findByType(entity.getType());
        if (null == handle) {
            QualificationsHandleTO handleTO = new QualificationsHandleTO();
            handleTO.setType(entity.getType());
            handleSer.save(handleTO);
        }
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO delete(String id) throws SerException {
        QualificationsInfo entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public QualificationsInfoBO updateStatus(QualificationsInfoStatusTO to) throws SerException {
        QualificationsInfo entity = super.findById(to.getId());
        entity.setModifyTime(LocalDateTime.now());
        entity.setStatus(to.getStatus());
        super.update(entity);
        return BeanTransform.copyProperties(entity, QualificationsInfoBO.class);
    }

    @Override
    public List<QualificationsInfoBO> findByType(String type) throws SerException {
        QualificationsInfoDTO dto = new QualificationsInfoDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<QualificationsInfo> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsInfoBO.class);
    }

    @Override
    public List<QualificationsInfoBO> maps(QualificationsInfoDTO dto) throws SerException {
        List<QualificationsInfo> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QualificationsInfoBO.class);
    }
}