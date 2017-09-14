package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.organize.dto.ModulesDTO;
import com.bjike.goddess.organize.entity.Modules;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 岗位工作明细表-模块表业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-12 01:58 ]
* @Description:	[ 岗位工作明细表-模块表业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="organizeSerCache")
@Service
public class ModulesSerImpl extends ServiceImpl<Modules, ModulesDTO> implements ModulesSer { 

 }