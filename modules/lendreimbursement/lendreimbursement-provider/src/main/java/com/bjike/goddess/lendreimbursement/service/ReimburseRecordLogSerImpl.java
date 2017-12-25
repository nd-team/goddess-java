package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordLogDTO;
import com.bjike.goddess.lendreimbursement.entity.ReimburseRecordLog;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 报销记录日志表业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-04-11 05:48 ]
* @Description:	[ 报销记录日志表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="lendreimbursementSerCache")
@Service
public class ReimburseRecordLogSerImpl extends ServiceImpl<ReimburseRecordLog, ReimburseRecordLogDTO> implements ReimburseRecordLogSer { 

 }