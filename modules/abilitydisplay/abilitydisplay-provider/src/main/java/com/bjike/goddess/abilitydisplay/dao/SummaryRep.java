package com.bjike.goddess.abilitydisplay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.abilitydisplay.dto.SummaryDTO;
import com.bjike.goddess.abilitydisplay.entity.Summary;

/**
* 能力展示汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-08 02:04 ]
* @Description:	[ 能力展示汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SummaryRep extends JpaRep<Summary ,SummaryDTO> { 

 }