package com.bjike.goddess.businessprojectmanage.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessprojectmanage.dto.BusinessContractProDTO;
import com.bjike.goddess.businessprojectmanage.entity.BusinessContractPro;

/**
* 项目合同基本信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-11 11:43 ]
* @Description:	[ 项目合同基本信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BusinessContractProRep extends JpaRep<BusinessContractPro ,BusinessContractProDTO> { 

 }