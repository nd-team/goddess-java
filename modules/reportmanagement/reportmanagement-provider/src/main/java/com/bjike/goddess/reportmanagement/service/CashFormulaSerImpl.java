package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashFormulaDTO;
import com.bjike.goddess.reportmanagement.entity.CashFormula;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 项目公式业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-21 05:09 ]
* @Description:	[ 项目公式业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class CashFormulaSerImpl extends ServiceImpl<CashFormula, CashFormulaDTO> implements CashFormulaSer { 

 }