package com.bjike.goddess.businsurance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingDetailDTO;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 团体意外险购买详情业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-26 10:44 ]
* @Description:	[ 团体意外险购买详情业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="businsuranceSerCache")
@Service
public class CasualtyPurchasingDetailSerImpl extends ServiceImpl<CasualtyPurchasingDetail, CasualtyPurchasingDetailDTO> implements CasualtyPurchasingDetailSer { 

 }