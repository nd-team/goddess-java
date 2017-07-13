package com.bjike.goddess.fundcheck.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.fundcheck.dto.AccountSpendDTO;
import com.bjike.goddess.fundcheck.entity.AccountSpend;

/**
* 账务支出持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-07-01 02:02 ]
* @Description:	[ 账务支出持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AccountSpendRep extends JpaRep<AccountSpend ,AccountSpendDTO> { 

 }