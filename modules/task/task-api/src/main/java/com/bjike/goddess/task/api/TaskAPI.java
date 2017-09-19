package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.to.TaskTO;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface TaskAPI {
    /**
     * 下发任务
     *
     * @param to
     * @throws SerException
     */
    default void issued(TaskTO to) throws SerException {

    }
}
