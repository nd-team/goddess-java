package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.QualificationsHandleDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资质办理管理业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 07:15 ]
* @Description:	[ 资质办理管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class QualificationsHandleSerImpl extends ServiceImpl<QualificationsHandle, QualificationsHandleDTO> implements QualificationsHandleSer { 

 }