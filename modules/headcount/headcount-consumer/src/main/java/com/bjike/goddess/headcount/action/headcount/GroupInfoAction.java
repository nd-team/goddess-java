package com.bjike.goddess.headcount.action.headcount;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.headcount.api.GroupInfoAPI;
import com.bjike.goddess.headcount.bo.GroupInfoBO;
import com.bjike.goddess.headcount.entity.plancost.GroupInfo;
import com.bjike.goddess.headcount.to.GroupInfoTO;
import com.bjike.goddess.headcount.vo.GroupInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: [yewenbo]
 * @Date: [2017-03-13 15:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */

@RestController
@RequestMapping("headcount/group")
public class GroupAct {
    @Autowired
    private GroupInfoAPI groupInfoAPI;

    /**
     * 添加部门信息
     *
     * @param groupInfoTO 部门信息
     * @Des 部门信息
     * @throws ActException
     */
    @PostMapping("v1/add")
    public Result add(@Validated({ADD.class}) GroupInfoTO groupInfoTO)throws ActException{
        try{
            return ActResult.initialize(groupInfoAPI.saveByTO(null,groupInfoTO));
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取部门信息
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list()throws ActException{
        try{
            List<GroupInfoVO> groupInfoVOList = BeanTransform.copyProperties(groupInfoAPI.list(), GroupInfoVO.class);
            return ActResult.initialize(groupInfoVOList);
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据部门名称查询部门信息
     * @param name 部门名称
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findByPlanCost/{name}")
    public Result findByName(@PathVariable String name)throws ActException{
        try{
            GroupInfoBO groupInfoBO = groupInfoAPI.findByName(name);
            GroupInfoVO groupInfoVOs = BeanTransform.copyProperties(groupInfoBO,GroupInfoVO.class,true);
            //Boolean result = (null !=groupInfoAPI.findByName(name));
            return ActResult.initialize(groupInfoVOs);
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 修改部门信息
     *
     * @param groupInfoTO 部门信息
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result edit(@Validated({EDIT.class}) GroupInfoTO groupInfoTO)throws ActException{
        try{
            groupInfoAPI.update(groupInfoTO);
            return ActResult.initialize("edit success");
        }catch(SerException e){
            throw new ActException(e.getMessage());
        }
    }
}
