package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaMattersDTO;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaMatters;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 老系统的摘要业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-11-17 10:35 ]
* @Description:	[ 老系统的摘要业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="lendreimbursementSerCache")
@Service
public class AjoaMattersSerImpl extends ServiceImpl<AjoaMatters, AjoaMattersDTO> implements AjoaMattersSer { 

 }