package com.bjike.goddess.staffwelfare.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfare.entity.StaffBirthDayWelfare;

/**
* 员工生日福利记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-04-07 10:49 ]
* @Description:	[ 员工生日福利记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface StaffBirthDayWelfareRep extends JpaRep<StaffBirthDayWelfare ,StaffBirthDayWelfareDTO> { 

 }