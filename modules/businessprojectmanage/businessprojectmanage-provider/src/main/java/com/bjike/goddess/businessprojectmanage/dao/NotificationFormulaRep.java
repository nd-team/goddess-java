package com.bjike.goddess.businessprojectmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessprojectmanage.dto.NotificationFormulaDTO;
import com.bjike.goddess.businessprojectmanage.entity.NotificationFormula;

/**
* 通报机制制定持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-14 05:05 ]
* @Description:	[ 通报机制制定持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface NotificationFormulaRep extends JpaRep<NotificationFormula ,NotificationFormulaDTO> { 

 }