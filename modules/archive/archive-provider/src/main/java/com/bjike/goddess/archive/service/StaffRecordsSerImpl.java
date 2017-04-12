package com.bjike.goddess.archive.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.archive.dto.StaffRecordsDTO;
import com.bjike.goddess.archive.entity.StaffRecords;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 员工档案业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 10:32 ]
* @Description:	[ 员工档案业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="archiveSerCache")
@Service
public class StaffRecordsSerImpl extends ServiceImpl<StaffRecords, StaffRecordsDTO> implements StaffRecordsSer { 

 }