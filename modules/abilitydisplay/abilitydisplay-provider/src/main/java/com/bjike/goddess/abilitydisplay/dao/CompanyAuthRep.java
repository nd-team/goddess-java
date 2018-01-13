package com.bjike.goddess.abilitydisplay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.abilitydisplay.dto.CompanyAuthDTO;
import com.bjike.goddess.abilitydisplay.entity.CompanyAuth;

/**
* 公司认证持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-08 05:31 ]
* @Description:	[ 公司认证持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyAuthRep extends JpaRep<CompanyAuth ,CompanyAuthDTO> { 

 }