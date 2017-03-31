package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.FinanceInfoDTO;
import com.bjike.goddess.qualifications.entity.FinanceInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 财务资料业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 06:42 ]
* @Description:	[ 财务资料业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class FinanceInfoSerImpl extends ServiceImpl<FinanceInfo, FinanceInfoDTO> implements FinanceInfoSer { 

 }