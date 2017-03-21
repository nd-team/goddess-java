package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractQuoteDataAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractQuoteDataBO;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import com.bjike.goddess.contractquotemanager.vo.ContractQuoteDataVO;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 合同单价资料信息
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:01:53.318 ]
* @Description:	[ 合同单价资料信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("contractquotemanager/contractquotedata")
public class ContractQuoteDataAction { 
   @Autowired
   private ContractQuoteDataAPI contractQuoteDataAPI;

 /**
  * 添加合同单价资料信息
  *
  * @param contractQuoteDataTO 合同单价资料信息
  * @Des 返回合同单价资料信息
  * @throws ActException
  * @version v1
  */
   @PostMapping("v1/add")
   public Result save(@Validated({ADD.class}) ContractQuoteDataTO contractQuoteDataTO)throws ActException{
    try{
         return ActResult.initialize(contractQuoteDataAPI.save(contractQuoteDataTO));
    }catch(SerException e){
       throw new ActException(e.getMessage());
    }
   }

    /**
     * 获取合同单价资料信息
     * @throws ActException
     * @version v1
     */
   @GetMapping("v1/list")
   public Result list()throws ActException{
    try{
       List<ContractQuoteDataVO> contractQuoteDataVOs = BeanTransform.copyProperties(
               contractQuoteDataAPI.list(),ContractQuoteDataVO.class,true);
       return ActResult.initialize(contractQuoteDataVOs);
    }catch(SerException e){
       throw new ActException(e.getMessage());
    }
   }

    /**
     * 编辑合同单价资料信息
     * @param contractQuoteDataTO 合同单价资料信息
     * @version v1
     * @throws ActException
     */
   @PostMapping("v1/edit")
   public Result update(@Validated({EDIT.class}) ContractQuoteDataTO contractQuoteDataTO)throws ActException{
    try{
       contractQuoteDataAPI.update(contractQuoteDataTO);
       return ActResult.initialize("edit success");
    }catch(SerException e){
       throw new ActException(e.getMessage());
    }
   }

    /**
     * 根据id删除合同单价资料信息
     * @param id 合同单价资料信息唯一标识
     * @version v1
     * @throws ActException
     */
   @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id)throws ActException{
       try{
            contractQuoteDataAPI.remove(id);
            return ActResult.initialize("delete success");
       }catch(SerException e){
            throw new ActException(e.getMessage());
       }
   }

    /**
     * 根据地区获取合同单价资料信息
     * @param area 地区
     * @Des 合同单价资料信息
     * @throws ActException
     * @version v1
     */
   @GetMapping("v1/findByArea/{area}")
   public Result findByArea(@PathVariable String area)throws ActException{
       try{
           List<ContractQuoteDataVO> contractQuoteDataVOs = BeanTransform.copyProperties(
                   contractQuoteDataAPI.findByArea(area),ContractQuoteDataVO.class,true
           );
           return ActResult.initialize(contractQuoteDataVOs);
       }catch(SerException e){
           throw new ActException(e.getMessage());
       }
   }

    /**
     * 根据客户名称获取合同单价信息资料
     * @param customerName 客户名称
     * @Des 合同单价信息资料
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findByCustomerName/{customerName}")
    public Result findByCustomerName(@PathVariable String customerName)throws ActException{
        try{
            List<ContractQuoteDataVO> contractQuoteDataVOs = BeanTransform.copyProperties(
                    contractQuoteDataAPI.findByCustomerName(customerName),ContractQuoteDataVO.class,true
            );
            return ActResult.initialize(contractQuoteDataVOs);
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }
 }