package com.bjike.goddess.rentcar.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.rentcar.dto.CollectDriverInfoDTO;
import com.bjike.goddess.rentcar.entity.CollectDriverInfo;

/**
* 租车协议管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectDriverInfoRep extends JpaRep<CollectDriverInfo ,CollectDriverInfoDTO> { 

 }