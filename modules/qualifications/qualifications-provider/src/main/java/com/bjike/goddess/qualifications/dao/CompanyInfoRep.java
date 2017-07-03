package com.bjike.goddess.qualifications.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.qualifications.dto.CompanyInfoDTO;
import com.bjike.goddess.qualifications.entity.CompanyInfo;

/**
* 公司基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-03-31 06:38 ]
* @Description:	[ 公司基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyInfoRep extends JpaRep<CompanyInfo ,CompanyInfoDTO> { 

 }