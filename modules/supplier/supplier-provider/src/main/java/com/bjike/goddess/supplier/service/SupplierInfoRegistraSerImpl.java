package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.SupplierInfoRegistraDTO;
import com.bjike.goddess.supplier.entity.SupplierInfoRegistra;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 供应商信息管理业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 03:55 ]
* @Description:	[ 供应商信息管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class SupplierInfoRegistraSerImpl extends ServiceImpl<SupplierInfoRegistra, SupplierInfoRegistraDTO> implements SupplierInfoRegistraSer { 

 }