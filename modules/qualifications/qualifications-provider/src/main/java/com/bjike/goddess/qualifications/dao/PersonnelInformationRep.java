package com.bjike.goddess.qualifications.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.qualifications.dto.PersonnelInformationDTO;
import com.bjike.goddess.qualifications.entity.PersonnelInformation;

/**
* 人员信息资料持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 07:24 ]
* @Description:	[ 人员信息资料持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PersonnelInformationRep extends JpaRep<PersonnelInformation ,PersonnelInformationDTO> { 

 }