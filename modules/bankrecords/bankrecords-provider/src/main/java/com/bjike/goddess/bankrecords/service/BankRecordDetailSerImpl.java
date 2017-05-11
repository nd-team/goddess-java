package com.bjike.goddess.bankrecords.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.bankrecords.dto.BankRecordDetailDTO;
import com.bjike.goddess.bankrecords.entity.BankRecordDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 银行流水明细业务实现
* @Author:			[ Jason ]
* @Date:			[  2017-04-22 05:47 ]
* @Description:	[ 银行流水明细业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="bankrecordsSerCache")
@Service
public class BankRecordDetailSerImpl extends ServiceImpl<BankRecordDetail, BankRecordDetailDTO> implements BankRecordDetailSer { 

 }