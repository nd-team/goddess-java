package com.bjike.goddess.proearlymarketcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.proearlymarketcost.bo.SalesCostInfoBO;
import com.bjike.goddess.proearlymarketcost.dto.SalesCostInfoDTO;
import com.bjike.goddess.proearlymarketcost.entity.SalesCostInfo;
import com.bjike.goddess.proearlymarketcost.to.SalesCostInfoTO;

import java.util.List;

/**
* 销售费用信息业务接口
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:36 ]
* @Description:	[ 销售费用信息业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalesCostInfoAPI  { 
  default SalesCostInfoBO save(SalesCostInfoTO salesCostInfoTO)throws SerException{
    return null;
  }
  default List<SalesCostInfoBO> list(SalesCostInfoDTO salesCostInfoDTO)throws SerException{
    return null;
  }
  default void update(SalesCostInfoTO salesCostInfoTO)throws SerException{

  }
  default void remove(String id)throws SerException{

  }

  default List<SalesCostInfoBO> collect(SalesCostInfoBO salesCostInfoBO)throws SerException{
    return null;
  }
 }