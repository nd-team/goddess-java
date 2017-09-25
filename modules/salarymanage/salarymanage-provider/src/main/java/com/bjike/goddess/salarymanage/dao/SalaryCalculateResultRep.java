package com.bjike.goddess.salarymanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateResultDTO;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateResult;

/**
* 薪资测算结果持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryCalculateResultRep extends JpaRep<SalaryCalculateResult ,SalaryCalculateResultDTO> { 

 }