package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.FilesDataTotalDTO;
import com.bjike.goddess.marketdevelopment.entity.FilesDataTotal;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 合计业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 02:38 ]
* @Description:	[ 合计业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class FilesDataTotalSerImpl extends ServiceImpl<FilesDataTotal, FilesDataTotalDTO> implements FilesDataTotalSer { 

 }