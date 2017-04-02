package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.qualifications.bo.FacilityInformationBO;
import com.bjike.goddess.qualifications.dto.FacilityInformationDTO;
import com.bjike.goddess.qualifications.entity.FacilityInformation;
import com.bjike.goddess.qualifications.to.FacilityInformationTO;
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
        FacilityInformation entity = BeanTransform.copyProperties(to, FacilityInformation.class), facility = super.findById(to.getId());
        entity.setCreateTime(facility.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, FacilityInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FacilityInformationBO delete(String id) throws SerException {
        FacilityInformation entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, FacilityInformationBO.class);
    }

    @Override
    public List<FacilityInformationBO> all() throws SerException {
        return BeanTransform.copyProperties(super.findAll(), FacilityInformationBO.class);
    }
}