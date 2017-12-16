package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.SupplierTypeSetDTO;
import com.bjike.goddess.supplier.entity.SupplierTypeSet;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 供应商类型设置业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:07 ]
* @Description:	[ 供应商类型设置业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class SupplierTypeSetSerImpl extends ServiceImpl<SupplierTypeSet, SupplierTypeSetDTO> implements SupplierTypeSetSer { 

 }