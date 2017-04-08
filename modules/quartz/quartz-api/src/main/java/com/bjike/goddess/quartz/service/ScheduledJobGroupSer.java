package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.quartz.bo.ScheduledJobGroupBO;
import com.bjike.goddess.quartz.dto.ScheduledJobGroupDTO;
import com.bjike.goddess.quartz.entity.ScheduledJobGroup;
import com.bjike.goddess.quartz.to.ScheduledJobGroupTO;

/**
 * 任务调度组业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ScheduledJobGroupSer extends Ser<ScheduledJobGroup, ScheduledJobGroupDTO> {

    /**
     * 添加任务调度组
     *
     * @param jobGroupTO
     * @return
     */
    default ScheduledJobGroupBO add(ScheduledJobGroupTO jobGroupTO) throws SerException {
        return null;
    }

    /**
     * 编辑任务调度组
     *
     * @param jobGroupTO
     */
    default void edit(ScheduledJobGroupTO jobGroupTO) throws SerException {

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