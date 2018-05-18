package com.bjike.goddess.salarymanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salarymanage.dto.SalaryTestCollectDTO;
import com.bjike.goddess.salarymanage.entity.SalaryTestCollect;

/**
* 薪资测试汇总表持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测试汇总表持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryTestCollectRep extends JpaRep<SalaryTestCollect ,SalaryTestCollectDTO> { 

 }