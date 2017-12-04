package com.bjike.goddess.salarymanage.action.salarymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salarymanage.api.SalaryCalculateResultAPI;
import com.bjike.goddess.salarymanage.bo.*;
import com.bjike.goddess.salarymanage.to.SalaryCalculateResultTO;
import com.bjike.goddess.salarymanage.vo.ResultAreaVO;
import com.bjike.goddess.salarymanage.vo.SalaryCalculateResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
* 薪资测算结果
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salarycalculateresult")
public class SalaryCalculateResultAction {

    @Autowired
    private SalaryCalculateResultAPI salaryCalculateResultAPI;


    /**
     * 列表
     * @return class ResultAreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList() throws ActException{
        try {
            List<ResultAreaBO> resultAreaBOS = salaryCalculateResultAPI.pageList();
//            for(ResultAreaBO bo:resultAreaBOS){
//                bo.setId( UUID.randomUUID().toString());
//            }
            List<ResultAreaVO> resultAreaVOS = BeanTransform.copyProperties(resultAreaBOS,ResultAreaVO.class);
            return ActResult.initialize(resultAreaVOS);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 制定等级份额
     * @param to 内容
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/makeShare")
    public Result makeShare(@Validated(ADD.class) SalaryCalculateResultTO to) throws ActException{
        try {
            salaryCalculateResultAPI.makeShare(to);
            return new ActResult("制定等级份额成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id来查询单个薪资测算结果
     *
     * @param id
     * @return class SalaryCalculateResultVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one/{id}")
    public Result findOne(@PathVariable String id) throws ActException {
        try {
            SalaryCalculateResultBO bo = salaryCalculateResultAPI.findOne(id);
            SalaryCalculateResultVO vo = BeanTransform.copyProperties(bo, SalaryCalculateResultVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
 }