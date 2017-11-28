package com.bjike.goddess.employeecontract.action.employeecontract;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.employeecontract.api.ContractCollectAPI;
import com.bjike.goddess.employeecontract.bo.ContractAreaCollectBO;
import com.bjike.goddess.employeecontract.vo.ContractAreaCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* 员工合同管理汇总
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-10 03:22 ]
* @Description:	[ 员工合同管理汇总 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("contractcollect")
public class ContractCollectAction {
    @Autowired
    private ContractCollectAPI contractCollectAPI;

    /**
     * 月汇总
     * @param year 汇总年份
     * @param month 汇总月份
     * @return class ContractAreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/monthCollect")
    public Result monthCollect(Integer year,Integer month) throws ActException{
        try {
            List<ContractAreaCollectBO> boList = contractCollectAPI.monthCollect(year,month);
            List<ContractAreaCollectVO> voList = BeanTransform.copyProperties(boList,ContractAreaCollectVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 累计汇总
     * @param endDate 截止日期
     * @return class ContractAreaCollectVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/allColelct")
    public Result allColelct(String endDate) throws ActException{
        try {
            List<ContractAreaCollectBO> boList = contractCollectAPI.allCollect(endDate);
            List<ContractAreaCollectVO> voList = BeanTransform.copyProperties(boList,ContractAreaCollectVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

 }