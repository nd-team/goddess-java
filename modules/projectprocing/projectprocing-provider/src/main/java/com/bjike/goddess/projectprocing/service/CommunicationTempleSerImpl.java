package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectprocing.dto.CommunicationTempleDTO;
import com.bjike.goddess.projectprocing.entity.CommunicationTemple;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 各类沟通模板业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:26 ]
* @Description:	[ 各类沟通模板业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectprocingSerCache")
@Service
public class CommunicationTempleSerImpl extends ServiceImpl<CommunicationTemple, CommunicationTempleDTO> implements CommunicationTempleSer { 

 }