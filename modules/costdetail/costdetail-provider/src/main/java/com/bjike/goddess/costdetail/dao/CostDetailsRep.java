package com.bjike.goddess.costdetail.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.costdetail.dto.CostDetailsDTO;
import com.bjike.goddess.costdetail.entity.CostDetails;

/**
* 成本明细持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-07-06 05:45 ]
* @Description:	[ 成本明细持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CostDetailsRep extends JpaRep<CostDetails ,CostDetailsDTO> { 

 }