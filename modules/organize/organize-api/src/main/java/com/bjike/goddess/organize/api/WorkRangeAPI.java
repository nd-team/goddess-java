package com.bjike.goddess.organize.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeFlatTO;
import com.bjike.goddess.organize.to.WorkRangeTO;

import java.util.List;

/**
 * 对外工作范围信息设置业务接口
 *
 * @Author: [dengjunren]
 * @Date: [2017-03-08 17:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface WorkRangeAPI {

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

    /**
     * 删除
     *
     * @param id 工作范围数据id
     * @return
     * @throws SerException
     */
    default WorkRangeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 工作范围设置数据传输
     * @return
     * @throws SerException
     */
    default List<WorkRangeBO> maps(WorkRangeDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id查询工作范围数据
     *
     * @param id 工作范围数据id
     * @return
     * @throws SerException
     */
    default WorkRangeBO findById(String id) throws SerException {
        return null;
    }


    /**
     * 查询方向
     *
     * @return
     * @throws SerException
     */
    default List<DirectionBO> findDirection() throws SerException {
        return null;
    }

    /**
     * 查询科目
     *
     * @return
     * @throws SerException
     */
    default List<ProjectBO> findProject() throws SerException {
        return null;
    }

    /**
     * 查询分类
     *
     * @return
     * @throws SerException
     */
    default List<ClassifyBO> findClassify() throws SerException {
        return null;
    }

    /**
     * 关闭
     *
     * @param id 部门工作范围数据id
     * @return
     * @throws SerException
     */
    default WorkRangeBO close(String id) throws SerException {
        return null;
    }

    /**
     * 开启
     *
     * @param id 部门工作范围数据id
     * @return
     * @throws SerException
     */
    default WorkRangeBO open(String id) throws SerException {
        return null;
    }

    /**
     * 获取工作范围选项
     *
     * @return
     * @throws SerException
     */
    default List<OpinionBO> findThawOpinion() throws SerException {
        return null;
    }

    /**
     * 获取所有工作范围选项
     *
     * @return
     * @throws SerException
     */
    default List<WorkRangeBO> findByStatus(Status status) throws SerException {
        return null;
    }

    /*
     * 获取未冻结的工作范围
     */
    default List<String> findWorkScope() throws SerException {
        return null;
    }

    /**
     * 平台列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<WorkRangeFlatBO> getFlatList(WorkRangeDTO dto) throws SerException {
        return null;
    }

    /**
     * 平台添加
     *
     * @param to
     * @throws SerException
     */
    default void flatAdd(WorkRangeFlatTO to) throws SerException {
        return;
    }

    /**
     * 根据平台业务方向分类获取平台对象
     *
     * @param direction
     * @return
     */
    default List<WorkRangeFlatBO> findByFlatDirection(String direction) throws SerException {
        return null;
    }

    /**
     * 平台删除
     *
     * @param direction
     * @throws SerException
     */
    default void faltDelete(String direction) throws SerException {
        return;
    }

    /**
     * 平台编辑
     *
     * @param to
     * @throws SerException
     */
    default void flatUpdate(WorkRangeFlatTO to) throws SerException {
        return;
    }

    /**
     * 平台关闭
     *
     * @param direction
     * @throws SerException
     */
    default void flatClose(String direction) throws SerException {
        return;
    }

    /**
     * 平台重启
     *
     * @param direction
     * @throws SerException
     */
    default void flatOpen(String direction) throws SerException {
        return;
    }
}
