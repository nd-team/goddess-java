package com.bjike.goddess.materialsummary.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.materialsummary.dto.MaterialClassifyYearSumDTO;
import com.bjike.goddess.materialsummary.entity.MaterialClassifyYearSum;

/**
* 物资分类年汇总持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ sunfengtao ]
* @Date:			[  2017-05-22 10:48 ]
* @Description:	[ 物资分类年汇总持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MaterialClassifyYearSumRep extends JpaRep<MaterialClassifyYearSum ,MaterialClassifyYearSumDTO> { 

 }