package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.democraticmeet.dto.AdviceTableDTO;
import com.bjike.goddess.democraticmeet.entity.AdviceTable;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 建议表业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-03 11:26 ]
* @Description:	[ 建议表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="democraticmeetSerCache")
@Service
public class AdviceTableSerImpl extends ServiceImpl<AdviceTable, AdviceTableDTO> implements AdviceTableSer { 

 }