package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.headcount.dto.ConfigPlanDTO;
import com.bjike.goddess.headcount.entity.ConfigPlan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 部门人数配置(计划)业务实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:18:34.795 ]
* @Description:	[ 部门人数配置(计划)业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="configurationPlanSerCache")
@Service
public class ConfigurationPlanSerImpl extends ServiceImpl<ConfigPlan, ConfigPlanDTO> implements ConfigPlanSer {

 }