package com.bjike.goddess.capability.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.capability.dto.CompanyCertificateDTO;
import com.bjike.goddess.capability.entity.CompanyCertificate;

/**
* 公司荣誉证书持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-17 09:49 ]
* @Description:	[ 公司荣誉证书持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyCertificateRep extends JpaRep<CompanyCertificate ,CompanyCertificateDTO> { 

 }