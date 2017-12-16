package com.bjike.goddess.competitorsmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.competitorsmanagement.dto.CompetitorDTO;
import com.bjike.goddess.competitorsmanagement.entity.Competitor;

/**
* 竞争对手管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2017-12-13 09:45 ]
* @Description:	[ 竞争对手管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompetitorRep extends JpaRep<Competitor ,CompetitorDTO> { 

 }