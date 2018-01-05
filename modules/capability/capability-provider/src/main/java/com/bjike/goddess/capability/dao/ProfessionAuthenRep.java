package com.bjike.goddess.capability.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.capability.dto.ProfessionAuthenDTO;
import com.bjike.goddess.capability.entity.ProfessionAuthen;

/**
* 专业资质认证持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-06-16 06:06 ]
* @Description:	[ 专业资质认证持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ProfessionAuthenRep extends JpaRep<ProfessionAuthen ,ProfessionAuthenDTO> { 

 }