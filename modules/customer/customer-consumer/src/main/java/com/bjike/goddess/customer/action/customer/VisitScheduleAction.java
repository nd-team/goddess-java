package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.VisitScheduleAPI;
import com.bjike.goddess.customer.bo.VisitScheduleBO;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.entity.VisitSchedule;
import com.bjike.goddess.customer.vo.VisitScheduleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 拜访日程表
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 03:13 ]
 * @Description: [ 拜访日程表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("visitschedule")
public class VisitScheduleAction {
    @Autowired
    private VisitScheduleAPI visitScheduleAPI;
    /**
     * 拜访日程表
     *
     * @param visitScheduleDTO 客户基本信息dto
     * @return class CustomerBaseInfoVO
     * @des 获取所有客户基本信息
     * @version v1
     */
    @GetMapping("v1/listVisi")
    public Result findListVisi(VisitScheduleDTO visitScheduleDTO) throws ActException {
        try {
            VisitScheduleBO visitScheduleBO = visitScheduleAPI.findVisit(visitScheduleDTO);
            VisitScheduleVO visitScheduleVO = BeanTransform.copyProperties(visitScheduleBO,VisitScheduleVO.class);
            return ActResult.initialize(visitScheduleVO);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}