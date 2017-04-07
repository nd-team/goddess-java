package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.ProjectCarryAuditBO;
import com.bjike.goddess.projectprocing.dto.ProjectCarryAuditDTO;
import com.bjike.goddess.projectprocing.service.ProjectCarryAuditSer;
import com.bjike.goddess.projectprocing.to.ProjectCarryAuditTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目实施审核(针对没派工单情况)业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-31 06:41 ]
 * @Description: [ 项目实施审核(针对没派工单情况)业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectCarryAuditAuditApiImpl")
public class ProjectCarryAuditApiImpl implements ProjectCarryAuditAPI {

    @Autowired
    private ProjectCarryAuditSer projectCarryAuditSer;

    @Override
    public Long countProjectCarryAudit(ProjectCarryAuditDTO projectCarryAuditDTO) throws SerException {
        return projectCarryAuditSer.countProjectCarryAudit(projectCarryAuditDTO);
    }

    @Override
    public List<ProjectCarryAuditBO> listProjectCarryAudit(ProjectCarryAuditDTO projectCarryAuditDTO) throws SerException {
        return projectCarryAuditSer.listProjectCarryAudit(projectCarryAuditDTO);
    }

    @Override
    public ProjectCarryAuditBO addProjectCarryAudit(ProjectCarryAuditTO projectCarryAuditTO) throws SerException {
        return projectCarryAuditSer.addProjectCarryAudit(projectCarryAuditTO);
    }

    @Override
    public ProjectCarryAuditBO editProjectCarryAudit(ProjectCarryAuditTO projectCarryAuditTO) throws SerException {
        return projectCarryAuditSer.editProjectCarryAudit(projectCarryAuditTO);
    }

    @Override
    public void deleteProjectCarryAudit(String id) throws SerException {
        projectCarryAuditSer.deleteProjectCarryAudit(id);
    }

    @Override
    public List<ProjectCarryAuditBO> searchListProjectCarryAudit(ProjectCarryAuditDTO projectCarryAuditDTO) throws SerException {
        return projectCarryAuditSer.searchListProjectCarryAudit(projectCarryAuditDTO);
    }
}