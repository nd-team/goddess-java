package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.WorkJoinBO;
import com.bjike.goddess.workjoin.dto.WorkJoinDTO;
import com.bjike.goddess.workjoin.entity.WorkJoin;
import com.bjike.goddess.workjoin.to.WorkJoinTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:14 ]
 * @Description: [ 工作交接业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class WorkJoinSerImpl extends ServiceImpl<WorkJoin, WorkJoinDTO> implements WorkJoinSer {

    @Override
    public Long countWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        Long count = super.count(workJoinDTO);
        return count;
    }

    @Override
    public WorkJoinBO getOne(String id) throws SerException {
        WorkJoin workJoin = super.findById(id);
        return BeanTransform.copyProperties(workJoin,WorkJoinBO.class);
    }

    @Override
    public List<WorkJoinBO> findListWorkJoin(WorkJoinDTO workJoinDTO) throws SerException {
        List<WorkJoin> workJoins = super.findByPage(workJoinDTO);
        List<WorkJoinBO> workJoinBOS = BeanTransform.copyProperties(workJoins,WorkJoinBO.class);
        return workJoinBOS;
    }

    @Override
    public WorkJoinBO insertWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        WorkJoin workJoin = BeanTransform.copyProperties(workJoinTO,WorkJoin.class,true);
        workJoin.setCreateTime(LocalDateTime.now());
        super.save(workJoin);
        return BeanTransform.copyProperties(workJoin,WorkJoinBO.class);
    }

    @Override
    public WorkJoinBO editWorkJoin(WorkJoinTO workJoinTO) throws SerException {
        WorkJoin workJoin = super.findById(workJoinTO.getId());
        BeanTransform.copyProperties(workJoinTO,workJoin,true);
        workJoin.setModifyTime(LocalDateTime.now());
        super.update(workJoin);
        return BeanTransform.copyProperties(workJoin,WorkJoinBO. class);
    }

    @Override
    public void removeWorkJoin(String id) throws SerException {
        super.remove(id);
    }
}