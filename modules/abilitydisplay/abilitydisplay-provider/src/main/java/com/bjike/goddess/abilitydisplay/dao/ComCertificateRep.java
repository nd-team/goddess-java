package com.bjike.goddess.abilitydisplay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.abilitydisplay.dto.ComCertificateDTO;
import com.bjike.goddess.abilitydisplay.entity.ComCertificate;

/**
* 公司证书持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 02:45 ]
* @Description:	[ 公司证书持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ComCertificateRep extends JpaRep<ComCertificate ,ComCertificateDTO> { 

 }