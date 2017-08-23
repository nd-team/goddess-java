package com.bjike.goddess.businessproject.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.DispatchSheet;

/**
* 商务项目派工单信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-21 10:06 ]
* @Description:	[ 商务项目派工单信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DispatchSheetRep extends JpaRep<DispatchSheet ,DispatchSheetDTO> { 

 }