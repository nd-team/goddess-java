package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.AuditMaterialDTO;
import com.bjike.goddess.qualifications.entity.AuditMaterial;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 审核资料业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 06:44 ]
* @Description:	[ 审核资料业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class AuditMaterialSerImpl extends ServiceImpl<AuditMaterial, AuditMaterialDTO> implements AuditMaterialSer { 

 }