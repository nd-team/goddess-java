package com.bjike.goddess.archive.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.entity.ForeignStaffing;

/**
* 对外人员信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 03:09 ]
* @Description:	[ 对外人员信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ForeignStaffingRep extends JpaRep<ForeignStaffing ,ForeignStaffingDTO> { 

 }