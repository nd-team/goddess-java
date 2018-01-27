package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.taskallotment.dto.TaskNodeBaseDTO;
import com.bjike.goddess.taskallotment.entity.TaskNodeBase;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 计划任务节点集合业务实现
* @Author:			[ chenyang ]
* @Date:			[  2018-01-18 11:58 ]
* @Description:	[ 计划任务节点集合业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="taskallotmentSerCache")
@Service
public class TaskNodeBaseSerImpl extends ServiceImpl<TaskNodeBase, TaskNodeBaseDTO> implements TaskNodeBaseSer {

 }