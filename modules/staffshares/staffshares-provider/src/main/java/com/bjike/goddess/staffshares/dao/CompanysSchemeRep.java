package com.bjike.goddess.staffshares.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffshares.dto.CompanysSchemeDTO;
import com.bjike.goddess.staffshares.entity.CompanysScheme;

/**
* 公司干股交易情况表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-08-11 04:38 ]
* @Description:	[ 公司干股交易情况表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanysSchemeRep extends JpaRep<CompanysScheme ,CompanysSchemeDTO> { 

 }