package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.FacilityInformationDTO;
import com.bjike.goddess.qualifications.entity.FacilityInformation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 设备信息业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 06:59 ]
* @Description:	[ 设备信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class FacilityInformationSerImpl extends ServiceImpl<FacilityInformation, FacilityInformationDTO> implements FacilityInformationSer { 

 }