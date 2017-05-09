package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectcost.dto.OtherExpensesDTO;
import com.bjike.goddess.projectcost.entity.OtherExpenses;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 其他费用业务实现
* @Author:			[ 邓钧仁 ]
* @Date:			[  2017-05-04 05:02 ]
* @Description:	[ 其他费用业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectcostSerCache")
@Service
public class OtherExpensesSerImpl extends ServiceImpl<OtherExpenses, OtherExpensesDTO> implements OtherExpensesSer { 

 }