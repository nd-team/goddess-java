package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.AccrualAllotBO;
import com.bjike.goddess.moneyside.dto.AccrualAllotDTO;
import com.bjike.goddess.moneyside.entity.AccrualAllot;
import com.bjike.goddess.moneyside.to.AccrualAllotTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权责分配业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:13 ]
 * @Description: [ 权责分配业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class AccrualAllotSerImpl extends ServiceImpl<AccrualAllot, AccrualAllotDTO> implements AccrualAllotSer {

    @Override
    public Long countAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        Long count = super.count(accrualAllotDTO);
        return count;
    }

    @Override
    public AccrualAllotBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        AccrualAllot accrualAllot = super.findById(id);
        return BeanTransform.copyProperties(accrualAllot, AccrualAllotBO.class);
    }

    @Override
    public List<AccrualAllotBO> findListAccrualAllot(AccrualAllotDTO accrualAllotDTO) throws SerException {
        List<AccrualAllot> accrualAllots = super.findByPage(accrualAllotDTO);
        List<AccrualAllotBO> accrualAllotBOS = BeanTransform.copyProperties(accrualAllots, AccrualAllotBO.class);
        return accrualAllotBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccrualAllotBO insertAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        AccrualAllot accrualAllot = BeanTransform.copyProperties(accrualAllotTO, AccrualAllot.class, true);
        accrualAllot.setCreateTime(LocalDateTime.now());
        super.save(accrualAllot);
        return BeanTransform.copyProperties(accrualAllot, AccrualAllotBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public AccrualAllotBO editAccrualAllot(AccrualAllotTO accrualAllotTO) throws SerException {
        if (StringUtils.isBlank(accrualAllotTO.getId())) {
            throw new SerException("id不能为空");
        }
        AccrualAllot accrualAllot = super.findById(accrualAllotTO.getId());
        BeanTransform.copyProperties(accrualAllotTO, accrualAllot, true);
        accrualAllot.setModifyTime(LocalDateTime.now());
        super.update(accrualAllot);
        return BeanTransform.copyProperties(accrualAllot, AccrualAllotBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeAccrualAllot(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }


}