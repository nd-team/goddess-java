package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaFinanceAuditLogDTO;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaFinanceAuditLog;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 老系统的财务审核日志业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-11-17 11:43 ]
* @Description:	[ 老系统的财务审核日志业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="lendreimbursementSerCache")
@Service
public class AjoaFinanceAuditLogSerImpl extends ServiceImpl<AjoaFinanceAuditLog, AjoaFinanceAuditLogDTO> implements AjoaFinanceAuditLogSer { 

 }