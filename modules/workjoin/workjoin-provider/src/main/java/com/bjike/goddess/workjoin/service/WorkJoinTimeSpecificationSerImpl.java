package com.bjike.goddess.workjoin.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.WorkJoinTimeSpecificationBO;
import com.bjike.goddess.workjoin.dto.WorkJoinTimeSpecificationDTO;
import com.bjike.goddess.workjoin.entity.WorkJoinTimeSpecification;
import com.bjike.goddess.workjoin.to.WorkJoinTimeSpecificationTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作交接时间规范业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 05:18 ]
 * @Description: [ 工作交接时间规范业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "workjoinSerCache")
@Service
public class WorkJoinTimeSpecificationSerImpl extends ServiceImpl<WorkJoinTimeSpecification, WorkJoinTimeSpecificationDTO> implements WorkJoinTimeSpecificationSer {

    @Override
    public Long countWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        Long count = super.count(workJoinTimeSpecificationDTO);
        return count;
    }

    @Override
    public WorkJoinTimeSpecificationBO getOne(String id) throws SerException {
        WorkJoinTimeSpecification workJoinTimeSpecification = super.findById(id);
        return BeanTransform.copyProperties(workJoinTimeSpecification, WorkJoinTimeSpecificationBO.class);
    }

    @Override
    public List<WorkJoinTimeSpecificationBO> findListWorkJoinTimeSpecification(WorkJoinTimeSpecificationDTO workJoinTimeSpecificationDTO) throws SerException {
        List<WorkJoinTimeSpecification> workJoinTimeSpecifications = super.findByPage(workJoinTimeSpecificationDTO);
        List<WorkJoinTimeSpecificationBO> workJoinTimeSpecificationBOS = BeanTransform.copyProperties(workJoinTimeSpecifications, WorkJoinTimeSpecificationBO.class);
        return workJoinTimeSpecificationBOS;
    }

    @Override
    public WorkJoinTimeSpecificationBO insertWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        WorkJoinTimeSpecification workJoinTimeSpecification = BeanTransform.copyProperties(workJoinTimeSpecificationTO, WorkJoinTimeSpecification.class, true);
        workJoinTimeSpecification.setCreateTime(LocalDateTime.now());
        super.save(workJoinTimeSpecification);
        return BeanTransform.copyProperties(workJoinTimeSpecification, WorkJoinTimeSpecificationBO.class);
    }

    @Override
    public WorkJoinTimeSpecificationBO editWorkJoinTimeSpecification(WorkJoinTimeSpecificationTO workJoinTimeSpecificationTO) throws SerException {
        WorkJoinTimeSpecification workJoinTimeSpecification = super.findById(workJoinTimeSpecificationTO.getId());
        BeanTransform.copyProperties(workJoinTimeSpecificationTO, workJoinTimeSpecification, true);
        workJoinTimeSpecification.setModifyTime(LocalDateTime.now());
        super.update(workJoinTimeSpecification);
        return BeanTransform.copyProperties(workJoinTimeSpecification, WorkJoinTimeSpecificationBO.class);
    }

    @Override
    public void removeWorkJoinTimeSpecification(String id) throws SerException {
        super.remove(id);
    }
}