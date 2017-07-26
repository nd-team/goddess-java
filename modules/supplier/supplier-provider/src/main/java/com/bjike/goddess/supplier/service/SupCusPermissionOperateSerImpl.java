package com.bjike.goddess.supplier.service;

import com.bjike.goddess.supplier.dto.SupCusPermissionOperateDTO;
import com.bjike.goddess.supplier.entity.SupCusPermissionOperate;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 客户权限配置操作对象业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-05-25 02:12 ]
* @Description:	[ 客户权限配置操作对象业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class SupCusPermissionOperateSerImpl extends ServiceImpl<SupCusPermissionOperate, SupCusPermissionOperateDTO> implements SupCusPermissionOperateSer {

 }