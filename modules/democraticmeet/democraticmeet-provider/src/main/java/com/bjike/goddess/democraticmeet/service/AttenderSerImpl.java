package com.bjike.goddess.democraticmeet.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.democraticmeet.dto.AttenderDTO;
import com.bjike.goddess.democraticmeet.entity.Attender;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 会议计划参与人员业务实现
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-02 11:29 ]
* @Description:	[ 会议计划参与人员业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="democraticmeetSerCache")
@Service
public class AttenderSerImpl extends ServiceImpl<Attender, AttenderDTO> implements AttenderSer { 

 }