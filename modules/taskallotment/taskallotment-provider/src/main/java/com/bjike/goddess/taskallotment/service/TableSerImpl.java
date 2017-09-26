package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.entity.Table;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 项目表业务实现
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-14 11:58 ]
* @Description:	[ 项目表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="taskallotmentSerCache")
@Service
public class TableSerImpl extends ServiceImpl<Table, TableDTO> implements TableSer { 

 }