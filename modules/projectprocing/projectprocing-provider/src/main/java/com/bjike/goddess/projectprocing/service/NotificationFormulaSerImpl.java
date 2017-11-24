package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectprocing.dto.NotificationFormulaDTO;
import com.bjike.goddess.projectprocing.entity.NotificationFormula;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 通报机制制定业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:24 ]
* @Description:	[ 通报机制制定业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectprocingSerCache")
@Service
public class NotificationFormulaSerImpl extends ServiceImpl<NotificationFormula, NotificationFormulaDTO> implements NotificationFormulaSer { 

 }