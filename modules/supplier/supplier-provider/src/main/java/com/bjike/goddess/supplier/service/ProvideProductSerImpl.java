package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.ProvideProductDTO;
import com.bjike.goddess.supplier.entity.ProvideProduct;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 针对拟为我公司提供产品业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:06 ]
* @Description:	[ 针对拟为我公司提供产品业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class ProvideProductSerImpl extends ServiceImpl<ProvideProduct, ProvideProductDTO> implements ProvideProductSer { 

 }