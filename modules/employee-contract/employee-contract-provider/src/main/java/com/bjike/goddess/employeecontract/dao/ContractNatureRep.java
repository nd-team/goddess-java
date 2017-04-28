package com.bjike.goddess.employeecontract.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.employeecontract.dto.ContractNatureDTO;
import com.bjike.goddess.employeecontract.entity.ContractNature;
import org.springframework.stereotype.Repository;

/**
* 合同性质持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ dengjunren ]
* @Date:			[  2017-04-21 01:58 ]
* @Description:	[ 合同性质持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Repository
public interface ContractNatureRep extends JpaRep<ContractNature ,ContractNatureDTO> {

 }