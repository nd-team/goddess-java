package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
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

  default List<ContractQuoteDataBO> list(ContractQuoteDataDTO contractQuoteDataDTO)throws SerException{
   return null;
  }

  default void update(ContractQuoteDataTO contractQuoteDataTO)throws SerException{

  }
  default void remove(String id)throws SerException{

  }
  /**
   * 冻结使用状态
   *
   * @param id id
   */
  default void congealStatus(String id) throws SerException {
    return;
  }

  ;

  /**
   * 解冻使用状态
   *
   * @param id id
   */
  default void thawStatus(String id) throws SerException {
    return;
  }

  default List<ContractQuoteDataBO> collect(ContractQuoteDataDTO dto)throws SerException{
    return null;
  }

  default List<ContractQuoteDataBO> searchs(ContractQuoteDataBO bo)throws SerException{
    return null;
  }

 }