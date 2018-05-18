package com.bjike.goddess.contractware.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractware.dto.InvoiceManagementDTO;
import com.bjike.goddess.contractware.entity.InvoiceManagement;

/**
* 发票管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-01 11:04 ]
* @Description:	[ 发票管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InvoiceManagementRep extends JpaRep<InvoiceManagement ,InvoiceManagementDTO> { 

 }