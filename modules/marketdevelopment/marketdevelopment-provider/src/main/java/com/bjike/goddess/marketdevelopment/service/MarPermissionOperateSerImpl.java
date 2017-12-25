package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.MarPermissionOperateDTO;
import com.bjike.goddess.marketdevelopment.entity.MarPermissionOperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 市场计划进度管理权限配置操作对象业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-31 11:36 ]
* @Description:	[ 市场计划进度管理权限配置操作对象业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class MarPermissionOperateSerImpl extends ServiceImpl<MarPermissionOperate, MarPermissionOperateDTO> implements MarPermissionOperateSer { 

 }