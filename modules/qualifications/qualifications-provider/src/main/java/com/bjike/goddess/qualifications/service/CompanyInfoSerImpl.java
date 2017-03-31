package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.CompanyInfoDTO;
import com.bjike.goddess.qualifications.entity.CompanyInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 公司基本信息业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 06:38 ]
* @Description:	[ 公司基本信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class CompanyInfoSerImpl extends ServiceImpl<CompanyInfo, CompanyInfoDTO> implements CompanyInfoSer { 

 }