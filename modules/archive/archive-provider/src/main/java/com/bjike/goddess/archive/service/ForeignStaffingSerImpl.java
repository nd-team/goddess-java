package com.bjike.goddess.archive.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.entity.ForeignStaffing;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 对外人员信息业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 03:09 ]
* @Description:	[ 对外人员信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="archiveSerCache")
@Service
public class ForeignStaffingSerImpl extends ServiceImpl<ForeignStaffing, ForeignStaffingDTO> implements ForeignStaffingSer { 

 }