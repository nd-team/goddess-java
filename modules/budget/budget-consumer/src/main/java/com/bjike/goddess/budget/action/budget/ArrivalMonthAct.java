package com.bjike.goddess.budget.action.budget;

import com.bjike.goddess.budget.api.ArrivalMonthAPI;
import com.bjike.goddess.budget.bo.ArrivalMonthBO;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.dto.ArrivalMonthDTO;
import com.bjike.goddess.budget.vo.ArrivalMonthVO;
import com.bjike.goddess.budget.vo.ArrivalWeekVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 地区收入月
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:06 ]
 * @Description: [ 地区收入月 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("arrivalmonth")
public class ArrivalMonthAct {
    @Autowired
    private ArrivalMonthAPI arrivalMonthAPI;

    /**
     * 查找
     *
     * @param dto     地区收入月分页信息
     * @param request 请求对象
     * @return class ArrivalMonthVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ArrivalMonthDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<ArrivalMonthBO> list = arrivalMonthAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalMonthVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查找该月明细
     *
     * @param id      地区收入月id
     * @param request 请求对象
     * @return class ArrivalWeekVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findDetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<ArrivalWeekBO> list = arrivalMonthAPI.findDetail(id);
            return ActResult.initialize(BeanTransform.copyProperties(list, ArrivalWeekVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}