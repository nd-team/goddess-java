package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.businessproject.bo.BaseInfoManageBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectprocing.bo.ProjectAcceptanceBO;
import com.bjike.goddess.projectprocing.entity.ProjectAcceptance;
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
public interface ProjectAcceptanceSer extends Ser<ProjectAcceptance, ProjectAcceptanceDTO> {

    /**
     * 项目验收情况列表总条数
     *
     */
    default Long countProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        return null;
    }
    /**
     * 根据id获取项目验收情况列表
     * @return class ProjectAcceptanceBO
     */
    default ProjectAcceptanceBO getOneById(String id) throws SerException {return null;}

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


    /**
     * 获取合同外部名称和合同外部编号和合同内部名称和合同内部编号
     */
    List<BaseInfoManageBO> findManage() throws SerException;


}