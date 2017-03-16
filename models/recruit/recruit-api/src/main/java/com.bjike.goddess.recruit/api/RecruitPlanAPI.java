package com.bjike.goddess.recruit.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.to.RecruitPlanTO;

import java.util.List;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 10:52]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitPlanAPI {

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
