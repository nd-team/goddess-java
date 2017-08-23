package com.bjike.goddess.competitormanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.competitormanage.dto.CompetitorCollectDTO;
import com.bjike.goddess.competitormanage.entity.CompetitorCollect;

/**
* 竞争对手汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-03-23 11:27 ]
* @Description:	[ 竞争对手汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompetitorCollectRep extends JpaRep<CompetitorCollect,CompetitorCollectDTO> {

 }