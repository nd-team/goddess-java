package com.bjike.goddess.abilitydisplay.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.abilitydisplay.dto.CompanyConDTO;
import com.bjike.goddess.abilitydisplay.entity.CompanyCon;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 公司联系人业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-05 09:00 ]
* @Description:	[ 公司联系人业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="abilitydisplaySerCache")
@Service
public class CompanyConSerImpl extends ServiceImpl<CompanyCon, CompanyConDTO> implements CompanyConSer { 

 }