package com.bjike.goddess.quartz.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.quartz.bo.ScheduleJobBO;
import com.bjike.goddess.quartz.dto.ScheduleJobDTO;
import com.bjike.goddess.quartz.to.ScheduleJobTO;

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
public interface ScheduleJobAPI {

    /**
     * 任务调度列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<ScheduleJobBO> list(ScheduleJobDTO dto) throws SerException {
        return null;
    }

    /**
     * 任务调度数据量
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long count(ScheduleJobDTO dto) throws SerException {
        return null;
    }

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
     * 通过id查询数据
     *
     * @param id
     * @return
     */
    default ScheduleJobBO findById(String id) throws SerException {
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

}