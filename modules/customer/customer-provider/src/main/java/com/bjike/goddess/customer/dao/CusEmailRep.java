package com.bjike.goddess.customer.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.customer.dto.CusEmailDTO;
import com.bjike.goddess.customer.entity.CusEmail;

/**
* 客户邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ tanghaixiang ]
* @Date:			[  2017-03-16T19:08:18.889 ]
* @Description:	[ 客户邮件发送定制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CusEmailRep extends JpaRep<CusEmail ,CusEmailDTO> { 

 }