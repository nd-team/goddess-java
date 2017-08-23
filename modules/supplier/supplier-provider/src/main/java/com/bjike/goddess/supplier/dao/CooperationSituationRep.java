package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.CooperationSituationDTO;
import com.bjike.goddess.supplier.entity.CooperationSituation;

/**
* 合作情况持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-20T11:05:37.663 ]
* @Description:	[ 合作情况持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CooperationSituationRep extends JpaRep<CooperationSituation ,CooperationSituationDTO> { 

 }