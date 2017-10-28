package com.bjike.goddess.rentutilitiespay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.rentutilitiespay.dto.CollectDTO;
import com.bjike.goddess.rentutilitiespay.entity.Collect;

/**
* 汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-20 03:59 ]
* @Description:	[ 汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectRep extends JpaRep<Collect ,CollectDTO> { 

 }