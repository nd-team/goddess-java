package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaUserDTO;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 老系统的报销业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-11-21 12:03 ]
* @Description:	[ 老系统的报销业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="lendreimbursementSerCache")
@Service
public class AjoaUserSerImpl extends ServiceImpl<AjoaUser, AjoaUserDTO> implements AjoaUserSer { 

 }