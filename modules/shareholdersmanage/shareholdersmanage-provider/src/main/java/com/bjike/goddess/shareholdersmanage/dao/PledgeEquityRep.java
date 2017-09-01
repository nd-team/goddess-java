package com.bjike.goddess.shareholdersmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.shareholdersmanage.dto.PledgeEquityDTO;
import com.bjike.goddess.shareholdersmanage.entity.PledgeEquity;

/**
* 质押股权持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 05:32 ]
* @Description:	[ 质押股权持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PledgeEquityRep extends JpaRep<PledgeEquity ,PledgeEquityDTO> { 

 }