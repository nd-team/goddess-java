package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.*;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.service.ProjectMonthSer;
import com.bjike.goddess.budget.to.GuidePermissionTO;
import com.bjike.goddess.budget.to.ProjectMonthTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目收入月业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 03:59 ]
 * @Description: [ 项目收入月业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectMonthApiImpl")
public class ProjectMonthApiImpl implements ProjectMonthAPI {
    @Autowired
    private ProjectMonthSer projectMonthSer;

    @Override
    public ProjectMonthBO save(ProjectMonthTO to) throws SerException {
        return projectMonthSer.save(to);
    }

    @Override
    public void edit(ProjectMonthTO to) throws SerException {
        projectMonthSer.edit(to);
    }

    @Override
    public void deleteAll() throws SerException {
        projectMonthSer.deleteAll();
    }

    @Override
    public List<ProjectMonthBO> list(ProjectMonthDTO dto) throws SerException {
        return projectMonthSer.list(dto);
    }

    @Override
    public List<ProjectWeekListBO> listProjectMonth(ProjectMonthDTO dto) throws SerException {
        return projectMonthSer.listProjectMonth(dto);
    }

    @Override
    public ProjectMonthBO findByID(String id) throws SerException {
        return projectMonthSer.findByID(id);
    }

    @Override
    public List<ProjectMonthCountBO> count() throws SerException {
        return projectMonthSer.count();
    }

    @Override
    public List<ProjectMonthCountBO> conditionsCount(ProjectMonthDTO dto1) throws SerException {
        return projectMonthSer.conditionsCount(dto1);
    }

    @Override
    public List<ProjectWeekBO> findDetail(String id) throws SerException {
        return projectMonthSer.findDetail(id);
    }

    @Override
    public Long countNum(ProjectMonthDTO dto) throws SerException {
        return projectMonthSer.countNum(dto);
    }

    @Override
    public List<String> findAllProjects() throws SerException {
        return projectMonthSer.findAllProjects();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return projectMonthSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectMonthSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ProjectMonthCountBO> collect(ProjectMonthDTO dto) throws SerException {
        return projectMonthSer.collect(dto);
    }

    @Override
    public OptionBO figureShow() throws SerException {
        return projectMonthSer.figureShow();
    }
}