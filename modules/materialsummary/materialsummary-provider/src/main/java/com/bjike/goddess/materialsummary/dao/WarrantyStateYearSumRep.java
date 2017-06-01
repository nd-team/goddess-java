package com.bjike.goddess.materialsummary.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialsummary.dto.WarrantyStateYearSumDTO;
import com.bjike.goddess.materialsummary.entity.WarrantyStateYearSum;

/**
* 保修状态年汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 02:16 ]
* @Description:	[ 保修状态年汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WarrantyStateYearSumRep extends JpaRep<WarrantyStateYearSum ,WarrantyStateYearSumDTO> { 

 }