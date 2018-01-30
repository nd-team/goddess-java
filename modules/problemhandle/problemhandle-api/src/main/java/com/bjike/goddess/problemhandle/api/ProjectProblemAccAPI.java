package com.bjike.goddess.problemhandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.problemhandle.bo.OptionBO;
import com.bjike.goddess.problemhandle.bo.ProjectProblemAccBO;
import com.bjike.goddess.problemhandle.bo.ProjectSummaryBO;
import com.bjike.goddess.problemhandle.dto.ProjectProblemAccDTO;
import com.bjike.goddess.problemhandle.excel.SonPermissionObject;
import com.bjike.goddess.problemhandle.to.GuidePermissionTO;
import com.bjike.goddess.problemhandle.to.ProjectProblemAccTO;

import java.util.List;

/**
 * 项目中问题受理和处理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-08 03:43 ]
 * @Description: [ 项目中问题受理和处理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectProblemAccAPI {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 项目中问题受理和处理列表总条数
     */
    default Long countProjectProblem(ProjectProblemAccDTO projectProblemAccDTO) throws SerException {
        return null;
    }

    /**
     * 一个项目中问题受理和处理
     *
     * @return class ProjectProblemAccBO
     */
    default ProjectProblemAccBO getOne(String id) throws SerException {
        return null;
    }


    /**
     * 项目中问题受理和处理
     *
     * @param projectProblemAccDTO 参与处理人员的任务分配dto
     * @return class ProjectProblemAccBO
     * @throws SerException
     */
    default List<ProjectProblemAccBO> findListProjectProblem(ProjectProblemAccDTO projectProblemAccDTO) throws SerException {
        return null;
    }

    /**
     * 添加项目中问题受理和处理
     *
     * @param projectProblemAccTO 项目中问题受理和处理数据to
     * @throws SerException
     */
    default ProjectProblemAccBO insertProjectProblem(ProjectProblemAccTO projectProblemAccTO) throws SerException {
        return null;
    }

    /**
     * 编辑项目中问题受理和处理
     *
     * @param projectProblemAccTO 项目中问题受理和处理to
     * @return class InvolvedProcessingTaskBO
     * @throws SerException
     */
    default ProjectProblemAccBO editProjectProblem(ProjectProblemAccTO projectProblemAccTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除项目中问题受理和处理
     *
     * @param id
     * @throws SerException
     */
    default void removeProjectProblem(String id) throws SerException {

    }

    /**
     * 冻结项目中问题受理和处理
     *
     * @param id id
     */
    default void congeal(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻项目中问题受理和处理
     *
     * @param id id
     */
    default void thaw(String id) throws SerException {
        return;
    }

    /**
     * 导入
     *
     * @param projectProblemAccTOS 项目中问题受理和处理
     * @return class ProjectProblemAccBO
     */
    default void importExcel(List<ProjectProblemAccTO> projectProblemAccTOS) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导入Excel导入模板
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 项目问题受理与处理日汇总
     * @param summationDate 时间
     * @return class ProjectSummaryBO
     * @throws SerException
     */
    default List<ProjectSummaryBO> summaDay(String summationDate) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理周汇总
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class ProjectSummaryBO
     * @throws SerException
     */
    default List<ProjectSummaryBO> summaWeek(Integer year,Integer month,Integer week) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理月汇总
     * @param year 年份
     * @param month 月份
     * @return class ProjectSummaryBO
     * @throws SerException
     */
    default List<ProjectSummaryBO> summaMonth(Integer year,Integer month) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理季度汇总
     * @param year 年份
     * @param quarter 季度
     * @return class ProjectSummaryBO
     * @throws SerException
     */
    default List<ProjectSummaryBO> summaQuarter(Integer year,Integer quarter) throws SerException {
        return null;
    }

    /**
     * 项目问题受理与处理累计汇总
     * @param endDate 截止日期
     * @return class ProjectSummaryBO
     * @throws SerException
     */
    default List<ProjectSummaryBO> summaTotal(String endDate) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理图形展示日汇总数据
     * @param summDate 日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowDay(String summDate) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理图形展示周汇总数据
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowWeek(Integer year,Integer month,Integer week) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理图形展示月汇总数据
     * @param year 年份
     * @param month 月份
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowMonth(Integer year,Integer month) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理图形展示季度汇总数据
     * @param year 年份
     * @param quarter 季度
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowQuarter(Integer year,Integer quarter) throws SerException{
        return null;
    }
    /**
     * 项目问题受理与处理图形展示累计汇总数据
     * @param endDate 截止日期
     * @return class OptionBO
     * @throws SerException
     */
    default OptionBO figureShowTotal(String endDate) throws SerException{
        return null;
    }
}