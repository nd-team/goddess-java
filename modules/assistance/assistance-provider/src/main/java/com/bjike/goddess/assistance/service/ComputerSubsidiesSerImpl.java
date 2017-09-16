package com.bjike.goddess.assistance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.assistance.dto.ComputerSubsidiesDTO;
import com.bjike.goddess.assistance.entity.ComputerSubsidies;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 电脑补助业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-16 02:59 ]
* @Description:	[ 电脑补助业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="assistanceSerCache")
@Service
public class ComputerSubsidiesSerImpl extends ServiceImpl<ComputerSubsidies, ComputerSubsidiesDTO> implements ComputerSubsidiesSer { 

 }