package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.intromanage.bo.WorkExperienceBO;
import com.bjike.goddess.intromanage.dto.WorkExperienceDTO;
import com.bjike.goddess.intromanage.entity.WorkExperience;
import com.bjike.goddess.intromanage.to.WorkExperienceTO;

import java.util.List;

/**
 * 工作经历业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WorkExperienceSer extends Ser<WorkExperience, WorkExperienceDTO> {

    /**
     * 分页查询工作经历
     *
     * @return class WorkExperienceBO
     * @throws SerException
     */
    List<WorkExperienceBO> list(WorkExperienceDTO dto) throws SerException;

    /**
     * 保存工作经历
     *
     * @param to 工作经历to
     * @return class WorkExperienceBO
     * @throws SerException
     */
    WorkExperienceBO save(WorkExperienceTO to) throws SerException;

    /**
     * 根据id删除工作经历
     *
     * @param id 工作经历唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新工作经历
     *
     * @param to 工作经历to
     * @throws SerException
     */
    void update(WorkExperienceTO to) throws SerException;

}