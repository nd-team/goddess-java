package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectprocing.bo.ProjectCarryBO;
import com.bjike.goddess.projectprocing.entity.ProjectCarry;
import com.bjike.goddess.projectprocing.dto.ProjectCarryDTO;
import com.bjike.goddess.projectprocing.to.ProjectCarryTO;

import java.util.List;

/**
 * 项目实施业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:33 ]
 * @Description: [ 项目实施业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectCarrySer extends Ser<ProjectCarry, ProjectCarryDTO> {

    /**
     * 项目实施列表总条数
     *
     */
    default Long countProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {
        return null;
    }
    /**
     * 项目实施列表
     * @return class ProjectCarryBO
     */
    default List<ProjectCarryBO> listProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {return null;}
    /**
     *  添加
     * @param projectCarryTO 项目实施信息
     * @return class ProjectCarryBO
     */
    default ProjectCarryBO addProjectCarry(ProjectCarryTO projectCarryTO) throws SerException { return null;}

    /**
     *  编辑
     * @param projectCarryTO 项目实施信息
     * @return class ProjectCarryBO
     */
    default ProjectCarryBO editProjectCarry(ProjectCarryTO projectCarryTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteProjectCarry(String id ) throws SerException {return;};

    /**
     * 搜索查询
     * @return class ProjectCarryBO
     */
    default List<ProjectCarryBO> searchListProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {return null;}


}