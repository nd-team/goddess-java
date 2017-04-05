package com.bjike.goddess.qualifications.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.qualifications.dto.HandlePlanStageDTO;
import com.bjike.goddess.qualifications.entity.HandlePlanStage;

/**
* 资质办理计划阶段划分持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 04:55 ]
* @Description:	[ 资质办理计划阶段划分持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface HandlePlanStageRep extends JpaRep<HandlePlanStage ,HandlePlanStageDTO> { 

 }