package com.bjike.goddess.materialbuy.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.materialbuy.dto.BankOfTheWaterDetailDTO;
import com.bjike.goddess.materialbuy.entity.BankOfTheWaterDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 银行流水业务实现
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-07 10:29 ]
* @Description:	[ 银行流水业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="materialbuySerCache")
@Service
public class BankOfTheWaterDetailSerImpl extends ServiceImpl<BankOfTheWaterDetail, BankOfTheWaterDetailDTO> implements BankOfTheWaterDetailSer { 

 }