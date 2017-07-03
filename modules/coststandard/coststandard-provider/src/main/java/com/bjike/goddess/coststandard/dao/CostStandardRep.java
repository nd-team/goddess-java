package com.bjike.goddess.coststandard.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.coststandard.dto.CostStandardDTO;
import com.bjike.goddess.coststandard.entity.CostStandard;

/**
* 费用标准持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-24 11:22 ]
* @Description:	[ 费用标准持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CostStandardRep extends JpaRep<CostStandard ,CostStandardDTO> { 

 }