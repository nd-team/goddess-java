package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.exception.RepException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.dao.ContractQuoteDataRep;
import com.bjike.goddess.contractquotemanager.dto.ContractQuoteDataDTO;
import com.bjike.goddess.contractquotemanager.entity.ContractQuoteData;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
* 合同单价资料信息业务实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:01:53.323 ]
* @Description:	[ 合同单价资料信息业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="contractQuoteDataSerCache")
@Service
public class ContractQuoteDataSerImpl extends ServiceImpl<ContractQuoteData, ContractQuoteDataDTO> implements ContractQuoteDataSer { 
   @Autowired
   private ContractQuoteDataRep contractQuoteDataRep;

    @Transactional(rollbackFor = SerException.class)
   @Override
   public ContractQuoteDataBO save(ContractQuoteDataTO contractQuoteDataTO)throws SerException{
    ContractQuoteData contractQuoteData = BeanTransform.copyProperties(contractQuoteDataTO,ContractQuoteData.class,true);
    super.save(contractQuoteData);
    contractQuoteDataTO.setId(contractQuoteData.getId());
    return BeanTransform.copyProperties(contractQuoteDataTO,ContractQuoteDataBO.class,true);
   }

   @Override
   public List<ContractQuoteDataBO> list()throws SerException{
     List<ContractQuoteData> contractQuoteDatas = super.findAll();
     List<ContractQuoteDataBO> contractQuoteDataBOs = BeanTransform.copyProperties(contractQuoteDatas,ContractQuoteDataBO.class,true);
    return contractQuoteDataBOs;
   }

   @Transactional(rollbackFor = SerException.class)
   @Override
  public void update(ContractQuoteDataTO contractQuoteDataTO)throws SerException{
       ContractQuoteData contractQuoteData  = super.findById(contractQuoteDataTO.getId());
       BeanTransform.copyProperties(contractQuoteDataTO,contractQuoteData,true);
       contractQuoteData.setModifyTime(LocalDateTime.now());
       super.update(contractQuoteData);
    }

   @Transactional(rollbackFor = SerException.class)
   @Override
   public void remove(String id)throws SerException{
      super.remove(id);
     }
   @Override
    public List<ContractQuoteDataBO> findByArea(String area)throws SerException{
       try{
           List<ContractQuoteDataBO> contractQuoteDataBOs = contractQuoteDataRep.findByArea(area);
           return contractQuoteDataBOs;
       }catch(RepException e){
           throw new SerException(e.getMessage());
       }
   }

    @Override
    public List<ContractQuoteDataBO> findByCustomerName(String customerName)throws SerException{
        try{
            List<ContractQuoteDataBO> contractQuoteDataBOs = contractQuoteDataRep.findByCustomerName(customerName);
            return contractQuoteDataBOs;
        }catch(RepException e){
            throw new SerException(e.getMessage());
        }
    }


 }