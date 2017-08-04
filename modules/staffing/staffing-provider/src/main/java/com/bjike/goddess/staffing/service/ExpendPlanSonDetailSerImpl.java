package com.bjike.goddess.staffing.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffing.dto.ExpendPlanSonDetailDTO;
import com.bjike.goddess.staffing.entity.ExpendPlanSonDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 人工成本计划子表详细业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-31 02:24 ]
* @Description:	[ 人工成本计划子表详细业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="staffingSerCache")
@Service
public class ExpendPlanSonDetailSerImpl extends ServiceImpl<ExpendPlanSonDetail, ExpendPlanSonDetailDTO> implements ExpendPlanSonDetailSer {

 }