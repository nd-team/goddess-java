package com.bjike.goddess.reimbursementprepare.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reimbursementprepare.dto.MoneyReadyDTO;
import com.bjike.goddess.reimbursementprepare.entity.MoneyReady;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 资金准备业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-03 03:07 ]
* @Description:	[ 资金准备业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="reimbursementprepareSerCache")
@Service
public class MoneyReadySerImpl extends ServiceImpl<MoneyReady, MoneyReadyDTO> implements MoneyReadySer { 

 }