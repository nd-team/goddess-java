package com.bjike.goddess.shareholdersmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.shareholdersmanage.dto.LogoutShareDTO;
import com.bjike.goddess.shareholdersmanage.entity.LogoutShare;

/**
* 注销股东持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-08-21 03:55 ]
* @Description:	[ 注销股东持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface LogoutShareRep extends JpaRep<LogoutShare ,LogoutShareDTO> { 

 }