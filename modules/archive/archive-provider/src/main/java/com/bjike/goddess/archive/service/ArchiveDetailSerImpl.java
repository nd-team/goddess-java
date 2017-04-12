package com.bjike.goddess.archive.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.entity.ArchiveDetail;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 档案明细业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 02:05 ]
* @Description:	[ 档案明细业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="archiveSerCache")
@Service
public class ArchiveDetailSerImpl extends ServiceImpl<ArchiveDetail, ArchiveDetailDTO> implements ArchiveDetailSer { 

 }