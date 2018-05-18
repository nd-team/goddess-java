package com.bjike.goddess.salarymanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salarymanage.dto.SalaryManageCollectDTO;
import com.bjike.goddess.salarymanage.entity.SalaryManageCollect;

/**
* 薪资管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 09:36 ]
* @Description:	[ 薪资管理汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryManageCollectRep extends JpaRep<SalaryManageCollect ,SalaryManageCollectDTO> { 

 }