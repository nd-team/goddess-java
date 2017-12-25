package com.bjike.goddess.socialinsurance.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.socialinsurance.dto.SocialInsuranceDTO;
import com.bjike.goddess.socialinsurance.entity.SocialInsurance;

/**
* 社会保险管理持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-21 04:23 ]
* @Description:	[ 社会保险管理持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SocialInsuranceRep extends JpaRep<SocialInsurance ,SocialInsuranceDTO> { 

 }