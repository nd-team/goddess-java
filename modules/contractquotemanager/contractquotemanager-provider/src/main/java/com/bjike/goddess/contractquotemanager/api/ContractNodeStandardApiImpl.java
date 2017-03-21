package com.bjike.goddess.contractquotemanager.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.service.ContractNodeStandardSer;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 合同节点标准信息业务接口实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:03:20.724 ]
* @Description:	[ 合同节点标准信息业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("contractNodeStandardApiImpl")
public class ContractNodeStandardApiImpl implements ContractNodeStandardAPI  {
   @Autowired
   private ContractNodeStandardSer contractNodeStandardSer;

   @Override
   public ContractNodeStandardBO save(ContractNodeStandardTO contractNodeStandardTO)throws SerException{
       return contractNodeStandardSer.save(contractNodeStandardTO);
   }

   @Override
   public List<ContractNodeStandardBO> list()throws SerException{
        return contractNodeStandardSer.list();
     }

   @Override
   public void update(ContractNodeStandardTO contractNodeStandardTO)throws SerException{
        contractNodeStandardSer.update(contractNodeStandardTO);
   }

   @Override
   public void remove(String id )throws SerException{
      contractNodeStandardSer.remove(id);
   }
 }