package com.bjike.goddess.lendreimbursement.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.lendreimbursement.dto.mailingByReimDTO;
import com.bjike.goddess.lendreimbursement.entity.mailingByReim;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 报销-寄件信息业务实现
* @Author:			[ wany ]
* @Date:			[  2018-03-13 11:07 ]
* @Description:	[ 报销-寄件信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="lendreimbursementSerCache")
@Service
public class mailingByReimSerImpl extends ServiceImpl<mailingByReim, mailingByReimDTO> implements mailingByReimSer { 

 }