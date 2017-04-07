package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectAcceptanceBO;
import com.bjike.goddess.projectprocing.dto.ProjectAcceptanceDTO;
import com.bjike.goddess.projectprocing.to.ProjectAcceptanceTO;

import java.util.List;

/**
 * 项目验收情况业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:54 ]
 * @Description: [ 项目验收情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectAcceptanceAPI {

    /**
     * 项目验收情况列表总条数
     *
     */
    default Long countProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        return null;
    }
    /**
     * 项目验收情况列表
     * @return class ProjectAcceptanceBO
     */
    default List<ProjectAcceptanceBO> listProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {return null;}
    /**
     *  添加
     * @param projectAcceptanceTO 项目验收情况信息
     * @return class ProjectAcceptanceBO
     */
    default ProjectAcceptanceBO addProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException { return null;}

    /**
     *  编辑
     * @param projectAcceptanceTO 项目验收情况信息
     * @return class ProjectAcceptanceBO
     */
    default ProjectAcceptanceBO editProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteProjectAcceptance(String id ) throws SerException {return;};

    /**
     * 搜索查询
     * @return class ProjectAcceptanceBO
     */
    default List<ProjectAcceptanceBO> searchListProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {return null;}

}