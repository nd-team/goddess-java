package com.bjike.goddess.quartz.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.bo.ScheduledJobBO;
import com.bjike.goddess.quartz.to.ScheduledJobTO;

/**
 * 任务调度业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:24 ]
 * @Description: [ 任务调度业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ScheduledJobAPI {

    /**
     * 添加任务调度
     *
     * @param scheduledJobTO
     * @return
     */
    default ScheduledJobBO add(ScheduledJobTO scheduledJobTO) throws SerException {
        return null;
    }

    /**
     * 编辑任务调度
     *
     * @param scheduledJobTO
     */
    default void edit(ScheduledJobTO scheduledJobTO) throws SerException {

    }

    /**
     * 删除任务调度
     *
     * @param id
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 启用关闭任务调度
     *
     * @param enable
     */
    default void enable(String id, boolean enable) throws SerException {

    }

}