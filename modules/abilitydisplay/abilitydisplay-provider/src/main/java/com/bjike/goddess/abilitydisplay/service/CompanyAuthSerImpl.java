package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.abilitydisplay.dto.CompanyAuthDTO;
import com.bjike.goddess.abilitydisplay.entity.CompanyAuth;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 公司认证业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-08 05:31 ]
* @Description:	[ 公司认证业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="abilitydisplaySerCache")
@Service
public class CompanyAuthSerImpl extends ServiceImpl<CompanyAuth, CompanyAuthDTO> implements CompanyAuthSer { 

 }