package com.bjike.goddess.contractquotemanager.action.contractquotemanager;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractquotemanager.api.ContractNodeStandardAPI;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.entity.ContractNodeStandard;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;
import com.bjike.goddess.contractquotemanager.to.ContractQuoteDataTO;
import com.bjike.goddess.contractquotemanager.vo.ContractNodeStandardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 合同节点标准信息
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-20T17:03:20.722 ]
* @Description:	[ 合同节点标准信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("contractquotemanager/contractnodestandard")
public class ContractNodeStandardAction { 
  @Autowired
  private ContractNodeStandardAPI contractNodeStandardAPI;

 /**
  * 添加合同节点标准信息
  * @param contractNodeStandardTO 合同节点标准信息
  * @Des 返回合同节点标准信息
  * @throws ActException
  * @version v1
  */
  @PostMapping("v1/add")
  public Result save(@Validated({ADD.class}) ContractNodeStandardTO contractNodeStandardTO)throws ActException {
   try {
    return ActResult.initialize(contractNodeStandardAPI.save(contractNodeStandardTO));
   } catch (SerException e) {
    throw new ActException(e.getMessage());
   }
  }

 /**
  * 获取合同节点标准信息
  * @version v1
  * @throws ActException
  */
  @GetMapping("v1/list")
  public Result list()throws ActException{
   try{
       List<ContractNodeStandardVO> contractNodeStandardVOList =BeanTransform.copyProperties(
            contractNodeStandardAPI.list(), ContractNodeStandardVO.class,true);
    return ActResult.initialize(contractNodeStandardVOList);
   }catch(SerException e){
    throw new ActException(e.getMessage());
   }
 }

 /**
  * 编辑合同节点标准信息
  * @param contractNodeStandardTO 合同节点标准信息
  * @version v1
  * @throws ActException
  */
 @PostMapping("v1/edit")
 public Result update(@Validated({EDIT.class}) ContractNodeStandardTO contractNodeStandardTO)throws ActException{
  try{
   contractNodeStandardAPI.update(contractNodeStandardTO);
   return ActResult.initialize("edit success");
  }catch(SerException e){
   throw new ActException(e.getMessage());
  }
 }

 /**
  * 根据id删除合同节点标准信息
  *
  * @param id 合同节点标准信息
  * @version v1
  * @throws ActException
  */
 @DeleteMapping("v1/delete/{id}")
 public Result delete(@PathVariable String id)throws ActException{
  try{
   contractNodeStandardAPI.remove(id);
   return ActResult.initialize("delete success");
  }catch(SerException e){
   throw new ActException(e.getMessage());
  }
 }

}