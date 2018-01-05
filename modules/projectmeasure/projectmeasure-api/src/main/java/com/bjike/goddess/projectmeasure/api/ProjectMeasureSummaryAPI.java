package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureBO;
import com.bjike.goddess.projectmeasure.bo.ProjectMeasureSummaryBO;
import com.bjike.goddess.projectmeasure.dto.ProjectMeasureSummaryDTO;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectMeasureSummaryTO;

import java.util.List;

/**
 * 项目测算汇总业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 05:24 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectMeasureSummaryAPI {

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
     * 计算总条数
     *
     * @param dto 项目测算汇总dto
     * @throws SerException
     */
    Long count(ProjectMeasureSummaryDTO dto) throws SerException;

    /**
     * 分页查询项目测算汇总邮件发送
     *
     * @param dto 项目测算汇总dto
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    List<ProjectMeasureSummaryBO> list(ProjectMeasureSummaryDTO dto) throws SerException;

    /**
     * 保存项目测算汇总
     *
     * @param to 项目测算汇总to
     * @return class ProjectMeasureSummaryBO
     * @throws SerException
     */
    ProjectMeasureSummaryBO save(ProjectMeasureSummaryTO to) throws SerException;

    /**
     * 根据id删除项目测算汇总
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 编辑项目测算汇总
     *
     * @param to 项目测算汇总to
     * @throws SerException
     */
    void update(ProjectMeasureSummaryTO to) throws SerException;

    /**
     * 解冻项目测算汇总
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    void thaw(String id) throws SerException;

    /**
     * 冻结项目测算汇总
     *
     * @param id 项目测算汇总唯一标识
     * @throws SerException
     */
    void congeal(String id) throws SerException;

    /**
     * 项目测算汇总
     *
     * @param areas 汇总地区
     * @return class ProjectMeasureBO
     * @throws SerException
     */
    List<ProjectMeasureBO> summarize(String[] areas) throws SerException;

    /**
     * 定时器检测要发送的邮件
     *
     */
    default void checkSendEmail() throws SerException {
        return ;
    }

    /**
     * 一个个邮件
     * @return class CollectEmailBO
     */
    default ProjectMeasureSummaryBO getOne(String id) throws SerException {return null;}

}