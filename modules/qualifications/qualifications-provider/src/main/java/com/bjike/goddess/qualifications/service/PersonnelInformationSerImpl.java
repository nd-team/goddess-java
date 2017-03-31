package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.qualifications.dto.PersonnelInformationDTO;
import com.bjike.goddess.qualifications.entity.PersonnelInformation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 人员信息资料业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 07:24 ]
* @Description:	[ 人员信息资料业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="qualificationsSerCache")
@Service
public class PersonnelInformationSerImpl extends ServiceImpl<PersonnelInformation, PersonnelInformationDTO> implements PersonnelInformationSer { 

 }