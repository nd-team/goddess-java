package com.bjike.goddess.abilitydisplay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.abilitydisplay.dto.CompanyPlanDTO;
import com.bjike.goddess.abilitydisplay.entity.CompanyPlan;

/**
* 公司规划持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 02:24 ]
* @Description:	[ 公司规划持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyPlanRep extends JpaRep<CompanyPlan ,CompanyPlanDTO> { 

 }