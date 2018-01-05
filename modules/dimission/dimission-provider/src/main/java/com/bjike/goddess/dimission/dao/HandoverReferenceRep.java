package com.bjike.goddess.dimission.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dimission.dto.HandoverReferenceDTO;
import com.bjike.goddess.dimission.entity.HandoverReference;

/**
* 交接信息参考持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-17 02:36 ]
* @Description:	[ 交接信息参考持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface HandoverReferenceRep extends JpaRep<HandoverReference ,HandoverReferenceDTO> { 

 }