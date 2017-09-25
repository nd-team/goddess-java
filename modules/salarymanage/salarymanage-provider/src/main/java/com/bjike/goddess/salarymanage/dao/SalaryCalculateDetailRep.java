package com.bjike.goddess.salarymanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateDetail;

/**
* 薪资测算明细表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-16 10:45 ]
* @Description:	[ 薪资测算明细表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryCalculateDetailRep extends JpaRep<SalaryCalculateDetail ,SalaryCalculateDetailDTO> { 

 }