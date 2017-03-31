package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.QualificationsGatherDTO;
import com.bjike.goddess.qualifications.entity.QualificationsGather;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资质办理信息采集业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 04:33 ]
* @Description:	[ 资质办理信息采集业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class QualificationsGatherSerImpl extends ServiceImpl<QualificationsGather, QualificationsGatherDTO> implements QualificationsGatherSer { 

 }