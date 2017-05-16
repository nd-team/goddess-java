package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.rotation.dto.RecommendRotationDTO;
import com.bjike.goddess.rotation.entity.RecommendRotation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 岗位轮换推荐业务实现
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-13 02:28 ]
* @Description:	[ 岗位轮换推荐业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="rotationSerCache")
@Service
public class RecommendRotationSerImpl extends ServiceImpl<RecommendRotation, RecommendRotationDTO> implements RecommendRotationSer { 

 }