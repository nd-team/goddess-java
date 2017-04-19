package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.HostApplyBO;
import com.bjike.goddess.checkhost.bo.StayApplyBO;
import com.bjike.goddess.checkhost.entity.StayApply;
import com.bjike.goddess.checkhost.to.HostApplyTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.checkhost.dto.HostApplyDTO;
import com.bjike.goddess.checkhost.entity.HostApply;
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
 * 离宿申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 04:51 ]
 * @Description: [ 离宿申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "checkhostSerCache")
@Service
public class HostApplySerImpl extends ServiceImpl<HostApply, HostApplyDTO> implements HostApplySer {

    @Autowired
    private UserAPI userAPI;
    @Cacheable
    @Override
    public List<HostApplyBO> findListHostApply(HostApplyDTO hostApplyDTO) throws SerException {
        List<HostApply> hostApplies = super.findByCis(hostApplyDTO, true);
        return BeanTransform.copyProperties(hostApplies, HostApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HostApplyBO insertHostApply(HostApplyTO hostApplyTO) throws SerException {
        HostApply hostApply = BeanTransform.copyProperties(hostApplyTO, HostApply.class, true);
        hostApply.setCreateTime(LocalDateTime.now());
        super.save(hostApply);
        return BeanTransform.copyProperties(hostApply, HostApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HostApplyBO editHostApply(HostApplyTO hostApplyTO) throws SerException {
        if(!StringUtils.isEmpty(hostApplyTO.getId())){
            HostApply hostApply = super.findById(hostApplyTO.getId());
            BeanTransform.copyProperties(hostApplyTO,hostApply,true);
            hostApply.setModifyTime(LocalDateTime.now());
            super.update(hostApply);
        }else{
            throw new SerException("更新ID不能为空");
        }
        return BeanTransform.copyProperties(hostApplyTO,HostApply.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeHostApply(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public HostApplyBO auditHostApply(HostApplyTO hostApplyTO) throws SerException {
        hostApplyTO.setHeadAudit(userAPI.currentUser().getUsername());
        HostApply hostApply = BeanTransform.copyProperties(hostApplyTO,HostApply.class,true);
        super.update(hostApply);

        HostApplyBO hostApplyBO = BeanTransform.copyProperties(hostApply,HostApplyBO.class);
        return hostApplyBO;
    }
}