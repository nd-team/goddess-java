package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectSituationBO;
import com.bjike.goddess.projectprocing.dto.ProjectSituationDTO;
import com.bjike.goddess.projectprocing.entity.ProjectSituation;
import com.bjike.goddess.projectprocing.service.ProjectSituationSer;
import com.bjike.goddess.projectprocing.to.ProjectSituationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目情况业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:16 ]
 * @Description: [ 项目情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectSituationApiImpl")
public class ProjectSituationApiImpl implements ProjectSituationAPI {

    @Autowired
    private ProjectSituationSer projectSituationSer;

    @Override
    public Long countProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        return projectSituationSer.countProjectSituation(projectSituationDTO);
    }

    @Override
    public List<ProjectSituationBO> listProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        return projectSituationSer.listProjectSituation(projectSituationDTO);
    }

    @Override
    public ProjectSituationBO addProjectSituation(ProjectSituationTO projectSituationTO) throws SerException {
        return projectSituationSer.addProjectSituation(projectSituationTO);
    }

    @Override
    public ProjectSituationBO editProjectSituation(ProjectSituationTO projectSituationTO) throws SerException {
        return projectSituationSer.editProjectSituation(projectSituationTO);
    }

    @Override
    public void deleteProjectSituation(String id) throws SerException {
        projectSituationSer.deleteProjectSituation(id);
    }

    @Override
    public List<ProjectSituationBO> searchListProjectSituation(ProjectSituationDTO projectSituationDTO) throws SerException {
        return projectSituationSer.searchListProjectSituation(projectSituationDTO);
    }
}