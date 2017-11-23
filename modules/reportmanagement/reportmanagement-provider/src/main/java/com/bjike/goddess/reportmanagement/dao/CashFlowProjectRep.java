package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.CashFlowProjectDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlowProject;

/**
* 现金流量项目表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-20 03:06 ]
* @Description:	[ 现金流量项目表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CashFlowProjectRep extends JpaRep<CashFlowProject ,CashFlowProjectDTO> { 

 }