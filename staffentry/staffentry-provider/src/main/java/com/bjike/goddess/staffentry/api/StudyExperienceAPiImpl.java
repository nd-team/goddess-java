package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.StudyExperienceBO;
import com.bjike.goddess.staffentry.service.StudyExperienceImpl;
import com.bjike.goddess.staffentry.service.StudyExperienceSer;
import com.bjike.goddess.staffentry.to.StudyExperienceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学习经历业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:56]
 * @Description: [学习经历业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("studyExperienceAPiImpl")
public class StudyExperienceAPiImpl implements StudyExperienceAPI{

    @Autowired
    private StudyExperienceSer studyExperienceSer;

    @Override
    public void insertStudyExperiences(List<StudyExperienceTO> studyExperienceTOs) throws SerException {
        studyExperienceSer.insertStudyExperiences( studyExperienceTOs);
    }
}
