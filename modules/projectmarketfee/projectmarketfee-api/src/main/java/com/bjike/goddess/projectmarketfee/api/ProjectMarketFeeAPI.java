package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeBO;
import com.bjike.goddess.projectmarketfee.bo.ProjectMarketFeeCountBO;
import com.bjike.goddess.projectmarketfee.dto.ProjectMarketFeeDTO;

import java.util.List;

/**
 * 项目前期的市场活动费业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-10 10:34 ]
 * @Description: [ 项目前期的市场活动费业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectMarketFeeAPI {
    /**
     * 查找
     *
     * @param dto 项目前期的市场活动费分页信息
     * @return class ProjectMarketFeeBO
     * @throws SerException
     */
    default List<ProjectMarketFeeBO> list(ProjectMarketFeeDTO dto) throws SerException {
        return null;
    }

    /**
     * 汇总一级科目
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default List<ProjectMarketFeeCountBO> firstSubjectCount(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 汇总二级科目
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default List<ProjectMarketFeeCountBO> secondSubjectCount(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 汇总三级科目
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default List<ProjectMarketFeeCountBO> thirdSubjectCount(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default List<ProjectMarketFeeCountBO> areaCount(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 项目组汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default List<ProjectMarketFeeCountBO> projectGroupCount(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 项目名称汇总
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default List<ProjectMarketFeeCountBO> projectNameCount(String startTime, String endTime) throws SerException {
        return null;
    }

    /**
     * 查找明细
     *
     * @param firstSubject  一级科目
     * @param secondSubject 二级科目
     * @param thirdSubject  三级科目
     * @param area          地区
     * @param projectGroup  项目组
     * @param projectName   项目名称
     * @return class ProjectMarketFeeBO
     * @throws SerException
     */
    default List<ProjectMarketFeeBO> findDetail(String firstSubject, String secondSubject, String thirdSubject, String area, String projectGroup, String projectName) throws SerException {
        return null;
    }

    /**
     * 根据项目组，地区，年份，月份和项目名称汇总
     *
     * @param projectGroup 项目组
     * @param area         地区
     * @param year         年份
     * @param month        月份
     * @param projectName  项目名称
     * @return class ProjectMarketFeeCountBO
     * @throws SerException
     */
    default ProjectMarketFeeCountBO count(String projectGroup, String area, Integer year, Integer month, String projectName) throws SerException {
        return null;
    }
}