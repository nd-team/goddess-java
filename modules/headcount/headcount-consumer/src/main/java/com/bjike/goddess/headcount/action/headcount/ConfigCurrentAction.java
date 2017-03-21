package com.bjike.goddess.headcount.action.headcount;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.api.ConfigCurrentAPI;
import com.bjike.goddess.headcount.to.ConfigCurrentTO;
import com.bjike.goddess.headcount.vo.ConfigCurrentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 当前部门人数配置
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-15T14:19:57.635 ]
* @Description:	[ 当前部门人数配置 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("headcount/configcurrent")
public class ConfigCurrentAction {
   @Autowired
   private ConfigCurrentAPI configCurrentAPI;

 /**
  * 添加当前部门人员配置
  *
  * @param configCurrentTO 当前部门人员配置信息
  * @Des 返回当前人员配置信息
  * @throws ActException
  */
   @PostMapping("v1/add")
   public Result add(ConfigCurrentTO configCurrentTO)throws ActException{
      try{
          return ActResult.initialize(configCurrentAPI.save(null,configCurrentTO));
      }catch(SerException e){
         throw new ActException(e.getMessage());
      }
   }

   @GetMapping("v1/list")
    public Result list()throws ActException{
        try{
            List<ConfigCurrentVO> configCurrentVOs = BeanTransform.copyProperties(configCurrentAPI.list(), ConfigCurrentVO.class);
            return ActResult.initialize(configCurrentVOs);
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
   }

    /**
     * 更新人工成本计划信息
     *
     * @param configCurrentTO 人工成本信息
     * @version v1
     * @throws ActException
     */
    @PostMapping("v1/edit")
    public Result edit(ConfigCurrentTO configCurrentTO)throws ActException{
        try{
            configCurrentAPI.update(configCurrentTO);
            return ActResult.initialize("edit SUCCESS");
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 通过id删除人工计划成本
     *
     * @param id 人工计划成本唯一标识
     */
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id)throws ActException{
        try{
            configCurrentAPI.remove(id);
            return ActResult.initialize("delete success");
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }


 }