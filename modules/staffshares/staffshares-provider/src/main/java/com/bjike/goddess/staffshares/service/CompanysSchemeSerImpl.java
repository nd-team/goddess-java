package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffshares.dto.CompanysSchemeDTO;
import com.bjike.goddess.staffshares.entity.CompanysScheme;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 公司干股交易情况表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-11 04:38 ]
* @Description:	[ 公司干股交易情况表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="staffsharesSerCache")
@Service
public class CompanysSchemeSerImpl extends ServiceImpl<CompanysScheme, CompanysSchemeDTO> implements CompanysSchemeSer { 

 }