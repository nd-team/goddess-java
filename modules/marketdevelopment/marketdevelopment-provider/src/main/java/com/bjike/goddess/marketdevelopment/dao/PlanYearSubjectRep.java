package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.PlanYearSubjectDTO;
import com.bjike.goddess.marketdevelopment.entity.PlanYearSubject;

/**
* 年计划(科目方向)持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-08 03:34 ]
* @Description:	[ 年计划(科目方向)持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PlanYearSubjectRep extends JpaRep<PlanYearSubject ,PlanYearSubjectDTO> { 

 }