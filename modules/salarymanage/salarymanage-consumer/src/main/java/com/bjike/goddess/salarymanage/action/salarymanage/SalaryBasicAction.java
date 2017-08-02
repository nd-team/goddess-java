package com.bjike.goddess.salarymanage.action.salarymanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.vo.AreaVO;
import com.bjike.goddess.organize.vo.HierarchyVO;
import com.bjike.goddess.organize.vo.OperateVO;
import com.bjike.goddess.salarymanage.api.SalaryBasicAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 薪资管理
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 09:50 ]
* @Description:	[ 薪资管理 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salarybasic")
public class SalaryBasicAction {
    @Autowired
    private SalaryBasicAPI salaryBasicAPI;

    /**
     * 查询所有地区
     * @return class AreaVO
     * @throws ActException
     */
    @GetMapping("v1/findArea")
    public Result findArea() throws ActException{
        try{
            List<AreaBO> list = salaryBasicAPI.findArea();
            List<AreaVO> areaVOS = BeanTransform.copyProperties(list,AreaVO.class);
            return ActResult.initialize(areaVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有未冻结的部门
     * @return class OperateVO
     * @throws ActException
     */
    @GetMapping("v1/thawOpinion/find")
    public Result findThawOpinion() throws ActException{
        try{
            List<OpinionBO> list = salaryBasicAPI.findThawOpinion();
            List<OperateVO> operateVOS = BeanTransform.copyProperties(list,OperateVO.class);
            return ActResult.initialize(operateVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询所有正常状态的体系
     * @return class HierarchyVO
     * @throws ActException
     */
    @GetMapping("v1/")
    public Result findStatus() throws ActException{
        try {
           List<HierarchyBO> list =  salaryBasicAPI.findStatus();
           List<HierarchyVO> hierarchyVOS = BeanTransform.copyProperties(list,HierarchyVO.class);
           return ActResult.initialize(hierarchyVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }



 }