package com.bjike.goddess.qualifications.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.entity.QualificationsInfo;

/**
* 资质信息管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 04:15 ]
* @Description:	[ 资质信息管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface QualificationsInfoRep extends JpaRep<QualificationsInfo ,QualificationsInfoDTO> { 

 }