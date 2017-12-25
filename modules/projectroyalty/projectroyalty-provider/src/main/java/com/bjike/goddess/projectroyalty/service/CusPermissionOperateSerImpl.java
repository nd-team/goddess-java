package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.projectroyalty.dto.CusPermissionOperateDTO;
import com.bjike.goddess.projectroyalty.entity.CusPermissionOperate;
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
@CacheConfig(cacheNames ="projectroyaltySerCache")
@Service
public class CusPermissionOperateSerImpl extends ServiceImpl<CusPermissionOperate, CusPermissionOperateDTO> implements CusPermissionOperateSer {

 }