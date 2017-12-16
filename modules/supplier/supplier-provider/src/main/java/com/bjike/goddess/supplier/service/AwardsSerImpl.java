package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.supplier.dto.AwardsDTO;
import com.bjike.goddess.supplier.entity.Awards;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 获奖情况业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:00 ]
* @Description:	[ 获奖情况业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="supplierSerCache")
@Service
public class AwardsSerImpl extends ServiceImpl<Awards, AwardsDTO> implements AwardsSer { 

 }