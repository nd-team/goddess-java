package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectAcceptanceBO;
import com.bjike.goddess.projectprocing.dto.ProjectAcceptanceDTO;
import com.bjike.goddess.projectprocing.entity.ProjectAcceptance;
import com.bjike.goddess.projectprocing.service.ProjectAcceptanceSer;
import com.bjike.goddess.projectprocing.to.ProjectAcceptanceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目验收情况业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 03:54 ]
 * @Description: [ 项目验收情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectAcceptanceApiImpl")
public class ProjectAcceptanceApiImpl implements ProjectAcceptanceAPI {

    @Autowired
    private ProjectAcceptanceSer projectAcceptanceSer;

    @Override
    public Long countProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        return projectAcceptanceSer.countProjectAcceptance(projectAcceptanceDTO);
    }

    @Override
    public List<ProjectAcceptanceBO> listProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        return projectAcceptanceSer.listProjectAcceptance(projectAcceptanceDTO);
    }

    @Override
    public ProjectAcceptanceBO addProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException {
        return projectAcceptanceSer.addProjectAcceptance(projectAcceptanceTO);
    }

    @Override
    public ProjectAcceptanceBO editProjectAcceptance(ProjectAcceptanceTO projectAcceptanceTO) throws SerException {
        return projectAcceptanceSer.editProjectAcceptance(projectAcceptanceTO);
    }

    @Override
    public void deleteProjectAcceptance(String id) throws SerException {
        projectAcceptanceSer.deleteProjectAcceptance(id);
    }

    @Override
    public List<ProjectAcceptanceBO> searchListProjectAcceptance(ProjectAcceptanceDTO projectAcceptanceDTO) throws SerException {
        return projectAcceptanceSer.searchListProjectAcceptance(projectAcceptanceDTO);
    }
}