package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.recruit.dto.ScoreWithStartDTO;
import com.bjike.goddess.recruit.entity.ScoreWithStart;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 打分业务实现
* @Author:			[ wanyi ]
* @Date:			[  2018-01-15 10:14 ]
* @Description:	[ 打分业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="recruitSerCache")
@Service
public class ScoreWithStartSerImpl extends ServiceImpl<ScoreWithStart, ScoreWithStartDTO> implements ScoreWithStartSer { 

 }