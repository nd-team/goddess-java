package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.intromanage.bo.EducateExperienceBO;
import com.bjike.goddess.intromanage.dto.EducateExperienceDTO;
import com.bjike.goddess.intromanage.to.EducateExperienceTO;

import java.util.List;

/**
 * 教育经历业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:40 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EducateExperienceAPI {

    /**
     * 根据id查询教育经历
     *
     * @param id 教育经历唯一标识
     * @return class EducateExperienceBO
     * @throws SerException
     */
    EducateExperienceBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 教育经历dto
     * @throws SerException
     */
    Long count(EducateExperienceDTO dto) throws SerException;

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