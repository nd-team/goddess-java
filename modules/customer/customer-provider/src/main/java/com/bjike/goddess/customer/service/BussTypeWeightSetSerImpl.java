package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.BussTypeWeightSetBO;
import com.bjike.goddess.customer.dto.BussTypeWeightSetDTO;
import com.bjike.goddess.customer.entity.BussTypeWeightSet;
import com.bjike.goddess.customer.to.BussTypeWeightSetTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 业务类型权重设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:24 ]
 * @Description: [ 业务类型权重设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class BussTypeWeightSetSerImpl extends ServiceImpl<BussTypeWeightSet, BussTypeWeightSetDTO> implements BussTypeWeightSetSer {
    @Override
    public Long countBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {
        Long count = super.count(bussTypeWeightSetDTO);
        return count;
    }

    @Override
    public BussTypeWeightSetBO getOneBussType(String id) throws SerException {
        BussTypeWeightSet bussTypeWeightSet = super.findById(id);
        return BeanTransform.copyProperties(bussTypeWeightSet, BussTypeWeightSetBO.class);
    }

    @Override
    public List<BussTypeWeightSetBO> listBussType(BussTypeWeightSetDTO bussTypeWeightSetDTO) throws SerException {
        List<BussTypeWeightSet> bussTypeWeightSetList = super.findByCis(bussTypeWeightSetDTO, true);
        return BeanTransform.copyProperties(bussTypeWeightSetList, BussTypeWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BussTypeWeightSetBO addBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        BussTypeWeightSet bussTypeWeightSet = BeanTransform.copyProperties(bussTypeWeightSetTO, BussTypeWeightSet.class, true);
        bussTypeWeightSet.setCreateTime(LocalDateTime.now());
        super.save(bussTypeWeightSet);
        return BeanTransform.copyProperties(bussTypeWeightSet, BussTypeWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public BussTypeWeightSetBO editBussType(BussTypeWeightSetTO bussTypeWeightSetTO) throws SerException {
        BussTypeWeightSet bussTypeWeightSet = super.findById(bussTypeWeightSetTO.getId());
        LocalDateTime date = bussTypeWeightSet.getCreateTime();
        bussTypeWeightSet = BeanTransform.copyProperties(bussTypeWeightSetTO, BussTypeWeightSet.class);
        bussTypeWeightSet.setCreateTime(date);
        super.update(bussTypeWeightSet);
        return BeanTransform.copyProperties(bussTypeWeightSet, BussTypeWeightSetBO.class);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteBussType(String id) throws SerException {
        super.remove(id);
    }
}