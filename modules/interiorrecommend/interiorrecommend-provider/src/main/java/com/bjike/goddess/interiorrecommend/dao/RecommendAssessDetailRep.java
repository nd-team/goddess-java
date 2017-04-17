package com.bjike.goddess.interiorrecommend.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.interiorrecommend.dto.RecommendAssessDetailDTO;
import com.bjike.goddess.interiorrecommend.entity.RecommendAssessDetail;

/**
* 推荐内容持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-09 03:28 ]
* @Description:	[ 推荐内容持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RecommendAssessDetailRep extends JpaRep<RecommendAssessDetail ,RecommendAssessDetailDTO> { 

 }