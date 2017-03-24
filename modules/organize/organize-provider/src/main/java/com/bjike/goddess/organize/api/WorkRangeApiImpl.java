package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.DepartmentWorkRangeBO;
import com.bjike.goddess.organize.bo.WorkRangeBO;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.service.WorkRangeSer;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作范围信息设置业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service("workRangeApiImpl")
public class WorkRangeApiImpl implements WorkRangeAPI {

    @Autowired
    private WorkRangeSer workRangeSer;

    @Override
    public List<DepartmentWorkRangeBO> findDepartmentWorkRangeView(String department_id, WorkRangeDTO dto) throws SerException {
        return workRangeSer.findDepartmentWorkRangeView(department_id, dto);
    }

    @Override
    public List<WorkRangeBO> findByDepartment(String departmentId) throws SerException {
        return workRangeSer.findByDepartment(departmentId);
    }

    @Override
    public List<DepartmentDetailBO> findByRange(String rangeId) throws SerException {
        return workRangeSer.findByRange(rangeId);
    }

    @Override
    public void departmentAddRange(DepartmentWorkRangeTO to) throws SerException {
        workRangeSer.departmentAddRange(to);
    }

    @Override
    public List<WorkRangeBO> findByDirection(String direction) throws SerException {
        return workRangeSer.findByDirection(direction);
    }

    @Override
    public List<WorkRangeBO> findByDirectionProject(String direction, String project) throws SerException {
        return workRangeSer.findByDirectionProject(direction, project);
    }

    @Override
    public List<WorkRangeBO> findByProject(String project) throws SerException {
        return workRangeSer.findByProject(project);
    }

    @Override
    public WorkRangeBO save(WorkRangeTO to) throws SerException {
        return workRangeSer.save(to);
    }

    @Override
    public WorkRangeBO update(WorkRangeTO to) throws SerException {
        return workRangeSer.update(to);
    }
}
