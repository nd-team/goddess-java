package com.bjike.goddess.proearlymarketcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.proearlymarketcost.bo.SalesCostInfoBO;
import com.bjike.goddess.proearlymarketcost.dto.SalesCostInfoDTO;
import com.bjike.goddess.proearlymarketcost.service.SalesCostInfoSer;
import com.bjike.goddess.proearlymarketcost.to.SalesCostInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 销售费用信息业务接口实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:36 ]
* @Description:	[ 销售费用信息业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salesCostInfoApiImpl")
public class SalesCostInfoApiImpl implements SalesCostInfoAPI  {
 @Autowired
 private SalesCostInfoSer salesCostInfoSer;

 @Override
 public SalesCostInfoBO save(SalesCostInfoTO salesCostInfoTO)throws SerException {
  return salesCostInfoSer.save(salesCostInfoTO);
 }

 @Override
 public  List<SalesCostInfoBO> list(SalesCostInfoDTO salesCostInfoDTO)throws SerException{
  return salesCostInfoSer.list(salesCostInfoDTO);
 }

 @Override
 public  void update(SalesCostInfoTO salesCostInfoTO)throws SerException{
    salesCostInfoSer.update(salesCostInfoTO);
 }

 @Override
 public  void remove(String id)throws SerException{
    salesCostInfoSer.remove(id);
 }

 @Override
 public List<SalesCostInfoBO> collect(SalesCostInfoBO salesCostInfoBO)throws SerException{
    return salesCostInfoSer.collect(salesCostInfoBO);
 }
 }