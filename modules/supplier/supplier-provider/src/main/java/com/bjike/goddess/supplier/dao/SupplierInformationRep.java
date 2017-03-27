package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.entity.SupplierInformation;

/**
* 供应商基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-20T10:46:45.061 ]
* @Description:	[ 供应商基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SupplierInformationRep extends JpaRep<SupplierInformation ,SupplierInformationDTO> { 

 }