package com.bjike.goddess.dimission.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.dimission.dto.DimissionInfoDTO;
import com.bjike.goddess.dimission.entity.DimissionInfo;

/**
* 离职信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-17 02:12 ]
* @Description:	[ 离职信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DimissionInfoRep extends JpaRep<DimissionInfo ,DimissionInfoDTO> { 

 }