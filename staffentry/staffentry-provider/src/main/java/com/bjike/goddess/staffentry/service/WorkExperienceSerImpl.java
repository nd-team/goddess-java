package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.WorkExperienceBO;
import com.bjike.goddess.staffentry.dto.WorkExperienceDTO;
import com.bjike.goddess.staffentry.entity.WorkExperience;
import com.bjike.goddess.staffentry.to.WorkExperienceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 工作经历业务实现
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 16:01]
 * @Description: [工作经历业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class WorkExperienceSerImpl extends ServiceImpl<WorkExperience, WorkExperienceDTO> implements WorkExperienceSer {

    @Transactional(rollbackFor = SerException.class)
    @Override


    public void insertWorkExperiences(List<WorkExperienceTO> workExperienceTOs) throws SerException {
        List<WorkExperience> workExperiences = new ArrayList<>(0);
        workExperienceTOs.stream().forEach(work -> {

            WorkExperience workExperience = BeanTransform.copyProperties(work, WorkExperience.class, true);
            workExperience.setCreateTime( LocalDateTime.now());
            workExperiences.add(workExperience);
        });
        if (workExperiences != null && workExperiences.size() > 0) {
            super.save(workExperiences);
        }
    }
}
