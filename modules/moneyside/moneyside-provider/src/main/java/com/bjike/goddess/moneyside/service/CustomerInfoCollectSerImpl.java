package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.moneyside.dto.CustomerInfoCollectDTO;
import com.bjike.goddess.moneyside.entity.CustomerInfoCollect;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 客户信息汇总业务实现
* @Author:			[ xiazhili ]
* @Date:			[  2017-06-07 10:11 ]
* @Description:	[ 客户信息汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="moneysideSerCache")
@Service
public class CustomerInfoCollectSerImpl extends ServiceImpl<CustomerInfoCollect, CustomerInfoCollectDTO> implements CustomerInfoCollectSer { 

 }