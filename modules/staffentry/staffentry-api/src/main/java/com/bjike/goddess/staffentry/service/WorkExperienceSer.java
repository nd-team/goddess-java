package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.dto.WorkExperienceDTO;
import com.bjike.goddess.staffentry.entity.WorkExperience;
import com.bjike.goddess.staffentry.to.WorkExperienceTO;

import java.util.List;

/**
 * 工作经历业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:56]
 * @Description: [工作经历业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface WorkExperienceSer extends Ser<WorkExperience,WorkExperienceDTO> {

    /**
     * 添加工作经历
     * @param workExperienceTOs 工作经历数据集合
     * @throws SerException
     */
    default void insertWorkExperiences(List<WorkExperienceTO> workExperienceTOs ) throws SerException {return ;}
}
