package com.bjike.goddess.dispatchcar.action.finance;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.to.FinanceCollectTO;
import com.bjike.goddess.dispatchcar.vo.FinanceAnalyzeVO;
import com.bjike.goddess.dispatchcar.vo.FinanceCollectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 财务出车汇总
 *
 * @Author: [Jason]
 * @Date: [17-4-17 上午9:05]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("finance")
public class FinanceCollectAct {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    /**
     * 周汇总
     *
     * @param startDate 开始时间
     * @param startDate 结束时间
     * @version v1
     */
    @GetMapping("v1/week")
    public Result weekCollect(String startDate, String endDate) throws ActException {
        try {
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.weekCollect(startDate, endDate), FinanceCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param month 月份
     * @version v1
     */
    @GetMapping("v1/month")
    public Result monthCollect(Integer year, Integer month) throws ActException {
        try {
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.monthCollect(year, month), FinanceCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param to 汇总条件
     * @version v1
     */
    @GetMapping("v1/area_collect")
    public Result selectCollect(@Validated({FinanceCollectTO.SelectCollect.class}) FinanceCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectCollect(to), FinanceCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param to 汇总条件
     * @version v1
     */
    @GetMapping("v1/group_collect")
    public Result groupCollect(@Validated({FinanceCollectTO.SelectCollect.class}) FinanceCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectCollect(to), FinanceCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机汇总
     *
     * @param to 汇总条件
     * @version v1
     */
    @GetMapping("v1/driver_collect")
    public Result drivercollect(@Validated({FinanceCollectTO.SelectCollect.class}) FinanceCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectCollect(to), FinanceCollectVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区分析
     *
     * @param to 汇总条件分析
     * @version v1
     */
    @GetMapping("v1/area_analyze")
    public Result areaAnalyze(@Validated({FinanceCollectTO.SelectCollect.class}) FinanceCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<FinanceAnalyzeVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectAnalyze(to), FinanceAnalyzeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组分析
     *
     * @param to 分析条件
     * @version v1
     */
    @GetMapping("v1/group_analyze")
    public Result groupAnalyze(@Validated({FinanceCollectTO.SelectCollect.class}) FinanceCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<FinanceAnalyzeVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectAnalyze(to), FinanceAnalyzeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机分析
     *
     * @param to 分析条件
     * @version v1
     */
    @GetMapping("v1/driver_analyze")
    public Result driverAnalyze(@Validated({FinanceCollectTO.SelectCollect.class}) FinanceCollectTO to, BindingResult bindingResult) throws ActException {
        try {
            List<FinanceAnalyzeVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectAnalyze(to), FinanceAnalyzeVO.class);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询汇总详情
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/findDetail/{id}")
    public Result findDetail(@PathVariable String id) throws ActException {
        try {
            FinanceCollectVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findCollectDetail(id), FinanceCollectVO.class);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
