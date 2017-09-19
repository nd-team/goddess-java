package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ScheduleAPI {
    default String detail(String projectId) throws SerException {
        return null;
    }

}
