package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.FacilityInformationBO;
import com.bjike.goddess.qualifications.dto.FacilityInformationDTO;
import com.bjike.goddess.qualifications.entity.FacilityInformation;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.to.FacilityInformationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 设备信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:59 ]
 * @Description: [ 设备信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "qualificationsSerCache")
@Service
public class FacilityInformationSerImpl extends ServiceImpl<FacilityInformation, FacilityInformationDTO> implements FacilityInformationSer {

    @Autowired
    private QualificationsHandleSer qualificationsHandleSer;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FacilityInformationBO save(FacilityInformationTO to) throws SerException {
        FacilityInformation entity = BeanTransform.copyProperties(to, FacilityInformation.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, FacilityInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FacilityInformationBO update(FacilityInformationTO to) throws SerException {
        FacilityInformation entity = super.findById(to.getId());
        if (null == entity)
            throw new SerException("该数据不存在");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, FacilityInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FacilityInformationBO delete(String id) throws SerException {
        FacilityInformation entity = super.findById(id);
        if(null == entity)
            throw new SerException("该数据不存在");
        List<QualificationsHandle> list = qualificationsHandleSer.findAll();
        for (QualificationsHandle handle : list)
            if (handle.getFacilitySet().stream().filter(m -> m.getId().equals(id)).count() != 0)
                throw new SerException("存在依赖关系,无法删除");super.remove(entity);
        return BeanTransform.copyProperties(entity, FacilityInformationBO.class);
    }

    @Override
    public List<FacilityInformationBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), FacilityInformationBO.class);
    }

    @Override
    public List<FacilityInformationBO> maps(FacilityInformationDTO dto) throws SerException {
        return BeanTransform.copyProperties(super.findByPage(dto), FacilityInformationBO.class);
    }

    @Override
    public Integer getTotal() throws SerException {
        return super.findAll().size();
    }

    @Override
    public FacilityInformationBO getById(String id) throws SerException {
        return BeanTransform.copyProperties(super.findById(id), FacilityInformationBO.class);
    }
}