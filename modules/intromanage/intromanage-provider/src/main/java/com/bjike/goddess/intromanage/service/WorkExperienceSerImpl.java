package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.WorkExperienceBO;
import com.bjike.goddess.intromanage.dto.WorkExperienceDTO;
import com.bjike.goddess.intromanage.entity.WorkExperience;
import com.bjike.goddess.intromanage.to.WorkExperienceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 工作经历业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class WorkExperienceSerImpl extends ServiceImpl<WorkExperience, WorkExperienceDTO> implements WorkExperienceSer {

    /**
     * 分页查询工作经历
     *
     * @return class WorkExperienceBO
     * @throws SerException
     */
    @Override
    public List<WorkExperienceBO> list(WorkExperienceDTO dto) throws SerException {
        List<WorkExperience> list = super.findByPage(dto);
        List<WorkExperienceBO> listBO = BeanTransform.copyProperties(list, WorkExperienceBO.class);
        return listBO;
    }

    /**
     * 保存工作经历
     *
     * @param to 工作经历to
     * @return class WorkExperienceBO
     * @throws SerException
     */
    @Override
    @Transactional
    public WorkExperienceBO save(WorkExperienceTO to) throws SerException {
        WorkExperience entity = BeanTransform.copyProperties(to, WorkExperience.class, true);
        entity = super.save(entity);
        WorkExperienceBO bo = BeanTransform.copyProperties(entity, WorkExperienceBO.class);
        return bo;
    }

    /**
     * 更新工作经历
     *
     * @param to 工作经历to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(WorkExperienceTO to) throws SerException {
        WorkExperience entity = BeanTransform.copyProperties(to, WorkExperience.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除工作经历
     *
     * @param id 工作经历唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}