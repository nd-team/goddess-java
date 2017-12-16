package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.QualificationLevelSetDTO;
import com.bjike.goddess.supplier.entity.QualificationLevelSet;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资质等级设置业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:08 ]
* @Description:	[ 资质等级设置业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class QualificationLevelSetSerImpl extends ServiceImpl<QualificationLevelSet, QualificationLevelSetDTO> implements QualificationLevelSetSer { 

 }