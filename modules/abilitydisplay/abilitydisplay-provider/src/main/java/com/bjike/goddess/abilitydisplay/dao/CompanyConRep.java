package com.bjike.goddess.abilitydisplay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.abilitydisplay.dto.CompanyConDTO;
import com.bjike.goddess.abilitydisplay.entity.CompanyCon;

/**
* 公司联系人持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-05 09:00 ]
* @Description:	[ 公司联系人持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyConRep extends JpaRep<CompanyCon ,CompanyConDTO> { 

 }