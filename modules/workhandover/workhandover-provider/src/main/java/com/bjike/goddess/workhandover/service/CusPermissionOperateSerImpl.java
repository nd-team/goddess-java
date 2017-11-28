package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.workhandover.dto.CusPermissionOperateDTO;
import com.bjike.goddess.workhandover.entity.CusPermissionOperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 权限配置操作对象业务实现
* @Author:			[ chenyang ]
* @Date:			[  2017-11-14 09:43 ]
* @Description:	[ 权限配置操作对象业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="workhandoverSerCache")
@Service
public class CusPermissionOperateSerImpl extends ServiceImpl<CusPermissionOperate, CusPermissionOperateDTO> implements CusPermissionOperateSer { 

 }