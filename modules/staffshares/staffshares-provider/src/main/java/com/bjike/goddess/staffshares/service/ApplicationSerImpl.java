package com.bjike.goddess.staffshares.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.staffshares.dto.ApplicationDTO;
import com.bjike.goddess.staffshares.entity.Application;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 干股代表申请业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-04 10:27 ]
* @Description:	[ 干股代表申请业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="staffsharesSerCache")
@Service
public class ApplicationSerImpl extends ServiceImpl<Application, ApplicationDTO> implements ApplicationSer { 

 }