package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.ProjectBO;
import com.bjike.goddess.reportmanagement.dto.ProjectDTO;
import com.bjike.goddess.reportmanagement.to.GuidePermissionTO;
import com.bjike.goddess.reportmanagement.to.ProjectTO;

import java.util.List;

/**
 * 项目表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 01:58 ]
 * @Description: [ 项目表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectAPI {

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
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ProjectBO> list(ProjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(ProjectTO to) throws SerException {
        return;
    }

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    default ProjectBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(ProjectTO to) throws SerException {
        return;
    }

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long count(ProjectDTO dto) throws SerException {
        return null;
    }
}