package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.IndicatorDTO;
import com.bjike.goddess.organize.entity.Indicator;

/**
* 模块指标表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-12 02:36 ]
* @Description:	[ 模块指标表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface IndicatorRep extends JpaRep<Indicator ,IndicatorDTO> { 

 }