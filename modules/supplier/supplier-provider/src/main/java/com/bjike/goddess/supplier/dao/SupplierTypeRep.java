package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.SupplierTypeDTO;
import com.bjike.goddess.supplier.entity.SupplierType;

/**
* 供应商类型管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-20T14:12:54.998 ]
* @Description:	[ 供应商类型管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SupplierTypeRep extends JpaRep<SupplierType ,SupplierTypeDTO> { 

 }