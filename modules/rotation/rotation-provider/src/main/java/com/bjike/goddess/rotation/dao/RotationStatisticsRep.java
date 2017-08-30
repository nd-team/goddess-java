package com.bjike.goddess.rotation.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.rotation.dto.RotationStatisticsDTO;
import com.bjike.goddess.rotation.entity.RotationStatistics;

/**
* 岗位轮换统计持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-13 02:38 ]
* @Description:	[ 岗位轮换统计持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface RotationStatisticsRep extends JpaRep<RotationStatistics ,RotationStatisticsDTO> { 

 }