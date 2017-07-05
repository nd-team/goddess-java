package com.bjike.goddess.quartz.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.bo.ScheduleJobGroupBO;
import com.bjike.goddess.quartz.dto.ScheduleJobGroupDTO;
import com.bjike.goddess.quartz.to.ScheduleJobGroupTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * 任务调度组业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ScheduleJobGroupAPI {

    default List<ScheduleJobGroupBO> list(ScheduleJobGroupDTO dto) throws SerException {
        return null;
    }
    default Long  count(ScheduleJobGroupDTO dto) throws SerException {
        return null;
    }
    /**
     * 添加任务调度组
     *
     * @param jobGroupTO
     * @return
     */
    default ScheduleJobGroupBO add(TransactionContext context,ScheduleJobGroupTO jobGroupTO) throws SerException {
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