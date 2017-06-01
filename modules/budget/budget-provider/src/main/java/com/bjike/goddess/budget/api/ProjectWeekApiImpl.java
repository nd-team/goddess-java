package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.ProjectWeekBO;
import com.bjike.goddess.budget.bo.ProjectWeekCountBO;
import com.bjike.goddess.budget.dto.ProjectWeekDTO;
import com.bjike.goddess.budget.service.ProjectWeekSer;
import com.bjike.goddess.budget.to.ProjectWeekTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目收入周业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:58 ]
 * @Description: [ 项目收入周业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectWeekApiImpl")
public class ProjectWeekApiImpl implements ProjectWeekAPI {
    @Autowired
    private ProjectWeekSer projectWeekSer;

    @Override
    public ProjectWeekBO save(ProjectWeekTO to) throws SerException {
        return projectWeekSer.save(to);
    }

    @Override
    public void edit(ProjectWeekTO to) throws SerException {
        projectWeekSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectWeekSer.delete(id);
    }

    @Override
    public List<ProjectWeekBO> list(ProjectWeekDTO dto) throws SerException {
        return projectWeekSer.list(dto);
    }

    @Override
    public ProjectWeekBO findByID(String id) throws SerException {
        return projectWeekSer.findByID(id);
    }

    @Override
    public List<ProjectWeekCountBO> count() throws SerException {
        return projectWeekSer.count();
    }

    @Override
    public List<ProjectWeekCountBO> conditionsCount(String[] projects) throws SerException {
        return projectWeekSer.conditionsCount(projects);
    }

    @Override
    public Long countNum(ProjectWeekDTO dto) throws SerException {
        return projectWeekSer.countNum(dto);
    }

    @Override
    public List<String> findAllProjects() throws SerException {
        return projectWeekSer.findAllProjects();
    }
}