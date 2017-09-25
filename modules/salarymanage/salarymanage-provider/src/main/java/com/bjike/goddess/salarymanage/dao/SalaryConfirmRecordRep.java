package com.bjike.goddess.salarymanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salarymanage.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.salarymanage.entity.SalaryConfirmRecord;

/**
* 招聘面谈薪资确认记录持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-15 02:20 ]
* @Description:	[ 招聘面谈薪资确认记录持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryConfirmRecordRep extends JpaRep<SalaryConfirmRecord ,SalaryConfirmRecordDTO> { 

 }