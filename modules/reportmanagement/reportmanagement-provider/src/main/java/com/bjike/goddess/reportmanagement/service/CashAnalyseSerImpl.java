package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashAnalyseDTO;
import com.bjike.goddess.reportmanagement.entity.CashAnalyse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 现金流量分析业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-23 11:43 ]
* @Description:	[ 现金流量分析业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class CashAnalyseSerImpl extends ServiceImpl<CashAnalyse, CashAnalyseDTO> implements CashAnalyseSer { 

 }