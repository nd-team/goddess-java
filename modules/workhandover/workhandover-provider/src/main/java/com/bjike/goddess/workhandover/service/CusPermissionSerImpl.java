package com.bjike.goddess.workhandover.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.workhandover.dto.CusPermissionDTO;
import com.bjike.goddess.workhandover.entity.CusPermission;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 权限配置业务实现
* @Author:			[ chenyang ]
* @Date:			[  2017-11-14 09:33 ]
* @Description:	[ 权限配置业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="workhandoverSerCache")
@Service
public class CusPermissionSerImpl extends ServiceImpl<CusPermission, CusPermissionDTO> implements CusPermissionSer { 

 }