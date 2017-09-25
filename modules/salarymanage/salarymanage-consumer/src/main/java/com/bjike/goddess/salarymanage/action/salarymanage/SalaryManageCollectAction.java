package com.bjike.goddess.salarymanage.action.salarymanage;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salarymanage.api.SalaryManageCollectAPI;
import com.bjike.goddess.salarymanage.bo.ManageAreaBO;
import com.bjike.goddess.salarymanage.entity.SalaryManageCollect;
import com.bjike.goddess.salarymanage.vo.ManageAreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* 薪资管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 09:36 ]
* @Description:	[ 薪资管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salarymanagecollect")
public class SalaryManageCollectAction {
    @Autowired
    private SalaryManageCollectAPI salaryManageCollectAPI;


    /**
     * 日汇总
     * @param theDay 汇总时间
     * @return class ManageAreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/dayCollect")
    public Result dayCollect(String theDay) throws ActException{
        try {
            List<ManageAreaBO> boList = salaryManageCollectAPI.dayCollect(theDay);
            List<ManageAreaVO> voList = BeanTransform.copyProperties(boList,ManageAreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 周汇总
     * @param theWeek 汇总时间
     * @return class ManageAreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/weekCollect")
    public Result weekCollect(String theWeek) throws ActException{
        try {
            List<ManageAreaBO> boList = salaryManageCollectAPI.weekCollect(theWeek);
            List<ManageAreaVO> voList = BeanTransform.copyProperties(boList,ManageAreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     * @param theDay 汇总时间
     * @return class ManageAreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCollcet")
    public Result monthCollect(String theDay) throws ActException{
        try {
            List<ManageAreaBO> boList = salaryManageCollectAPI.monthCollect(theDay);
            List<ManageAreaVO> voList = BeanTransform.copyProperties(boList,ManageAreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 累计汇总
     * @param theDay 汇总时间
     * @return class ManageAreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allCollect")
    public Result allCollect(String theDay) throws ActException{
        try {
            List<ManageAreaBO> boList = salaryManageCollectAPI.allCollect(theDay);
            List<ManageAreaVO> voList = BeanTransform.copyProperties(boList,ManageAreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }






 }