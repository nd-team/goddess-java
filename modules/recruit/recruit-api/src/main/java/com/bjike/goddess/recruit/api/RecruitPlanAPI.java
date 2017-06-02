package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.to.RecruitPlanTO;

import java.util.List;

/**
 * 招聘计划
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface RecruitPlanAPI {

    /**
     * 根据id查询招聘计划
     *
     * @param id 招聘计划唯一标识
     * @return class RecruitPlanBO
     * @throws SerException
     */
    RecruitPlanBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 招聘计划dto
     * @throws SerException
     */
    Long count(RecruitPlanDTO dto) throws SerException;

    /**
     * 分页查询招聘计划
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<RecruitPlanBO> list(RecruitPlanDTO dto) throws SerException;

    /**
     * 保存招聘计划
     *
     * @param recruitPlanTO
     * @return
     * @throws SerException
     */
    RecruitPlanBO save(RecruitPlanTO recruitPlanTO) throws SerException;

    /**
     * 根据id删除招聘计划
     *
     * @param id
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新招聘计划
     *
     * @param recruitPlanTO
     * @throws SerException
     */
    void update(RecruitPlanTO recruitPlanTO) throws SerException;
}
