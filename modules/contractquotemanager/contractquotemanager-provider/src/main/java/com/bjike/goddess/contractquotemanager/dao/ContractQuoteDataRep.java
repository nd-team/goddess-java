package com.bjike.goddess.contractquotemanager.dao;

import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;

import java.util.List;

/**
* 合同单价资料信息持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:01:53.323 ]
* @Description:	[ 合同单价资料信息持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractQuoteDataRep extends JpaRep<ContractQuoteData ,ContractQuoteDataDTO> { 

 }