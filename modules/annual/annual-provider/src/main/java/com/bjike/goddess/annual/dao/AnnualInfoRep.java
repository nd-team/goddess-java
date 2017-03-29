package com.bjike.goddess.annual.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.annual.dto.AnnualInfoDTO;
import com.bjike.goddess.annual.entity.AnnualInfo;

/**
* 年假信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-27 03:30 ]
* @Description:	[ 年假信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface AnnualInfoRep extends JpaRep<AnnualInfo ,AnnualInfoDTO> { 

 }