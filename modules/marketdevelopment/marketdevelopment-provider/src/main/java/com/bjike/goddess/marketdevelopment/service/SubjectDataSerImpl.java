package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.SubjectDataDTO;
import com.bjike.goddess.marketdevelopment.entity.SubjectData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 业务方向科目数据业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 03:03 ]
* @Description:	[ 业务方向科目数据业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class SubjectDataSerImpl extends ServiceImpl<SubjectData, SubjectDataDTO> implements SubjectDataSer { 

 }