package com.bjike.goddess.headcount.action.headcount;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.api.PlanCostsAPI;
import com.bjike.goddess.headcount.to.PlanCostsTO;
import com.bjike.goddess.headcount.vo.PlanCostsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 计划人工成本信息
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T16:48:23.648 ]
* @Description:	[ 计划人工成本信息 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("headcount/plancosts")
public class PlanCostsAction {
    @Autowired
    private PlanCostsAPI planCostsAPI;
 /**
  * 添加人工成本计划
  *
  * @param planCostsTO 人工成本计划信息
  * @des 返回人工成本计划信息
  * @version v1
  */
    @PostMapping("v1/add")
    public Result add(PlanCostsTO planCostsTO)throws ActException{
     try{
      return ActResult.initialize(planCostsAPI.saveByTO(null,planCostsTO));
     }catch(SerException e){
       throw new ActException(e.getMessage());
     }
    }
    @GetMapping("v1/list")
   public Result list()throws ActException{
    try{
      List<PlanCostsVO> planCostsVOList =BeanTransform.copyProperties(planCostsAPI.list(), PlanCostsVO.class);
      return ActResult.initialize(planCostsVOList);
    }catch(SerException e){
       throw new ActException(e.getMessage());
    }
   }
 /**
  * 更新人工成本计划信息
  *
  * @param planCostsTO 人工成本信息
  * @version v1
  * @throws ActException
  */
 @PostMapping("v1/update")
 public Result update(PlanCostsTO planCostsTO)throws ActException{
     try{
      planCostsAPI.update(planCostsTO);
      return ActResult.initialize("update SUCCESS");
     }catch(SerException e){
      throw new ActException(e.getMessage());
     }
    }
 }