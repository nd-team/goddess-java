package com.bjike.goddess.costdetail.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.costdetail.dto.SonCostDetailDTO;
import com.bjike.goddess.costdetail.entity.SonCostDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 子成本明细业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-02 11:03 ]
* @Description:	[ 子成本明细业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="costdetailSerCache")
@Service
public class SonCostDetailSerImpl extends ServiceImpl<SonCostDetail, SonCostDetailDTO> implements SonCostDetailSer { 

 }