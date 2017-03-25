package com.bjike.goddess.headcount.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.headcount.bo.PlanCostsBO;
import com.bjike.goddess.headcount.bo.PlanCostsInteBO;
import com.bjike.goddess.headcount.to.PlanCostsTO;
import org.mengyun.tcctransaction.api.TransactionContext;

import java.util.List;

/**
* 计划人工成本信息业务接口
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T16:48:23.646 ]
* @Description:	[ 计划人工成本信息业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface PlanCostsAPI  {

    default PlanCostsBO saveByTO(TransactionContext txContext, PlanCostsTO planCostsTO)throws SerException {
         return null;
    }

    default PlanCostsBO add()throws SerException{
         return null;
    }

    default List<PlanCostsBO> list()throws SerException{
          return null;
    }

    default List<PlanCostsBO> findByModifyTime(String modifyTime)throws SerException{
           return null;
    }
    default void update(PlanCostsTO planCostsTO)throws SerException{

    }

    default List<PlanCostsInteBO> queryAll()throws SerException{
        return null;
    }

 }