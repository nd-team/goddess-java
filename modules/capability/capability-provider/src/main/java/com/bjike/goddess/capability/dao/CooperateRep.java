package com.bjike.goddess.capability.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.capability.dto.CooperateDTO;
import com.bjike.goddess.capability.entity.Cooperate;

/**
* 公司合作对象持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:03 ]
* @Description:	[ 公司合作对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CooperateRep extends JpaRep<Cooperate ,CooperateDTO> { 

 }