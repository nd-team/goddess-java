package com.bjike.goddess.fundcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fundcheck.dto.AccountIncomeDTO;
import com.bjike.goddess.fundcheck.entity.AccountIncome;

/**
* 账务收入持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-07-01 01:52 ]
* @Description:	[ 账务收入持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AccountIncomeRep extends JpaRep<AccountIncome ,AccountIncomeDTO> { 

 }