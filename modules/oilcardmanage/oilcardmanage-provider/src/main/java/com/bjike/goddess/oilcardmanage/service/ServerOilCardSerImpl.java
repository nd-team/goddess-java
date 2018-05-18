package com.bjike.goddess.oilcardmanage.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.oilcardmanage.dto.ServerOilCardDTO;
import com.bjike.goddess.oilcardmanage.entity.ServerOilCard;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 旧服务器上的油卡信息业务实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 09:24 ]
* @Description:	[ 旧服务器上的油卡信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="oilcardmanageSerCache")
@Service
public class ServerOilCardSerImpl extends ServiceImpl<ServerOilCard, ServerOilCardDTO> implements ServerOilCardSer { 

 }