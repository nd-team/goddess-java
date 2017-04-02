package com.bjike.goddess.enterpriseculturemanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.enterpriseculturemanage.dto.EnterpriseCultureInfoDTO;
import com.bjike.goddess.enterpriseculturemanage.entity.EnterpriseCultureInfo;

/**
* 企业文化信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-03-31 05:05 ]
* @Description:	[ 企业文化信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface EnterpriseCultureInfoRep extends JpaRep<EnterpriseCultureInfo ,EnterpriseCultureInfoDTO> { 

 }