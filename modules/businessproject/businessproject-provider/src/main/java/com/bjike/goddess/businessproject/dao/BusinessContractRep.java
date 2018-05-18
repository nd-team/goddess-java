package com.bjike.goddess.businessproject.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.businessproject.dto.BusinessContractDTO;
import com.bjike.goddess.businessproject.entity.BusinessContract;

/**
* 商务项目合同持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ xiazhili ]
* @Date:			[  2017-10-19 11:36 ]
* @Description:	[ 商务项目合同持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface BusinessContractRep extends JpaRep<BusinessContract ,BusinessContractDTO> { 

 }