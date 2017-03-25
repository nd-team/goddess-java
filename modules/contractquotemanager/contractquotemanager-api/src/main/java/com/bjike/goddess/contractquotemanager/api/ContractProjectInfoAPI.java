package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractquotemanager.bo.ContractProjectInfoBO;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractProjectInfoDTO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.to.ContractProjectInfoTO;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;

import java.util.List;

/**
* 合同项目基本信息(临时表存放数据商务模块获取数据)业务接口
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-21 07:18 ]
* @Description:	[ ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractProjectInfoAPI  {
 default ContractProjectInfoBO save(ContractProjectInfoTO contractProjectInfoTO)throws SerException {
  return null;
 }

 default List<ContractProjectInfoBO> list(ContractProjectInfoDTO contractProjectInfoDTO)throws SerException{
  return null;
 }
 }