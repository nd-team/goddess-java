package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.labelOGDTO;
import com.bjike.goddess.recruit.entity.LabelOG;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 对赌标签业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-11 03:23 ]
* @Description:	[ 对赌标签业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="recruitSerCache")
@Service
public class labelOGSerImpl extends ServiceImpl<LabelOG, labelOGDTO> implements labelOGSer {

 }