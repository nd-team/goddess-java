package com.bjike.goddess.projectprocing.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectprocing.dto.NotificationFormulaDTO;
import com.bjike.goddess.projectprocing.entity.NotificationFormula;

/**
* 通报机制制定持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-11-18 03:24 ]
* @Description:	[ 通报机制制定持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface NotificationFormulaRep extends JpaRep<NotificationFormula ,NotificationFormulaDTO> { 

 }