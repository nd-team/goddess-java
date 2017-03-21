package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;

import java.util.List;

/**
* 合同单价资料信息业务接口
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:01:53.316 ]
* @Description:	[ 合同单价资料信息业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ContractQuoteDataAPI  {
  default ContractQuoteDataBO save(ContractQuoteDataTO contractQuoteDataTO)throws SerException{
    return null;
  }

  default List<ContractQuoteDataBO> list()throws SerException{
   return null;
  }

  default void update(ContractQuoteDataTO contractQuoteDataTO)throws SerException{

  }
  default void remove(String id)throws SerException{

  }

  default List<ContractQuoteDataBO> findByArea(String area)throws SerException{
    return null;
  }

  default List<ContractQuoteDataBO> findByCustomerName(String customerName)throws SerException{
      return null;
  }
 }