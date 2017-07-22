package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.recruit.bo.CountBO;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.entity.RecruitPlan;
import com.bjike.goddess.recruit.to.GuidePermissionTO;
import com.bjike.goddess.recruit.to.RecruitPlanTO;

import java.util.List;

/**
 * 招聘计划
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-11 11:22]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface RecruitPlanSer extends Ser<RecruitPlan, RecruitPlanDTO> {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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

    /**
     * 计划与实际对比
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<Object> countSituation(RecruitPlanDTO dto) throws SerException;

    /**
     * 日汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<CountBO> dayCount(RecruitPlanDTO dto) throws SerException;

    /**
     * 周汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<CountBO> weekCount(RecruitPlanDTO dto) throws SerException;

    /**
     * 月汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<CountBO> monthCount(RecruitPlanDTO dto) throws SerException;
}
