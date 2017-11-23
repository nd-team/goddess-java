package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.projectprocing.dto.OutsourProProgressManageDTO;
import com.bjike.goddess.projectprocing.entity.OutsourProProgressManage;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 外包,半外包项目结算进度管理业务实现
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:03 ]
* @Description:	[ 外包,半外包项目结算进度管理业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="projectprocingSerCache")
@Service
public class OutsourProProgressManageSerImpl extends ServiceImpl<OutsourProProgressManage, OutsourProProgressManageDTO> implements OutsourProProgressManageSer { 

 }