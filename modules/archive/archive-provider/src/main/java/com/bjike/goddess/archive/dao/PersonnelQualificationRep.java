package com.bjike.goddess.archive.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.archive.dto.PersonnelQualificationDTO;
import com.bjike.goddess.archive.entity.PersonnelQualification;

/**
* 人员资质持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-12 04:01 ]
* @Description:	[ 人员资质持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PersonnelQualificationRep extends JpaRep<PersonnelQualification ,PersonnelQualificationDTO> { 

 }