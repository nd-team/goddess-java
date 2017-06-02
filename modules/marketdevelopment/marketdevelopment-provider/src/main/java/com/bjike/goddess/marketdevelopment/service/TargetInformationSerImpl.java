package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.TargetInformationBO;
import com.bjike.goddess.marketdevelopment.dto.TargetInformationDTO;
import com.bjike.goddess.marketdevelopment.entity.TargetInformation;
import com.bjike.goddess.marketdevelopment.to.TargetInformationTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private MarPermissionSer marPermissionSer;

    private static final String marketCheck = "market-check";

    private static final String marketManage = "market-manage";

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
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                TargetInformation entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, TargetInformationBO.class);
            } catch (Exception e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public TargetInformationBO delete(TargetInformationTO to) throws SerException {
        TargetInformation entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
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

    @Override
    public List<TargetInformationBO> findByArea(String area) throws SerException {
        TargetInformationDTO dto = new TargetInformationDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        List<TargetInformation> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, TargetInformationBO.class);
    }
}