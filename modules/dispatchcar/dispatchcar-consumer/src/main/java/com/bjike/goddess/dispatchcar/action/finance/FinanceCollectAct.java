package com.bjike.goddess.dispatchcar.action.finance;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.to.AreaCollectTO;
import com.bjike.goddess.dispatchcar.to.DriverCollectTO;
import com.bjike.goddess.dispatchcar.to.FinanceCollectTO;
import com.bjike.goddess.dispatchcar.to.ProjectCollectTO;
import com.bjike.goddess.dispatchcar.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
     * @param endDate 结束时间
     * @return class FinanceCollectVO
     * @version v1
     */
    @GetMapping("v1/week")
    public Result weekCollect(String startDate, String endDate, HttpServletRequest request) throws ActException {
        try {
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.weekCollect(startDate, endDate), FinanceCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class FinanceCollectVO
     * @version v1
     */
    @GetMapping("v1/month")
    public Result monthCollect(Integer year, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.monthCollect(year, month), FinanceCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区汇总
     *
     * @param to 汇总条件
     * @return class FinanceCollectVO
     * @version v1
     */
    @GetMapping("v1/areacollect")
    public Result selectCollect(@Validated({AreaCollectTO.SelectCollect.class}) AreaCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FinanceCollectTO collectTO = BeanTransform.copyProperties(to, FinanceCollectTO.class);
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectCollect(collectTO), FinanceCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param to 汇总条件
     * @return class FinanceCollectVO
     * @version v1
     */
    @GetMapping("v1/groupcollect")
    public Result groupCollect(@Validated({ProjectCollectTO.SelectCollect.class}) ProjectCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FinanceCollectTO collectTO = BeanTransform.copyProperties(to, FinanceCollectTO.class);
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectCollect(collectTO), FinanceCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机汇总
     *
     * @param to 汇总条件
     * @return class FinanceCollectVO
     * @version v1
     */
    @GetMapping("v1/drivercollect")
    public Result drivercollect(@Validated({DriverCollectTO.SelectCollect.class}) DriverCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FinanceCollectTO collectTO = BeanTransform.copyProperties(to, FinanceCollectTO.class);
            List<FinanceCollectVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectCollect(collectTO), FinanceCollectVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 地区分析
     *
     * @param to 分析条件
     * @return class AreaAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/areaanalyze")
    public Result areaAnalyze(@Validated({AreaCollectTO.SelectCollect.class}) AreaCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FinanceCollectTO collectTO = BeanTransform.copyProperties(to, FinanceCollectTO.class);
            List<AreaAnalyzeVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectAnalyze(collectTO), AreaAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组分析
     *
     * @param to 分析条件
     * @return class ProjectAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/groupanalyze")
    public Result groupAnalyze(@Validated({ProjectCollectTO.SelectCollect.class}) ProjectCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FinanceCollectTO collectTO = BeanTransform.copyProperties(to, FinanceCollectTO.class);
            List<ProjectAnalyzeVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectAnalyze(collectTO), ProjectAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 司机分析
     *
     * @param to 分析条件
     * @return class DriverAnalyzeVO
     * @version v1
     */
    @GetMapping("v1/driveranalyze")
    public Result driverAnalyze(@Validated({DriverCollectTO.SelectCollect.class}) DriverCollectTO to, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            FinanceCollectTO collectTO = BeanTransform.copyProperties(to, FinanceCollectTO.class);
            List<DriverAnalyzeVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.selectAnalyze(collectTO), DriverAnalyzeVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询汇总详情
     *
     * @param id 出车记录id
     * @return class FinanceCollectVO
     * @version v1
     */
    @GetMapping("v1/detail/{id}")
    public Result findDetail(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            FinanceCollectVO vo = BeanTransform.copyProperties(dispatchCarInfoAPI.findCollectDetail(id), FinanceCollectVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}
