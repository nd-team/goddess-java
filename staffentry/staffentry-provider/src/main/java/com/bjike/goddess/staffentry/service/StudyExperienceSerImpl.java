package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.StudyExperienceBO;
import com.bjike.goddess.staffentry.dto.StudyExperienceDTO;
import com.bjike.goddess.staffentry.entity.StudyExperience;
import com.bjike.goddess.staffentry.to.StudyExperienceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 学习经历业务实现
 *
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 16:01]
 * @Description: [学习经历业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class StudyExperienceSerImpl extends ServiceImpl<StudyExperience, StudyExperienceDTO> implements StudyExperienceSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void insertStudyExperiences(List<StudyExperienceTO> studyExperienceTOs) throws SerException {

        List<StudyExperience> studyExperiences = new ArrayList<>(0);
        studyExperienceTOs.stream().forEach(study -> {

            StudyExperience studyExperience = BeanTransform.copyProperties(study, StudyExperience.class, true);
            studyExperience.setCreateTime( LocalDateTime.now());
            studyExperiences.add(studyExperience);
        });
        if (studyExperiences != null && studyExperiences.size() > 0) {
            super.save(studyExperiences);
        }
    }
}
