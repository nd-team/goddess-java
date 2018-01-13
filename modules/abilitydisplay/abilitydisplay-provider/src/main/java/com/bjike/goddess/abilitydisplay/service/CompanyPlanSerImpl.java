package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.abilitydisplay.dto.CompanyPlanDTO;
import com.bjike.goddess.abilitydisplay.entity.CompanyPlan;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 公司规划业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 02:24 ]
* @Description:	[ 公司规划业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="abilitydisplaySerCache")
@Service
public class CompanyPlanSerImpl extends ServiceImpl<CompanyPlan, CompanyPlanDTO> implements CompanyPlanSer { 

 }