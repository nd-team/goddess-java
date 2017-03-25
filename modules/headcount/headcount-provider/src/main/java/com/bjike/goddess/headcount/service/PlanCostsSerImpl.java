package com.bjike.goddess.headcount.service;

import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.bo.PlanCostsBO;
import com.bjike.goddess.headcount.bo.PlanCostsInteBO;
import com.bjike.goddess.headcount.dao.PlanCostsRep;
import com.bjike.goddess.headcount.dto.PlanCostsDTO;
import com.bjike.goddess.headcount.entity.PlanCosts;
import com.bjike.goddess.headcount.to.PlanCostsTO;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
* 计划人工成本信息业务实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T16:48:23.651 ]
* @Description:	[ 计划人工成本信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="planCostsSerCache")
@Service
public class PlanCostsSerImpl extends ServiceImpl<PlanCosts, PlanCostsDTO> implements PlanCostsSer {
     @Autowired
     private PlanCostsRep planCostsRep;

     @Override
     @Transactional(rollbackFor = SerException.class)
     public PlanCostsBO saveByTO(TransactionContext txContext, PlanCostsTO planCostsTO) throws SerException{
      PlanCosts planCosts = BeanTransform.copyProperties(planCostsTO,PlanCosts.class,true);
      super.save(planCosts);
      planCostsTO.setId(planCosts.getId());
      return BeanTransform.copyProperties(planCostsTO,PlanCostsBO.class);
     }

 @Override
 public List<PlanCostsBO> list()throws SerException{
  List<PlanCosts> planstCosts = super.findAll();
  List<PlanCostsBO> planCostsBOs = BeanTransform.copyProperties(planstCosts,PlanCostsBO.class);
  return planCostsBOs;
 }

 @Override
 public List<PlanCostsBO> findByModifyTime(String modifyTime)throws SerException{
  try{
   List<PlanCosts> planCosts = planCostsRep.findByModifyTime(modifyTime);
   return BeanTransform.copyProperties(planCosts,PlanCostsBO.class);
  }catch(RepException e){
   throw new SerException(e.getMessage());
  }
 }

 @Override
 public void update(PlanCostsTO planCostsTO )throws SerException{
  PlanCosts planCosts = super.findById(planCostsTO.getId());
  BeanTransform.copyProperties(planCostsTO,planCosts,true);
  planCosts.setModifyTime(LocalDateTime.now());
  super.update(planCosts);
 }

 /**
  * 根据id删除一条数据
  * @param id
  * @throws SerException
  */
 @Transactional(rollbackFor = SerException.class)
 public void  remove(String id) throws SerException{
  super.remove(id);
 }

 @Override
 public List<PlanCostsInteBO> queryAll()throws SerException{
      //super.findById();
      return null;
 }
}