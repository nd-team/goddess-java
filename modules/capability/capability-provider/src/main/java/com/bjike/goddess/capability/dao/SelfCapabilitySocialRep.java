package com.bjike.goddess.capability.dao;

import com.bjike.goddess.capability.dto.SelfCapabilityDTO;
import com.bjike.goddess.capability.dto.SelfCapabilitySocialDTO;
import com.bjike.goddess.capability.entity.SelfCapability;
import com.bjike.goddess.capability.entity.SelfCapabilitySocial;
import com.bjike.goddess.common.jpa.dao.JpaRep;

/**
* 个人能力展示持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-23 04:22 ]
* @Description:	[ 个人能力展示持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SelfCapabilitySocialRep extends JpaRep<SelfCapabilitySocial,SelfCapabilitySocialDTO> {

 }