package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.OutBillDTO;
import com.bjike.goddess.marketdevelopment.entity.OutBill;

/**
* 外出单持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-30 11:12 ]
* @Description:	[ 外出单持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface OutBillRep extends JpaRep<OutBill ,OutBillDTO> { 

 }