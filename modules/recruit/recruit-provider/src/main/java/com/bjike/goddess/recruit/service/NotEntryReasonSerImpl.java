package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.NotEntryReasonDTO;
import com.bjike.goddess.recruit.entity.NotEntryReason;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 未入职原因业务实现
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-26 01:59 ]
* @Description:	[ 未入职原因业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="recruitSerCache")
@Service
public class NotEntryReasonSerImpl extends ServiceImpl<NotEntryReason, NotEntryReasonDTO> implements NotEntryReasonSer { 

 }