package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.WorkJoinDutyBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDutyDTO;
import com.bjike.goddess.workjoin.entity.WorkJoinDuty;
import com.bjike.goddess.workjoin.to.WorkJoinDutyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接责任义务业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:20 ]
 * @Description: [ 工作交接责任义务业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class WorkJoinDutySerImpl extends ServiceImpl<WorkJoinDuty, WorkJoinDutyDTO> implements WorkJoinDutySer {

    @Override
    public Long countWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        Long count = super.count(workJoinDutyDTO);
        return count;
    }

    @Override
    public WorkJoinDutyBO getOne(String id) throws SerException {
        WorkJoinDuty workJoinDuty = super.findById(id);
        return BeanTransform.copyProperties(workJoinDuty,WorkJoinDutyBO.class);
    }

    @Override
    public List<WorkJoinDutyBO> findListWorkJoinDuty(WorkJoinDutyDTO workJoinDutyDTO) throws SerException {
        List<WorkJoinDuty> workJoinDuties = super.findByPage(workJoinDutyDTO);
        List<WorkJoinDutyBO> workJoinDutyBOS = BeanTransform.copyProperties(workJoinDuties,WorkJoinDutyBO.class);
        return workJoinDutyBOS;
    }

    @Override
    public WorkJoinDutyBO insertWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        WorkJoinDuty workJoinDuty = BeanTransform.copyProperties(workJoinDutyTO,WorkJoinDuty.class,true);
        workJoinDuty.setCreateTime(LocalDateTime.now());
        super.save(workJoinDuty);
        return BeanTransform.copyProperties(workJoinDuty,WorkJoinDutyBO.class);
    }

    @Override
    public WorkJoinDutyBO editWorkJoinDuty(WorkJoinDutyTO workJoinDutyTO) throws SerException {
        WorkJoinDuty workJoinDuty = super.findById(workJoinDutyTO.getId());
        BeanTransform.copyProperties(workJoinDutyTO,workJoinDuty,true);
        workJoinDuty.setModifyTime(LocalDateTime.now());
        super.update(workJoinDuty);
        return BeanTransform.copyProperties(workJoinDuty,WorkJoinDutyBO.class);

    }

    @Override
    public void removeWorkJoinDuty(String id) throws SerException {
        super.remove(id);
    }
}