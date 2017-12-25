package com.bjike.goddess.qualifications.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.qualifications.dto.QualificationsGatherDTO;
import com.bjike.goddess.qualifications.entity.QualificationsGather;

/**
* 资质办理信息采集持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 04:33 ]
* @Description:	[ 资质办理信息采集持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface QualificationsGatherRep extends JpaRep<QualificationsGather ,QualificationsGatherDTO> { 

 }