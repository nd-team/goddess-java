package com.bjike.goddess.royalty.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.royalty.dto.DepartmentBetDTO;
import com.bjike.goddess.royalty.entity.DepartmentBet;

/**
* 部门间对赌表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-07-12 02:16 ]
* @Description:	[ 部门间对赌表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DepartmentBetRep extends JpaRep<DepartmentBet ,DepartmentBetDTO> { 

 }