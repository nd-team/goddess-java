package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.TargetInformationBO;
import com.bjike.goddess.marketdevelopment.dto.TargetInformationDTO;
import com.bjike.goddess.marketdevelopment.entity.TargetInformation;
import com.bjike.goddess.marketdevelopment.to.TargetInformationTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 确定目标信息业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:12 ]
 * @Description: [ 确定目标信息业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class TargetInformationSerImpl extends ServiceImpl<TargetInformation, TargetInformationDTO> implements TargetInformationSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TargetInformationBO save(TargetInformationTO to) throws SerException {
        TargetInformation entity = BeanTransform.copyProperties(to, TargetInformation.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, TargetInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TargetInformationBO update(TargetInformationTO to) throws SerException {
        TargetInformation entity = BeanTransform.copyProperties(to, TargetInformation.class);
        super.update(entity);
        return BeanTransform.copyProperties(entity, TargetInformationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TargetInformationBO delete(TargetInformationTO to) throws SerException {
        TargetInformation entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, TargetInformationBO.class);
    }

    @Override
    public List<TargetInformationBO> findByType(String type) throws SerException {
        TargetInformationDTO dto = new TargetInformationDTO();
        dto.getConditions().add(Restrict.eq("type", type));
        List<TargetInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, TargetInformationBO.class);
    }

    @Override
    public List<TargetInformationBO> findByCourse(String course) throws SerException {
        TargetInformationDTO dto = new TargetInformationDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        List<TargetInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, TargetInformationBO.class);
    }

    @Override
    public List<TargetInformationBO> findByCourseType(String type, String course) throws SerException {
        TargetInformationDTO dto = new TargetInformationDTO();
        dto.getConditions().add(Restrict.eq("course", course));
        dto.getConditions().add(Restrict.eq("type", type));
        List<TargetInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, TargetInformationBO.class);
    }


}