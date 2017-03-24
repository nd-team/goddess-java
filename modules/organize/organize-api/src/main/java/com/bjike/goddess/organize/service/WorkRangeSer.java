package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.DepartmentWorkRangeBO;
import com.bjike.goddess.organize.bo.WorkRangeBO;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.entity.WorkRange;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeTO;

import java.util.List;

/**
 * 工作范围信息设置业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface WorkRangeSer extends Ser<WorkRange, WorkRangeDTO> {

    /**
     * 查询部门工作范围信息详细
     *
     * @param department_id 部门ID
     * @param dto
     * @return
     * @throws SerException
     */
    default List<DepartmentWorkRangeBO> findDepartmentWorkRangeView(String department_id, WorkRangeDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据部门详细ID查询工作范围信息
     *
     * @param departmentId 部门详细ID
     * @return
     * @throws SerException
     */
    default List<WorkRangeBO> findByDepartment(String departmentId) throws SerException {
        return null;
    }

    /**
     * 根据工作范围ID查询部门详细信息
     *
     * @param rangeId 工作范围ID
     * @return
     * @throws SerException
     */
    default List<DepartmentDetailBO> findByRange(String rangeId) throws SerException {
        return null;
    }

    /**
     * 部门详细增加工作范围
     *
     * @param to
     * @throws SerException
     */
    void departmentAddRange(DepartmentWorkRangeTO to) throws SerException;

    /**
     * 根据方向查询工作范围
     *
     * @param direction 方向
     * @return
     * @throws SerException
     */
    default List<WorkRangeBO> findByDirection(String direction) throws SerException {
        return null;
    }

    /**
     * 根据方向和科目查询工作范围
     *
     * @param direction 方向
     * @param project   科目
     * @return
     * @throws SerException
     */
    default List<WorkRangeBO> findByDirectionProject(String direction, String project) throws SerException {
        return null;
    }

    /**
     * 根据科目查询工作范围
     *
     * @param project 科目
     * @return
     * @throws SerException
     */
    default List<WorkRangeBO> findByProject(String project) throws SerException {
        return null;
    }

    /**
     * 保存工作范围
     *
     * @param to
     * @return
     */
    default WorkRangeBO save(WorkRangeTO to) throws SerException {
        return null;
    }

    /**
     * 修改工作范围
     *
     * @param to
     * @return
     */
    default WorkRangeBO update(WorkRangeTO to) throws SerException {
        return null;
    }
}
