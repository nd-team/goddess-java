package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.PositionDetailUserDTO;
import com.bjike.goddess.organize.entity.PositionDetailUser;

/**
* 用户职位持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-14 02:33 ]
* @Description:	[ 用户职位持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PositionDetailUserRep extends JpaRep<PositionDetailUser ,PositionDetailUserDTO> { 

 }