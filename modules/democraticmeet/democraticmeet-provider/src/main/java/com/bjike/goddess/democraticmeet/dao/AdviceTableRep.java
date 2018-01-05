package com.bjike.goddess.democraticmeet.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.democraticmeet.dto.AdviceTableDTO;
import com.bjike.goddess.democraticmeet.entity.AdviceTable;

/**
* 建议表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-06-03 11:26 ]
* @Description:	[ 建议表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AdviceTableRep extends JpaRep<AdviceTable ,AdviceTableDTO> { 

 }