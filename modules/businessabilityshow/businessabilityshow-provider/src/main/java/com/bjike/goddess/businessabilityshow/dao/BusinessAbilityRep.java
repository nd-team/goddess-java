package com.bjike.goddess.businessabilityshow.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessabilityshow.dto.BusinessAbilityDTO;
import com.bjike.goddess.businessabilityshow.entity.BusinessAbility;

/**
* 项目合同基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-16 02:50 ]
* @Description:	[ 项目合同基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BusinessAbilityRep extends JpaRep<BusinessAbility ,BusinessAbilityDTO> { 

 }