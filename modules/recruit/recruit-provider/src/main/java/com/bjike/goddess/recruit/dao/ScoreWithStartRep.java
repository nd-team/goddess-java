package com.bjike.goddess.recruit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.recruit.dto.ScoreWithStartDTO;
import com.bjike.goddess.recruit.entity.ScoreWithStart;

/**
* 打分持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-15 10:14 ]
* @Description:	[ 打分持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ScoreWithStartRep extends JpaRep<ScoreWithStart ,ScoreWithStartDTO> { 

 }