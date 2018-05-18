package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaSubjectDTO;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaSubject;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 老系统的科目业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-11-17 10:33 ]
* @Description:	[ 老系统的科目业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="lendreimbursementSerCache")
@Service
public class AjoaSubjectSerImpl extends ServiceImpl<AjoaSubject, AjoaSubjectDTO> implements AjoaSubjectSer { 

 }