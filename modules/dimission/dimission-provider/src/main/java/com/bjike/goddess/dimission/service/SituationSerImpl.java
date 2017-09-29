package com.bjike.goddess.dimission.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.dimission.dto.SituationDTO;
import com.bjike.goddess.dimission.entity.Situation;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 离职办理节点情况业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-28 11:28 ]
* @Description:	[ 离职办理节点情况业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="dimissionSerCache")
@Service
public class SituationSerImpl extends ServiceImpl<Situation, SituationDTO> implements SituationSer { 

 }