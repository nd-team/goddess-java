package com.bjike.goddess.costdetail.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.costdetail.dto.CollectDTO;
import com.bjike.goddess.costdetail.entity.Collect;

/**
* 成本明细汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-03 04:10 ]
* @Description:	[ 成本明细汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectRep extends JpaRep<Collect ,CollectDTO> { 

 }