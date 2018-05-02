package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.DayReportAPI;
import com.bjike.goddess.attendance.bo.DayReportBO;
import com.bjike.goddess.attendance.bo.DayReportCountBO;
import com.bjike.goddess.attendance.dto.DayReportDTO;
import com.bjike.goddess.attendance.to.GuidePermissionTO;
import com.bjike.goddess.attendance.vo.DayReportCountVO;
import com.bjike.goddess.attendance.vo.DayReportVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.taskallotment.api.TaskNodeAPI;
import com.bjike.goddess.taskallotment.to.CollectDataTO;
import com.bjike.goddess.taskallotment.vo.CollectDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

/**
 * 日报
 *
 * @Author: [chenjunhao]
 * @Date: [ 2017-10-07 10:28 ]
 * @Description: [ 日报 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("dayreport")
public class DayReportAction {
    @Autowired
    private DayReportAPI dayReportAPI;

    @Autowired
    private TaskNodeAPI taskNodeAPI;

    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = dayReportAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 日报列表
     *
     * @param dto dto
     * @return class DayReportVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(DayReportDTO dto, HttpServletRequest request) throws ActException {
        try {
            List<DayReportBO> list = dayReportAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DayReportVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 日报汇总
     *
     * @param dto dto
     * @return class DayReportCountVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/day/count")
    public Result dayCount(@Validated({DayReportDTO.COUNT.class}) DayReportDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            DayReportCountBO list = dayReportAPI.dayCount(dto);
            return ActResult.initialize(BeanTransform.copyProperties(list, DayReportCountVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 个人项目汇总
     *
     * @param collectDataTO collectDataTO
     * @return class CollectDataVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/person/collect")
    public Result personCollect( CollectDataTO collectDataTO, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            CollectDataVO list = taskNodeAPI.personProjectCollect( collectDataTO );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取所有年份
     *
     * @des 获取所有年份
     * @version v1
     */
    @GetMapping("v1/allYear")
    public Result listYear( ) throws ActException {
        try {
            LocalDate date = LocalDate.now();
            List<Integer> list = DateUtil.allYearList(date);
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 获取当前年月有几周
     *
     * @des 获取当前年月有几周
     * @version v1
     */
    @GetMapping("v1/weeks/{year}/{month}")
    public Result weeksByYearAndMonth(@PathVariable int year , @PathVariable int month) throws ActException {
        try {
            LocalDate date = LocalDate.now();
            int weekNum = DateUtil.getWeekNum(year, month);
            return ActResult.initialize(weekNum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前时间是第几周
     *
     * @des 获取当前时间是本年本月的第几周
     * @version v1
     */
    @GetMapping("v1/todayWeekNum")
    public Result todayWeekNum( ) throws ActException {
        try {
            LocalDate date = LocalDate.now();
            int weekNum = DateUtil.todayWeek( date );
            return ActResult.initialize(weekNum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取当前时间是第几季度
     *
     * @des 获取当前时间是本年的第几季度
     * @version v1
     */
    @GetMapping("v1/todayQuaryNum")
    public Result todayQuaryNum( ) throws ActException {
        try {
            LocalDate date = LocalDate.now();
            int quartNum = DateUtil.todayQuart( date );
            return ActResult.initialize(quartNum);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}