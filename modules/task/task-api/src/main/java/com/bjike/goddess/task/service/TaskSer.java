package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.dto.TaskDTO;
import com.bjike.goddess.task.entity.Task;
import com.bjike.goddess.task.to.TaskTO;

/**
 * 任务业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-19 10:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface TaskSer extends Ser<Task, TaskDTO> {
    /**
     * 下发任务
     *
     * @param to
     * @throws SerException
     */
    default void issued(TaskTO to) throws SerException {

    }
}
