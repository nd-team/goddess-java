package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.WorkExperienceBO;
import com.bjike.goddess.staffentry.service.WorkExperienceImpl;
import com.bjike.goddess.staffentry.service.WorkExperienceSer;
import com.bjike.goddess.staffentry.to.WorkExperienceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作经历业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:56]
 * @Description: [工作经历业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("workExperienceApiImpl")
public class WorkExperienceApiImpl implements WorkExperienceAPI{

    @Autowired
    private WorkExperienceSer workExperienceSer;

    @Override
    public void insertWorkExperiences(List<WorkExperienceTO> workExperienceTOs) throws SerException {
        workExperienceSer.insertWorkExperiences( workExperienceTOs);
    }
}
