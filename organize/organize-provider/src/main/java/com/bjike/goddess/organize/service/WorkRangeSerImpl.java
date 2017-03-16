package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.DepartmentWorkRangeBO;
import com.bjike.goddess.organize.bo.WorkRangeBO;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.entity.WorkRange;
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
@Service
public class WorkRangeSerImpl extends ServiceImpl<WorkRange, WorkRangeDTO> implements WorkRangeSer {

    @Override
    public List<DepartmentWorkRangeBO> findDepartmentWorkRangeView(WorkRangeDTO dto) throws SerException {
        return null;
    }

    @Override
    public List<WorkRangeBO> findByDepartment(String departmentId) throws SerException {
        return null;
    }

    @Override
    public List<DepartmentDetailBO> findByRange(String rangeId) throws SerException {
        return null;
    }

    @Override
    public void departmentAddRange(String departmentId, String[] rangeIds) throws SerException {

    }

    @Override
    public List<WorkRangeBO> findByDirection(String direction) throws SerException {
        return null;
    }

    @Override
    public List<WorkRangeBO> findByDirectionProject(String direction, String project) throws SerException {
        return null;
    }

    @Override
    public List<WorkRangeBO> findByProject(String project) throws SerException {
        return null;
    }
}
