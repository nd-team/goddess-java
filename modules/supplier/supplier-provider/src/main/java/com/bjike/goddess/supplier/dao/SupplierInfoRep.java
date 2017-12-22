package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.entity.SupplierInfo;

/**
* 供应商信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-12-15 10:33 ]
* @Description:	[ 供应商信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SupplierInfoRep extends JpaRep<SupplierInfo ,SupplierInfoDTO> { 

 }