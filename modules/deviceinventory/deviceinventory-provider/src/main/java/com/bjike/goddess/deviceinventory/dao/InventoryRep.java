package com.bjike.goddess.deviceinventory.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.deviceinventory.dto.InventoryDTO;
import com.bjike.goddess.deviceinventory.entity.Inventory;

/**
* 盘点持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ chenjunhao ]
* @Date:			[  2017-05-19 08:54 ]
* @Description:	[ 盘点持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InventoryRep extends JpaRep<Inventory ,InventoryDTO> { 

 }