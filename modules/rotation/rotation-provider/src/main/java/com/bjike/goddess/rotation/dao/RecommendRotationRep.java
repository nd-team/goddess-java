package com.bjike.goddess.rotation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.rotation.dto.RecommendRotationDTO;
import com.bjike.goddess.rotation.entity.RecommendRotation;

/**
* 岗位轮换推荐持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-13 02:28 ]
* @Description:	[ 岗位轮换推荐持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RecommendRotationRep extends JpaRep<RecommendRotation ,RecommendRotationDTO> { 

 }