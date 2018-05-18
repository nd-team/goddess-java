package com.bjike.goddess.marketdevelopment.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.marketdevelopment.dto.BusinessDTO;
import com.bjike.goddess.marketdevelopment.entity.Business;

/**
* 业务对象持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-28 02:54 ]
* @Description:	[ 业务对象持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BusinessRep extends JpaRep<Business ,BusinessDTO> { 

 }