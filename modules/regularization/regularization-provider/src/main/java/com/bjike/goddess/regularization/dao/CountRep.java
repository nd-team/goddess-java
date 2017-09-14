package com.bjike.goddess.regularization.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.regularization.dto.CountDTO;
import com.bjike.goddess.regularization.entity.Count;

/**
* 操作汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-09-09 02:00 ]
* @Description:	[ 操作汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CountRep extends JpaRep<Count ,CountDTO> { 

 }