package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectprocing.dto.SettleProgressRecordDTO;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 结算进度调整记录&结算问题汇总业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:19 ]
* @Description:	[ 结算进度调整记录&结算问题汇总业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectprocingSerCache")
@Service
public class SettleProgressRecordSerImpl extends ServiceImpl<SettleProgressRecord, SettleProgressRecordDTO> implements SettleProgressRecordSer { 

 }