package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.QualificationsCollectBO;
import com.bjike.goddess.qualifications.dto.QualificationsCollectDTO;
import com.bjike.goddess.qualifications.entity.QualificationsCollect;
import com.bjike.goddess.qualifications.to.QualificationsCollectFilterTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectTO;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 资质办理进度汇总业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class QualificationsCollectSerImpl extends ServiceImpl<QualificationsCollect, QualificationsCollectDTO> implements QualificationsCollectSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public QualificationsCollectBO save(QualificationsCollectTO to) throws SerException {
        QualificationsCollect entity = BeanTransform.copyProperties(to, QualificationsCollect.class, true);
        entity.setWriter(userAPI.currentUser().getUsername());
        entity.setModifyTime(LocalDateTime.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, QualificationsCollectBO.class);
    }

    @Override
    public QualificationsCollectBO update(QualificationsCollectTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据id不能为空");
        try {
            QualificationsCollect entity = super.findById(to.getId());
            BeanTransform.copyProperties(to, entity, true);
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);
            return BeanTransform.copyProperties(entity, QualificationsCollectBO.class);
        } catch (SerException e) {
            throw new SerException("数据对象不存在");
        }
    }

    @Override
    public QualificationsCollectBO delete(String id) throws SerException {
        if (StringUtils.isBlank(id))
            throw new SerException("数据id不能为空");
        QualificationsCollect entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, QualificationsCollectBO.class);
    }

    @Override
    public List<QualificationsCollectBO> findByFilter(QualificationsCollectFilterTO to) throws SerException {
        QualificationsCollectDTO dto = new QualificationsCollectDTO();
        if (StringUtils.isNotBlank(to.getCompany()))
            dto.getConditions().add(Restrict.eq("company", to.getCompany()));
        if (StringUtils.isNotBlank(to.getQualifications()))
            dto.getConditions().add(Restrict.eq("qualifications", to.getQualifications()));
        dto.getSorts().add("modifyTime=desc");
        List<QualificationsCollect> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, QualificationsCollectBO.class);
    }

    @Override
    public List<QualificationsCollectBO> maps(QualificationsCollectDTO dto) throws SerException {
        dto.getSorts().add("modifyTime=desc");
        List<QualificationsCollect> list = super.findByPage(dto);
        return BeanTransform.copyProperties(list, QualificationsCollectBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public QualificationsCollectBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), QualificationsCollectBO.class);
    }


}