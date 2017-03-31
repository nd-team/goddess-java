package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.entity.QualificationsInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资质信息管理业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 04:15 ]
* @Description:	[ 资质信息管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class QualificationsInfoSerImpl extends ServiceImpl<QualificationsInfo, QualificationsInfoDTO> implements QualificationsInfoSer { 

 }