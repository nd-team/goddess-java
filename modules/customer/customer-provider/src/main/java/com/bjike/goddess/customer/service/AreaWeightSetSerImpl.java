package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.AreaWeightSetBO;
import com.bjike.goddess.customer.dto.AreaWeightSetDTO;
import com.bjike.goddess.customer.entity.AreaWeightSet;
import com.bjike.goddess.customer.to.AreaWeightSetTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 地区权重设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:19 ]
 * @Description: [ 地区权重设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class AreaWeightSetSerImpl extends ServiceImpl<AreaWeightSet, AreaWeightSetDTO> implements AreaWeightSetSer {

    @Override
    public Long countAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {
        Long count = super.count(areaWeightSetDTO);
        return count;
    }

    @Override
    public AreaWeightSetBO getOneAreaWeight(String id) throws SerException {
        AreaWeightSet areaWeightSet = super.findById(id);
        return BeanTransform.copyProperties(areaWeightSet, AreaWeightSetBO.class);
    }

    @Override
    public List<AreaWeightSetBO> listAreaWeight(AreaWeightSetDTO areaWeightSetDTO) throws SerException {
        List<AreaWeightSet> areaWeightSetList = super.findByCis(areaWeightSetDTO, true);
        return BeanTransform.copyProperties(areaWeightSetList, AreaWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public AreaWeightSetBO addAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        AreaWeightSet areaWeightSet = BeanTransform.copyProperties(areaWeightSetTO, AreaWeightSet.class, true);
        areaWeightSet.setCreateTime(LocalDateTime.now());
        super.save(areaWeightSet);
        return BeanTransform.copyProperties(areaWeightSet, AreaWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public AreaWeightSetBO editAreaWeight(AreaWeightSetTO areaWeightSetTO) throws SerException {
        AreaWeightSet areaWeightSet = super.findById(areaWeightSetTO.getId());
        LocalDateTime date = areaWeightSet.getCreateTime();
        areaWeightSet = BeanTransform.copyProperties(areaWeightSetTO, AreaWeightSet.class, true);
        areaWeightSet.setCreateTime(date);
        return BeanTransform.copyProperties(areaWeightSet, AreaWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteAreaWeight(String id) throws SerException {
        super.remove(id);
    }
}