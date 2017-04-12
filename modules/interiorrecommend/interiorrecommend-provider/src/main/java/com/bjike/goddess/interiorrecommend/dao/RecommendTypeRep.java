package com.bjike.goddess.interiorrecommend.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.interiorrecommend.dto.RecommendTypeDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendType;

/**
* 推荐类型设定持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-09 02:10 ]
* @Description:	[ 推荐类型设定持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RecommendTypeRep extends JpaRep<RecommendType ,RecommendTypeDTO> { 

 }