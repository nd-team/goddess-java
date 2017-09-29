package com.bjike.goddess.businsurance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businsurance.dto.CasualtyPurchasingListDTO;
import com.bjike.goddess.businsurance.entity.CasualtyPurchasingList;

/**
* 团体意外险购买名单持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-09-26 10:24 ]
* @Description:	[ 团体意外险购买名单持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CasualtyPurchasingListRep extends JpaRep<CasualtyPurchasingList ,CasualtyPurchasingListDTO> { 

 }