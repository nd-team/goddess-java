package com.bjike.goddess.archive.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.entity.PersonnelQualification;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 人员资质业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 04:01 ]
* @Description:	[ 人员资质业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="archiveSerCache")
@Service
public class PersonnelQualificationSerImpl extends ServiceImpl<PersonnelQualification, PersonnelQualificationDTO> implements PersonnelQualificationSer { 

 }