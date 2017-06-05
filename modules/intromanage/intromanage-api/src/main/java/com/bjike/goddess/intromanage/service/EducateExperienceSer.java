package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.intromanage.bo.EducateExperienceBO;
import com.bjike.goddess.intromanage.dto.EducateExperienceDTO;
import com.bjike.goddess.intromanage.entity.EducateExperience;
import com.bjike.goddess.intromanage.to.EducateExperienceTO;

import java.util.List;

/**
 * 教育经历业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EducateExperienceSer extends Ser<EducateExperience, EducateExperienceDTO> {

    /**
     * 分页查询教育经历
     *
     * @return class EducateExperienceBO
     * @throws SerException
     */
    List<EducateExperienceBO> list(EducateExperienceDTO dto) throws SerException;

    /**
     * 保存教育经历
     *
     * @param to 教育经历to
     * @return class EducateExperienceBO
     * @throws SerException
     */
    EducateExperienceBO save(EducateExperienceTO to) throws SerException;

    /**
     * 根据id删除教育经历
     *
     * @param id 教育经历唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新教育经历
     *
     * @param to 教育经历to
     * @throws SerException
     */
    void update(EducateExperienceTO to) throws SerException;

}