package com.bjike.goddess.headcount.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.headcount.bo.PlanCostsBO;
import com.bjike.goddess.headcount.bo.PlanCostsInteBO;
import com.bjike.goddess.headcount.service.PlanCostsSer;
import com.bjike.goddess.headcount.to.PlanCostsTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 计划人工成本信息业务接口实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T16:48:23.650 ]
* @Description:	[ 技能管理等级配置业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("planCostsApiImpl")
public class PlanCostsApiImpl implements PlanCostsAPI  {
 @Autowired
 private PlanCostsSer planCostsSer;

 @Override
 public PlanCostsBO saveByTO(TransactionContext txContext, PlanCostsTO planCostsTO)throws SerException {
  return planCostsSer.saveByTO(txContext,planCostsTO);
 }

 @Override
 public List<PlanCostsBO> list()throws SerException{
  return planCostsSer.list();
 }

 @Override
 public List<PlanCostsBO> findByModifyTime(String modifyTime)throws SerException{
  return planCostsSer.findByModifyTime(modifyTime);
 }


 @Override
 public void update(PlanCostsTO planCostsTO)throws SerException{
  planCostsSer.update(planCostsTO);
 }

 @Override
 public List<PlanCostsInteBO> queryAll()throws SerException{
        return planCostsSer.queryAll();
 }
 }