package com.bjike.goddess.employeecontract.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.employeecontract.dto.ContractChangeInformationDTO;
import com.bjike.goddess.employeecontract.entity.ContractChangeInformation;

/**
* 合同变更信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-09 05:18 ]
* @Description:	[ 合同变更信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractChangeInformationRep extends JpaRep<ContractChangeInformation ,ContractChangeInformationDTO> { 

 }