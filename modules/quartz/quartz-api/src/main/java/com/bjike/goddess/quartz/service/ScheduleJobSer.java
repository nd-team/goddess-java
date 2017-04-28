package com.bjike.goddess.quartz.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.quartz.bo.ScheduleJobBO;
import com.bjike.goddess.quartz.dto.ScheduleJobDTO;
import com.bjike.goddess.quartz.entity.ScheduleJob;
import com.bjike.goddess.quartz.to.ScheduleJobTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
 * 任务调度业务接口
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:24 ]
 * @Description: [ 任务调度业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ScheduleJobSer extends Ser<ScheduleJob, ScheduleJobDTO> {

    /**
     * 添加任务调度
     *
     * @param scheduleJobTO
     * @return
     */
    default ScheduleJobBO add(ScheduleJobTO scheduleJobTO) throws SerException {
        return null;
    }

    /**
     * 编辑任务调度
     *
     * @param scheduleJobTO
     */
    default void edit(ScheduleJobTO scheduleJobTO) throws SerException {

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

    /**
     * 获取有效的调度任务
     *
     * @return
     * @throws SerException
     */
    default List<ScheduleJob> findScheduleJobs() throws SerException {
        return null;
    }

    /**
     * 通过组id获取定制任务
     *
     * @return
     * @throws SerException
     */
    default List<ScheduleJob> findByGroupId(String id) throws SerException {
        return null;
    }

}