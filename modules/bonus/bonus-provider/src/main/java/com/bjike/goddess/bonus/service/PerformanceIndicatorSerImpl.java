package com.bjike.goddess.bonus.service;

import com.bjike.goddess.bonus.bo.PerformanceIndicatorBO;
import com.bjike.goddess.bonus.dto.PerformanceIndicatorDTO;
import com.bjike.goddess.bonus.entity.PerformanceIndicator;
import com.bjike.goddess.bonus.to.PerformanceIndicatorTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 绩效指标业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-08 05:40 ]
 * @Description: [ 绩效指标业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "bonusSerCache")
@Service
public class PerformanceIndicatorSerImpl extends ServiceImpl<PerformanceIndicator, PerformanceIndicatorDTO> implements PerformanceIndicatorSer {

    @Override
    public PerformanceIndicatorBO save(PerformanceIndicatorTO to) throws SerException {
        PerformanceIndicator entity = BeanTransform.copyProperties(to, PerformanceIndicator.class);
        entity.setStatus(Boolean.TRUE);
        super.save(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public PerformanceIndicatorBO update(PerformanceIndicatorTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                PerformanceIndicator entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }

        } else
            throw new SerException("数据ID不能为空");
    }

    @Override
    public PerformanceIndicatorBO delete(String id) throws SerException {
        PerformanceIndicator entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public PerformanceIndicatorBO start(String id) throws SerException {
        PerformanceIndicator entity = super.findById(id);
        entity.setStatus(Boolean.TRUE);
        super.update(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public PerformanceIndicatorBO close(String id) throws SerException {
        PerformanceIndicator entity = super.findById(id);
        entity.setStatus(Boolean.FALSE);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, PerformanceIndicatorBO.class);
    }

    @Override
    public List<PerformanceIndicatorBO> findByStatus(Boolean status) throws SerException {
        PerformanceIndicatorDTO dto = new PerformanceIndicatorDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<PerformanceIndicator> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, PerformanceIndicatorBO.class);
    }
}