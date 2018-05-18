package com.bjike.goddess.businesscommission.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businesscommission.dto.ProportionDTO;
import com.bjike.goddess.businesscommission.entity.Proportion;

/**
* 业务提成分配比例表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-23 11:29 ]
* @Description:	[ 业务提成分配比例表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProportionRep extends JpaRep<Proportion ,ProportionDTO> { 

 }