package com.bjike.goddess.staffwelfare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffwelfare.dto.PersonalFestivalDTO;
import com.bjike.goddess.staffwelfare.entity.PersonalFestival;

/**
* 个人节日持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-07 01:56 ]
* @Description:	[ 个人节日持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PersonalFestivalRep extends JpaRep<PersonalFestival ,PersonalFestivalDTO> { 

 }