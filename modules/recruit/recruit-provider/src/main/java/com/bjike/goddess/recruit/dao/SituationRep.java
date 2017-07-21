package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.SituationDTO;
import com.bjike.goddess.recruit.entity.Situation;

/**
* 招聘情况统计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-07-20 08:26 ]
* @Description:	[ 招聘情况统计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SituationRep extends JpaRep<Situation ,SituationDTO> { 

 }