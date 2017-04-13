package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;
import com.bjike.goddess.checkhost.entity.StayDays;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工住宿天数汇总业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class StayDaysSerImpl extends ServiceImpl<StayDays, StayDaysDTO> implements StayDaysSer {
    @Autowired
    private UserAPI userAPI;

    @Cacheable
    @Override
    public List<StayDaysBO> findListStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        List<StayDays> stayDaysList = super.findByCis(stayDaysDTO, true);
        return BeanTransform.copyProperties(stayDaysList, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayDaysBO insertStayDays(StayDaysTO stayDaysTO) throws SerException {
        StayDays stayDays = BeanTransform.copyProperties(stayDaysTO, StayDays.class, true);
        stayDays.setCreateTime(LocalDateTime.now());
        super.save(stayDays);
        return BeanTransform.copyProperties(stayDays, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayDaysBO editStayDays(StayDaysTO stayDaysTO) throws SerException {
        if (!StringUtils.isEmpty(stayDaysTO.getId())) {
            StayDays stayDays = super.findById(stayDaysTO.getId());
            BeanTransform.copyProperties(stayDaysTO, stayDays, true);
            stayDays.setModifyTime(LocalDateTime.now());
            super.update(stayDays);
        } else {
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(stayDaysTO, StayDaysBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayDays(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayDaysBO auditStayDays(StayDaysTO stayDaysTO) throws SerException {
        stayDaysTO.setComprehensiveVerify(userAPI.currentUser().getUsername());
        StayDays stayDays = BeanTransform.copyProperties(stayDaysTO, StayDays.class, true);
        super.update(stayDays);

        StayDaysBO stayDaysBO = BeanTransform.copyProperties(stayDays, StayDaysBO.class);
        return stayDaysBO;
    }
}