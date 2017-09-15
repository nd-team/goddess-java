package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectroyalty.bo.ProjectFactorsBO;
import com.bjike.goddess.projectroyalty.dto.ProjectFactorsDTO;
import com.bjike.goddess.projectroyalty.entity.ProjectFactors;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.ProjectFactorsTO;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;

import java.util.List;

/**
 * 项目提成分配因素业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 11:39 ]
 * @Description: [ 项目提成分配因素业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectFactorsSer extends Ser<ProjectFactors, ProjectFactorsDTO> {

    /**
     * 保存项目提成分配因素
     *
     * @param to
     * @throws SerException
     */
    default void save(ProjectFactorsTO to) throws SerException {
        return;
    }

    /**
     * 修改
     *
     * @param to
     * @throws SerException
     */
    default void update(ProjectFactorsTO to) throws SerException {
        return;
    }

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
        return;
    }

    /**
     * 根据id获取业务提成定额数据
     *
     * @param id
     * @return
     * @throws SerException
     */
    default ProjectFactorsBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 项目提成分配因素列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ProjectFactorsBO> maps(ProjectFactorsDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目提成分配因素总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal(ProjectFactorsDTO dto) throws SerException {
        return null;
    }

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO
     * @return
     * @throws SerException
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
}