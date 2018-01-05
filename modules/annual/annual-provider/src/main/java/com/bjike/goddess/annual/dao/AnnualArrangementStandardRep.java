package com.bjike.goddess.annual.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.annual.dto.AnnualArrangementStandardDTO;
import com.bjike.goddess.annual.entity.AnnualArrangementStandard;

/**
* 年假层级标准持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-27 04:33 ]
* @Description:	[ 年假层级标准持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AnnualArrangementStandardRep extends JpaRep<AnnualArrangementStandard ,AnnualArrangementStandardDTO> { 

 }