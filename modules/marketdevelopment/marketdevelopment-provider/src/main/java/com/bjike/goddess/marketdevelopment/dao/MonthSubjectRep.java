package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectDTO;
import com.bjike.goddess.marketdevelopment.entity.MonthSubject;

/**
* 月计划的业务科目持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-06 05:04 ]
* @Description:	[ 月计划的业务科目持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MonthSubjectRep extends JpaRep<MonthSubject ,MonthSubjectDTO> { 

 }