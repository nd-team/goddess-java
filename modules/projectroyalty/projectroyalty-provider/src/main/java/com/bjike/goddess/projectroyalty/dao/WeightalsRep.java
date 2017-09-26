package com.bjike.goddess.projectroyalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectroyalty.dto.WeightalsDTO;
import com.bjike.goddess.projectroyalty.entity.Weightals;

/**
* 项目提成权重分配表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-20 03:34 ]
* @Description:	[ 项目提成权重分配表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WeightalsRep extends JpaRep<Weightals ,WeightalsDTO> { 

 }