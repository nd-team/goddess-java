package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.shareholdersmanage.dto.ShareOpenAccountDTO;
import com.bjike.goddess.shareholdersmanage.entity.ShareOpenAccount;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 股东开户业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-18 02:41 ]
* @Description:	[ 股东开户业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="shareholdersmanageSerCache")
@Service
public class ShareOpenAccountSerImpl extends ServiceImpl<ShareOpenAccount, ShareOpenAccountDTO> implements ShareOpenAccountSer { 

 }