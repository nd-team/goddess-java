package com.bjike.goddess.quartz.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.quartz.dto.ScheduleJobGroupDTO;
import com.bjike.goddess.quartz.entity.ScheduleJobGroup;

/**
 * 任务调度组持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ liguiqin ]
 * @Date: [ 2017-04-06 02:26 ]
 * @Description: [ 任务调度组持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ScheduleJobGroupRep extends JpaRep<ScheduleJobGroup, ScheduleJobGroupDTO> {

}