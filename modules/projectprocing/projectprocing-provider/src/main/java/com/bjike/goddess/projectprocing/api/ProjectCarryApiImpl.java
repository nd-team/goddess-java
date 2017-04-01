package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectCarryBO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryDTO;
import com.bjike.goddess.projectprocing.service.ProjectCarrySer;
import com.bjike.goddess.projectprocing.to.ProjectCarryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目实施业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 02:33 ]
 * @Description: [ 项目实施业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectCarryApiImpl")
public class ProjectCarryApiImpl implements ProjectCarryAPI {

    @Autowired
    private ProjectCarrySer projectCarrySer;

    @Override
    public Long countProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {
        return projectCarrySer.countProjectCarry(projectCarryDTO);
    }

    @Override
    public List<ProjectCarryBO> listProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {
        return projectCarrySer.listProjectCarry(projectCarryDTO);
    }

    @Override
    public ProjectCarryBO addProjectCarry(ProjectCarryTO projectCarryTO) throws SerException {
        return projectCarrySer.addProjectCarry(projectCarryTO);
    }

    @Override
    public ProjectCarryBO editProjectCarry(ProjectCarryTO projectCarryTO) throws SerException {
        return projectCarrySer.editProjectCarry(projectCarryTO);
    }

    @Override
    public void deleteProjectCarry(String id) throws SerException {
        projectCarrySer.deleteProjectCarry(id);
    }

    @Override
    public List<ProjectCarryBO> searchListProjectCarry(ProjectCarryDTO projectCarryDTO) throws SerException {
        return projectCarrySer.searchListProjectCarry(projectCarryDTO);
    }
}