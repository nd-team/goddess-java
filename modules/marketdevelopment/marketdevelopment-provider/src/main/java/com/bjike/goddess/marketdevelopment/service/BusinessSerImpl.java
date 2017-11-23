package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.BusinessDTO;
import com.bjike.goddess.marketdevelopment.entity.Business;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 业务方向分类业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-18 02:56 ]
* @Description:	[ 业务方向分类业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class BusinessSerImpl extends ServiceImpl<Business, BusinessDTO> implements BusinessSer { 

 }