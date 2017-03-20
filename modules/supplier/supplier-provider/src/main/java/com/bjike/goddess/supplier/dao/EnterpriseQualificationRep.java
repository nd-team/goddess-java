package com.bjike.goddess.supplier.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.supplier.dto.EnterpriseQualificationDTO;
import com.bjike.goddess.supplier.entity.EnterpriseQualification;

/**
* 企业资质持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-20T10:47:02.264 ]
* @Description:	[ 企业资质持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EnterpriseQualificationRep extends JpaRep<EnterpriseQualification ,EnterpriseQualificationDTO> { 

 }