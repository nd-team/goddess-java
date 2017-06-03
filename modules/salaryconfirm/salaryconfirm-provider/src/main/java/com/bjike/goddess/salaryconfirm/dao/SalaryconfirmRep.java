package com.bjike.goddess.salaryconfirm.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salaryconfirm.dto.SalaryconfirmDTO;
import com.bjike.goddess.salaryconfirm.entity.Salaryconfirm;

/**
* 薪资核算确认持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-05-16 03:22 ]
* @Description:	[ 薪资核算确认持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryconfirmRep extends JpaRep<Salaryconfirm ,SalaryconfirmDTO> { 

 }