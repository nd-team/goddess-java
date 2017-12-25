package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CusPermissionOperateDTO;
import com.bjike.goddess.reportmanagement.entity.CusPermissionOperate;
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
@CacheConfig(cacheNames ="customerSerCache")
@Service
public class CusPermissionOperateSerImpl extends ServiceImpl<CusPermissionOperate, CusPermissionOperateDTO> implements CusPermissionOperateSer {

 }