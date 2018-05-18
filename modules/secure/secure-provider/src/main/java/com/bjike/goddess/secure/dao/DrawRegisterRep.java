package com.bjike.goddess.secure.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.secure.dto.DrawRegisterDTO;
import com.bjike.goddess.secure.entity.DrawRegister;

/**
* 社保卡领取登记表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-09-25 05:55 ]
* @Description:	[ 社保卡领取登记表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DrawRegisterRep extends JpaRep<DrawRegister ,DrawRegisterDTO> { 

 }