package com.bjike.goddess.projectissuehandle.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectissuehandle.bo.InvolvedProcessingTaskBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemAcceptBO;
import com.bjike.goddess.projectissuehandle.bo.ProblemHandlingResultBO;
import com.bjike.goddess.projectissuehandle.dto.ProblemAcceptDTO;
import com.bjike.goddess.projectissuehandle.entity.InvolvedProcessingTask;
import com.bjike.goddess.projectissuehandle.dto.InvolvedProcessingTaskDTO;
import com.bjike.goddess.projectissuehandle.to.InvolvedProcessingTaskTO;
import com.bjike.goddess.projectissuehandle.to.ProblemAcceptTO;

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
public interface InvolvedProcessingTaskSer extends Ser<InvolvedProcessingTask, InvolvedProcessingTaskDTO> {
    /**
     * 获取参与处理人员的任务分配
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
     * 导出
     *
     * @throws SerException
     */
    default String exportExcel(String internalProjectName, String handler) throws SerException {
        return null;
    }

    /**
     * 搜索
     *
     * @throws SerException
     */
    default InvolvedProcessingTaskBO searchInvolvedProcessingTask(String internalProjectName, String handler) throws SerException {
        return null;
    }

    /**
     * 上传
     */
    default void upload() throws SerException {
        return;

    }

}