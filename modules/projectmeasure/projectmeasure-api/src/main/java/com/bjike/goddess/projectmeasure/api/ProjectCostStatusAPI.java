package com.bjike.goddess.projectmeasure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmeasure.bo.ProjectCostStatusBO;
import com.bjike.goddess.projectmeasure.dto.ProjectCostStatusDTO;
import com.bjike.goddess.projectmeasure.to.GuidePermissionTO;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;
import com.bjike.goddess.projectmeasure.to.ProjectCostStatusTO;

import java.util.List;

/**
 * 项目费用情况业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-23 10:21 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectCostStatusAPI {

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
     * 根据id查询项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    ProjectCostStatusBO findById(String id) throws SerException;

    /**
     * 计算总条数
     *
     * @param dto 项目费用情况dto
     * @throws SerException
     */
    Long count(ProjectCostStatusDTO dto) throws SerException;

    /**
     * 分页查询项目费用情况
     *
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    List<ProjectCostStatusBO> list(ProjectCostStatusDTO dto) throws SerException;

    /**
     * 保存项目费用情况
     *
     * @param to 项目费用情况to
     * @return class ProjectCostStatusBO
     * @throws SerException
     */
    ProjectCostStatusBO save(ProjectCostStatusTO to) throws SerException;

    /**
     * 根据id删除项目费用情况
     *
     * @param id 项目费用情况唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新项目费用情况
     *
     * @param to 项目费用情况to
     * @throws SerException
     */
    void update(ProjectCostStatusTO to) throws SerException;

}