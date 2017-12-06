package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.marketdevelopment.dto.WeekFilesDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekFiles;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 周计划的表头数据业务实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:46 ]
* @Description:	[ 周计划的表头数据业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="marketdevelopmentSerCache")
@Service
public class WeekFilesSerImpl extends ServiceImpl<WeekFiles, WeekFilesDTO> implements WeekFilesSer { 

 }