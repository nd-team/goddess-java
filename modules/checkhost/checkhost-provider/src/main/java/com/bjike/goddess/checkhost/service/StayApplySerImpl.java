package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.StayApplyBO;
import com.bjike.goddess.checkhost.to.StayApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.checkhost.dto.StayApplyDTO;
import com.bjike.goddess.checkhost.entity.StayApply;
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
 * 住宿申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class StayApplySerImpl extends ServiceImpl<StayApply, StayApplyDTO> implements StayApplySer {

    @Autowired
    private UserAPI userAPI;
    @Cacheable
    @Override
    public List<StayApplyBO> findListStayApply(StayApplyDTO stayApplyDTO) throws SerException {
        List<StayApply> stayApplies = super.findByCis(stayApplyDTO,true);
        return BeanTransform.copyProperties(stayApplies,StayApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO insertStayApply(StayApplyTO stayApplyTO) throws SerException {
        StayApply stayApply = BeanTransform.copyProperties(stayApplyTO,StayApply.class,true);
        stayApply.setCreateTime(LocalDateTime.now());
        super.save(stayApply);
        return BeanTransform.copyProperties(stayApply,StayApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO editStayApply(StayApplyTO stayApplyTO) throws SerException {
        if(!StringUtils.isEmpty(stayApplyTO.getId())){
            StayApply stayApply = super.findById(stayApplyTO.getId());
            BeanTransform.copyProperties(stayApplyTO,stayApply,true);
            stayApply.setModifyTime(LocalDateTime.now());
            super.update(stayApply);
        }else{
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(stayApplyTO,StayApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeStayApply(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public StayApplyBO auditStayApply(StayApplyTO stayApplyTO) throws SerException {
        stayApplyTO.setHeadAudit(userAPI.currentUser().getUsername());
        StayApply stayApply = BeanTransform.copyProperties(stayApplyTO,StayApply.class,true);
        super.update(stayApply);

        StayApplyBO stayApplyBO = BeanTransform.copyProperties(stayApply,StayApplyBO.class);
        return stayApplyBO;
    }
}