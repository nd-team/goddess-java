package com.bjike.goddess.progressmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.progressmanage.dto.NodeHeadValueDTO;
import com.bjike.goddess.progressmanage.entity.NodeHeadValue;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 节点表头值业务实现
* @Author:			[ Jason ]
* @Date:			[  2017-06-26 09:54 ]
* @Description:	[ 节点表头值业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="progressmanageSerCache")
@Service
public class NodeHeadValueSerImpl extends ServiceImpl<NodeHeadValue, NodeHeadValueDTO> implements NodeHeadValueSer { 

 }