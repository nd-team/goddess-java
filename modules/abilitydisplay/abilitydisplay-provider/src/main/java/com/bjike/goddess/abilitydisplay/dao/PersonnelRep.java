package com.bjike.goddess.abilitydisplay.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.abilitydisplay.dto.PersonnelDTO;
import com.bjike.goddess.abilitydisplay.entity.Personnel;

/**
* 人员组成持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ wanyi ]
* @Date:			[  2018-01-06 02:13 ]
* @Description:	[ 人员组成持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PersonnelRep extends JpaRep<Personnel ,PersonnelDTO> { 

 }