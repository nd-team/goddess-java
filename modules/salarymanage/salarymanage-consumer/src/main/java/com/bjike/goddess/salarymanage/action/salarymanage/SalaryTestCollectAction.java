package com.bjike.goddess.salarymanage.action.salarymanage;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salarymanage.api.SalaryTestCollectAPI;
import com.bjike.goddess.salarymanage.bo.AreaBO;
import com.bjike.goddess.salarymanage.bo.ResultAreaBO;
import com.bjike.goddess.salarymanage.bo.SalaryTestCollectBO;
import com.bjike.goddess.salarymanage.dto.SalaryTestCollectDTO;
import com.bjike.goddess.salarymanage.to.SalaryTestCollectTO;
import com.bjike.goddess.salarymanage.vo.AreaVO;
import com.bjike.goddess.salarymanage.vo.SalaryTestCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
* 薪资测算汇总表
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@RestController
@RequestMapping("salarytestcollect")
public class SalaryTestCollectAction {
    @Autowired
    private SalaryTestCollectAPI salaryTestCollectAPI;


    /**
     * 列表
     * @param dto 查询条件
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/pageList")
    public Result pageList(SalaryTestCollectDTO dto) throws ActException{
        try {
            List<AreaBO> areaBOList = salaryTestCollectAPI.pageList(dto);
            for(AreaBO bo:areaBOList){
                bo.setId( UUID.randomUUID().toString());
            }
            List<AreaVO> voList = BeanTransform.copyProperties(areaBOList,AreaVO.class);
            return ActResult.initialize(voList);
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 薪资标准制定
     * @param to 制定内容
     * @throws ActException
     * @version v1
     */
    @PostMapping("v1/make")
    public Result make(@Validated(ADD.class) SalaryTestCollectTO to) throws ActException{
        try {
            salaryTestCollectAPI.makeSalary(to);
            return new ActResult("薪资标准制定成功");
        }catch (SerException e){
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id来查询单个薪资汇总
     *
     * @param id
     * @return class AreaVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/find/one/{id}")
    public Result findOne(@PathVariable String id) throws ActException {
        try {
            SalaryTestCollectBO bo = salaryTestCollectAPI.findOne(id);
            SalaryTestCollectVO vo = BeanTransform.copyProperties(bo, SalaryTestCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

 }