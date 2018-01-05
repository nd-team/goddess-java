package com.bjike.goddess.projectmarketfee.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectmarketfee.dto.ProjectMarketFeeCountDTO;
import com.bjike.goddess.projectmarketfee.entity.ProjectMarketFeeCount;

/**
* 项目前期的市场活动费汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-15 01:50 ]
* @Description:	[ 项目前期的市场活动费汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProjectMarketFeeCountRep extends JpaRep<ProjectMarketFeeCount ,ProjectMarketFeeCountDTO> { 

 }