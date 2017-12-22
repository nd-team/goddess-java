package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.SupplierTypeSetDTO;
import com.bjike.goddess.supplier.entity.SupplierTypeSet;

/**
* 供应商类型设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 04:07 ]
* @Description:	[ 供应商类型设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SupplierTypeSetRep extends JpaRep<SupplierTypeSet ,SupplierTypeSetDTO> { 

 }