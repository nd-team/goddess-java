package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.SupPermissionOperateDTO;
import com.bjike.goddess.supplier.entity.SupPermissionOperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 供应商权限配置操作对象业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-27 11:22 ]
* @Description:	[ 供应商权限配置操作对象业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class SupPermissionOperateSerImpl extends ServiceImpl<SupPermissionOperate, SupPermissionOperateDTO> implements SupPermissionOperateSer { 

 }