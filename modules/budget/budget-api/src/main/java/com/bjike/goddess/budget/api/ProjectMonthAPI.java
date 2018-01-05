package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.*;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.ProjectMonthTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 项目收入月业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:59 ]
 * @Description: [ 项目收入月业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectMonthAPI {
    /**
     * 添加
     *
     * @param to 项目收入月信息
     * @return class ProjectMonthBO
     * @throws SerException
     */
    default ProjectMonthBO save(ProjectMonthTO to) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to 项目收入月信息
     * @throws SerException
     */
    default void edit(ProjectMonthTO to) throws SerException {
    }

    /**
     * 删除全部
     *
     * @throws SerException
     */
    default void deleteAll() throws SerException {

    }

    /**
     * 查找
     *
     * @param dto 项目收入月分页信息
     * @return class ProjectMonthBO
     * @throws SerException
     */
    default List<ProjectMonthBO> list(ProjectMonthDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目收入周列表
     *
     * @param dto 项目收入周列表
     * @return class ProjectWeekBO
     * @throws SerException
     */
    default List<ProjectWeekListBO> listProjectMonth(ProjectMonthDTO dto) throws SerException {
        return null;
    }


    /**
     * 通过id查找
     *
     * @param id 项目收入月id
     * @return class ProjectMonthBO
     * @throws SerException
     */
    default ProjectMonthBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 汇总
     *
     * @return class ProjectMonthCountBO
     * @throws SerException
     */
    default List<ProjectMonthCountBO> count() throws SerException {
        return null;
    }

    /**
     * 分项目汇总
     *
     * @param dto1 dto
     * @return class ProjectMonthCountBO
     * @throws SerException
     */
    List<ProjectMonthCountBO> conditionsCount(ProjectMonthDTO dto1) throws SerException;

    /**
     * 查找该月明细
     *
     * @param id 项目收入月id
     * @return class ProjectWeekBO
     * @throws SerException
     */
    default List<ProjectWeekBO> findDetail(String id) throws SerException {
        return null;
    }

    /**
     * 查询总记录数
     *
     * @param dto dto
     * @return class Long
     * @throws SerException
     */
    default Long countNum(ProjectMonthDTO dto) throws SerException {
        return null;
    }

    /**
     * 查找所有项目
     *
     * @throws SerException
     */
    default List<String> findAllProjects() throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    Boolean sonPermission() throws SerException;

    /**
     * 导航权限
     */
    Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException;

    /**
     * 按条件汇总
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ProjectMonthCountBO> collect(ProjectMonthDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目收入周图形化
     *
     * @return
     * @throws SerException
     */
    default OptionBO figureShow() throws SerException {
        return null;
    }
}