package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashFormulaDataDTO;
import com.bjike.goddess.reportmanagement.entity.CashFormulaData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 补充资料公式业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-22 03:08 ]
* @Description:	[ 补充资料公式业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class CashFormulaDataSerImpl extends ServiceImpl<CashFormulaData, CashFormulaDataDTO> implements CashFormulaDataSer { 

 }