package com.bjike.goddess.archive.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.entity.ArchiveGather;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 档案收集业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 02:22 ]
* @Description:	[ 档案收集业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="archiveSerCache")
@Service
public class ArchiveGatherSerImpl extends ServiceImpl<ArchiveGather, ArchiveGatherDTO> implements ArchiveGatherSer { 

 }