package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.EnterpriseQualificaDTO;
import com.bjike.goddess.supplier.entity.EnterpriseQualifica;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 企业资质业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 03:58 ]
* @Description:	[ 企业资质业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class EnterpriseQualificaSerImpl extends ServiceImpl<EnterpriseQualifica, EnterpriseQualificaDTO> implements EnterpriseQualificaSer { 

 }