package com.bjike.goddess.staffwelfaremanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.staffwelfaremanage.dto.StaffBirthDayWelfareDTO;
import com.bjike.goddess.staffwelfaremanage.entity.StaffBirthDayWelfare;

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