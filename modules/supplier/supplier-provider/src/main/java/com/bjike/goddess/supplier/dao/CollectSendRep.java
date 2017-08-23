package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.CollectSendDTO;
import com.bjike.goddess.supplier.entity.CollectSend;

/**
* 供应商汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-06-14 11:48 ]
* @Description:	[ 供应商汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectSendRep extends JpaRep<CollectSend ,CollectSendDTO> { 

 }