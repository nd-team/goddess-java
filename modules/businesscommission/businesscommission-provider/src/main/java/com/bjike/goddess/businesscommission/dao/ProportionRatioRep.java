package com.bjike.goddess.businesscommission.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businesscommission.dto.ProportionRatioDTO;
import com.bjike.goddess.businesscommission.entity.ProportionRatio;

/**
* 业务提成分配比例表子表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-23 11:26 ]
* @Description:	[ 业务提成分配比例表子表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProportionRatioRep extends JpaRep<ProportionRatio ,ProportionRatioDTO> { 

 }