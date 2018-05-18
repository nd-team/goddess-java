package com.bjike.goddess.workprogress.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.workprogress.dto.ReferenceTargetDTO;
import com.bjike.goddess.workprogress.entity.ReferenceTarget;

/**
* 参考指标持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-05-17 02:56 ]
* @Description:	[ 参考指标持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ReferenceTargetRep extends JpaRep<ReferenceTarget ,ReferenceTargetDTO> { 

 }