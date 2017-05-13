package com.bjike.goddess.projectcost.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectcost.dto.CostControlDTO;
import com.bjike.goddess.projectcost.entity.CostControl;

/**
* 项目成本控制持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ 邓钧仁 ]
* @Date:			[  2017-05-04 05:56 ]
* @Description:	[ 项目成本控制持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CostControlRep extends JpaRep<CostControl ,CostControlDTO> { 

 }