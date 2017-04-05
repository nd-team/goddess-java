package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectSituationBO;
import com.bjike.goddess.projectprocing.dto.ProjectSituationDTO;
import com.bjike.goddess.projectprocing.to.ProjectSituationTO;

import java.util.List;

/**
 * 项目情况业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ProjectSituationAPI {

    /**
     * 项目情况列表总条数
     *
     */
    default Long countProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        return null;
    }
    /**
     * 项目情况列表
     * @return class ProjectSituationBO
     */
    default List<ProjectSituationBO> listProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {return null;}
    /**
     *  添加
     * @param projectSituationTO 项目情况信息
     * @return class ProjectSituationBO
     */
    default ProjectSituationBO addProjectSituation(ProjectSituationTO projectSituationTO) throws SerException { return null;}

    /**
     *  编辑
     * @param projectSituationTO 项目情况信息
     * @return class ProjectSituationBO
     */
    default ProjectSituationBO editProjectSituation(ProjectSituationTO projectSituationTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteProjectSituation(String id ) throws SerException {return;};

    /**
     * 搜索查询
     * @return class ProjectSituationBO
     */
    default List<ProjectSituationBO> searchListProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {return null;}

}