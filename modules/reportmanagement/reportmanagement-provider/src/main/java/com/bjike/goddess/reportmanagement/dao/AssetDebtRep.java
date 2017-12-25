package com.bjike.goddess.reportmanagement.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.reportmanagement.dto.AssetDebtDTO;
import com.bjike.goddess.reportmanagement.entity.AssetDebt;

/**
* 资产负债总表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-06-19 11:37 ]
* @Description:	[ 资产负债总表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AssetDebtRep extends JpaRep<AssetDebt ,AssetDebtDTO> { 

 }