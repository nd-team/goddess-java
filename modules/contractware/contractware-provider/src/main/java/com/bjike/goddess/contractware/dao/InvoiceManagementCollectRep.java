package com.bjike.goddess.contractware.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractware.dto.InvoiceManagementCollectDTO;
import com.bjike.goddess.contractware.entity.InvoiceManagementCollect;

/**
* 发票管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-02 09:12 ]
* @Description:	[ 发票管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InvoiceManagementCollectRep extends JpaRep<InvoiceManagementCollect ,InvoiceManagementCollectDTO> { 

 }