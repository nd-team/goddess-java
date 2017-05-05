package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.ProjectMonthBO;
import com.bjike.goddess.budget.bo.ProjectMonthCountBO;
import com.bjike.goddess.budget.bo.ProjectWeekBO;
import com.bjike.goddess.budget.dto.ProjectMonthDTO;
import com.bjike.goddess.budget.service.ProjectMonthSer;
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
    public ProjectMonthBO findByID(String id) throws SerException {
        return projectMonthSer.findByID(id);
    }

    @Override
    public List<ProjectMonthCountBO> count() throws SerException {
        return projectMonthSer.count();
    }

    @Override
    public List<ProjectMonthCountBO> conditionsCount(String[] projects) throws SerException {
        return projectMonthSer.conditionsCount(projects);
    }

    @Override
    public List<ProjectWeekBO> findDetail(String id) throws SerException {
        return projectMonthSer.findDetail(id);
    }
}