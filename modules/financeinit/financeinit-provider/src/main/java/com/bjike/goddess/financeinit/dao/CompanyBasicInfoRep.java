package com.bjike.goddess.financeinit.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.financeinit.dto.CompanyBasicInfoDTO;
import com.bjike.goddess.financeinit.entity.CompanyBasicInfo;

/**
* 公司基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ lijuntao ]
* @Date:			[  2017-10-10 02:08 ]
* @Description:	[ 公司基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CompanyBasicInfoRep extends JpaRep<CompanyBasicInfo ,CompanyBasicInfoDTO> { 

 }