package com.bjike.goddess.archive.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.entity.ForeignStaffingSet;

/**
* 对外人员基本信息设置持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 02:28 ]
* @Description:	[ 对外人员基本信息设置持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ForeignStaffingSetRep extends JpaRep<ForeignStaffingSet ,ForeignStaffingSetDTO> { 

 }