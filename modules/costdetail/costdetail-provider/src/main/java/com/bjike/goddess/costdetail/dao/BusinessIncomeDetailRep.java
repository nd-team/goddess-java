package com.bjike.goddess.costdetail.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.costdetail.dto.BusinessIncomeDetailDTO;
import com.bjike.goddess.costdetail.entity.BusinessIncomeDetail;

/**
* 主营业务收入明细持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-07-06 06:27 ]
* @Description:	[ 主营业务收入明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BusinessIncomeDetailRep extends JpaRep<BusinessIncomeDetail ,BusinessIncomeDetailDTO> { 

 }