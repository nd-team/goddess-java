package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.ReplaceRegisterDTO;
import com.bjike.goddess.secure.entity.ReplaceRegister;

/**
* 社保卡补办登记表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-09-25 06:09 ]
* @Description:	[ 社保卡补办登记表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ReplaceRegisterRep extends JpaRep<ReplaceRegister ,ReplaceRegisterDTO> { 

 }