package com.bjike.goddess.qualifications.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.qualifications.dto.AuditMaterialDTO;
import com.bjike.goddess.qualifications.entity.AuditMaterial;

/**
* 审核资料持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 06:44 ]
* @Description:	[ 审核资料持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AuditMaterialRep extends JpaRep<AuditMaterial ,AuditMaterialDTO> { 

 }