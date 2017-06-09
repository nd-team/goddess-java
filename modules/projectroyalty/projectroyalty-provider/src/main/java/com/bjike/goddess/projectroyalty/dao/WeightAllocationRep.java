package com.bjike.goddess.projectroyalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.projectroyalty.dto.WeightAllocationDTO;
import com.bjike.goddess.projectroyalty.entity.WeightAllocation;

/**
* 项目提成权重分配持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-06-07 09:12 ]
* @Description:	[ 项目提成权重分配持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WeightAllocationRep extends JpaRep<WeightAllocation ,WeightAllocationDTO> { 

 }