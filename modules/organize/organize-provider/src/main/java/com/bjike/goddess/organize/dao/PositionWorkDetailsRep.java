package com.bjike.goddess.organize.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.organize.dto.PositionWorkDetailsDTO;
import com.bjike.goddess.organize.entity.PositionWorkDetails;

/**
* 岗位工作明细表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-09-12 01:41 ]
* @Description:	[ 岗位工作明细表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PositionWorkDetailsRep extends JpaRep<PositionWorkDetails ,PositionWorkDetailsDTO> { 

 }