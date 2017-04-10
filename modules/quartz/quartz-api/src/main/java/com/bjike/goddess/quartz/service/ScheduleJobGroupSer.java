package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.quartz.bo.ScheduleJobGroupBO;
import com.bjike.goddess.quartz.dto.ScheduleJobGroupDTO;
import com.bjike.goddess.quartz.entity.ScheduleJobGroup;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;

/**
 * 任务调度组业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ScheduleJobGroupSer extends Ser<ScheduleJobGroup, ScheduleJobGroupDTO> {

    /**
     * 添加任务调度组
     *
     * @param jobGroupTO
     * @return
     */
    default ScheduleJobGroupBO add(ScheduleJobGroupTO jobGroupTO) throws SerException {
        return null;
    }

    /**
     * 编辑任务调度组
     *
     * @param jobGroupTO
     */
    default void edit(ScheduleJobGroupTO jobGroupTO) throws SerException {

    }

    /**
     * 删除任务调度组
     *
     * @param id
     */
    default void delete(String id) throws SerException {

    }

    /**
     * 启用关闭任务调度组
     *
     * @param enable
     */
    default void enable(String id, boolean enable) throws SerException {

    }

}