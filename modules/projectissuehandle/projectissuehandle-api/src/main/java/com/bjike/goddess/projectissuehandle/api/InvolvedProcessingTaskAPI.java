package com.bjike.goddess.projectissuehandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.to.GuidePermissionTO;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;

import java.util.List;

/**
 * 参与处理人员的任务分配业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-23 05:02 ]
 * @Description: [ 参与处理人员的任务分配业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InvolvedProcessingTaskAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 参与处理人员的任务分配列表总条数
     */
    default Long countInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        return null;
    }

    /**
     * 一个参与处理人员的任务分配
     *
     * @return class InvolvedProcessingTaskBO
     */
    default InvolvedProcessingTaskBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 参与处理人员的任务分配
     *
     * @param involvedProcessingTaskDTO 参与处理人员的任务分配dto
     * @return class involvedProcessingTaskBO
     * @throws SerException
     */
    default List<InvolvedProcessingTaskBO> findListInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        return null;
    }

    /**
     * 添加参与处理人员的任务分配
     *
     * @param involvedProcessingTaskTO 参与处理人员的任务分配数据to
     * @throws SerException
     */
    default InvolvedProcessingTaskBO insertInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        return null;
    }

    /**
     * 编辑参与处理人员的任务分配
     *
     * @param involvedProcessingTaskTO 参与处理人员的任务分配数据to
     * @return class involvedProcessingTaskBO
     * @throws SerException
     */
    default InvolvedProcessingTaskBO editInvolvedProcessingTask(InvolvedProcessingTaskTO involvedProcessingTaskTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除参与处理人员的任务分配
     *
     * @param id
     * @throws SerException
     */
    default void removeInvolvedProcessingTask(String id) throws SerException {

    }

    /**
     * 搜索
     *
     * @throws SerException
     */
    default List<InvolvedProcessingTaskBO> searchInvolvedProcessingTask(InvolvedProcessingTaskDTO involvedProcessingTaskDTO) throws SerException {
        return null;
    }
    /**
     * 导出Excel
     *
     * @param dto
     * @throws SerException
     */
    byte[] exportExcel(InvolvedProcessingTaskDTO dto) throws SerException;

    /**
     * 获取内部项目名称
     *
     * @return class String
     */
    default List<String> getName() throws SerException {
        return null;
    }
    /**
     * 获取处理人员
     *
     * @return class String
     */
    default List<String> getHandler() throws SerException {
        return null;
    }
}