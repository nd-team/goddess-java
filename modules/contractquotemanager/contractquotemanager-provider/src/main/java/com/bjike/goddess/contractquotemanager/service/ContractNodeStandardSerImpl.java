package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractNodeStandard;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
* 合同节点标准信息业务实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:03:20.725 ]
* @Description:	[ 合同节点标准信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="contractNodeStandardSerCache")
@Service
public class ContractNodeStandardSerImpl extends ServiceImpl<ContractNodeStandard, ContractNodeStandardDTO> implements ContractNodeStandardSer {

 @Transactional(rollbackFor = SerException.class)
 @Override
 public ContractNodeStandardBO save(ContractNodeStandardTO contractNodeStandardTO)throws SerException{
  ContractNodeStandard contractNodeStandard = BeanTransform.copyProperties(contractNodeStandardTO,ContractNodeStandard.class,true);
  super.save(contractNodeStandard);
  contractNodeStandardTO.setId(contractNodeStandard.getId());
  return BeanTransform.copyProperties(contractNodeStandardTO,ContractNodeStandardBO.class,true);
 }

 @Override
 public List<ContractNodeStandardBO> list()throws SerException{
    List<ContractNodeStandard> contractNodeStandards = super.findAll();
    return BeanTransform.copyProperties(contractNodeStandards,ContractNodeStandardBO.class,true);
 }

 @Transactional(rollbackFor = SerException.class)
 @Override
 public void update(ContractNodeStandardTO contractNodeStandardTO)throws SerException{
   ContractNodeStandard contractNodeStandard =super.findById(contractNodeStandardTO.getId());
   BeanTransform.copyProperties(contractNodeStandardTO,contractNodeStandard,true);
   contractNodeStandard.setModifyTime(LocalDateTime.now());
   super.update(contractNodeStandard);
 }

 @Transactional(rollbackFor = SerException.class)
 @Override
 public void remove(String id)throws SerException{
   super.remove(id);
 }
 }