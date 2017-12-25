package com.bjike.goddess.salarymanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.entity.SalaryBasic;

/**
* 薪资管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 09:50 ]
* @Description:	[ 薪资管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryBasicRep extends JpaRep<SalaryBasic ,SalaryBasicDTO> { 

 }