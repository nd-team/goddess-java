package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.ProfitFormulaDTO;
import com.bjike.goddess.reportmanagement.entity.ProfitFormula;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 利润增减率分析和变动分析业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-01 05:07 ]
* @Description:	[ 利润增减率分析和变动分析业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class ProfitFormulaSerImpl extends ServiceImpl<ProfitFormula, ProfitFormulaDTO> implements ProfitFormulaSer { 

 }