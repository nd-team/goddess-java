package com.bjike.goddess.materialbuy.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialbuy.dto.BankSummaryDTO;
import com.bjike.goddess.materialbuy.entity.BankSummary;

/**
* 对银行流水汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yangruizhen ]
* @Date:			[  2018-04-02 02:21 ]
* @Description:	[ 对银行流水汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BankSummaryRep extends JpaRep<BankSummary ,BankSummaryDTO> { 

 }