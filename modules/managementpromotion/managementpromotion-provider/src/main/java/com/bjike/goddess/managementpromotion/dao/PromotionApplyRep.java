package com.bjike.goddess.managementpromotion.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.managementpromotion.dto.PromotionApplyDTO;
import com.bjike.goddess.managementpromotion.entity.PromotionApply;

/**
* 管理等级晋升申请持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-22 02:04 ]
* @Description:	[ 管理等级晋升申请持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PromotionApplyRep extends JpaRep<PromotionApply ,PromotionApplyDTO> { 

 }