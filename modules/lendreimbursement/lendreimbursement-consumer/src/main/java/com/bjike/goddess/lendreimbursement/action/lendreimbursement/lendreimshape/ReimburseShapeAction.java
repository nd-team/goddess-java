package com.bjike.goddess.lendreimbursement.action.lendreimbursement.lendreimshape;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseAuditLogAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimCompanyShapeDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseShapeDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.ReimburseTrendShapeDTO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.*;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 报销记录图形
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录图形 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("reimburseshape")
public class ReimburseShapeAction extends BaseFileAction {

    @Autowired
    private ReimburseRecordAPI reimburseRecordAPI;
    @Autowired
    private ReimburseAuditLogAPI reimburseAuditLogAPI;
    @Autowired
    private CategoryAPI categoryAPI;
    @Autowired
    private AccountAPI accountAPI;
    @Autowired
    private FileAPI fileAPI;
    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    @Autowired
    private ReimburseRecordAPI reimburseShapeAPI;


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

    /**
     * 汇总年和月和周和日数据
     *
     * @param reimburseShapeDTO 报销汇总条件
     * @return class ReimShapeAllVO
     * @des 汇总年和月和周和日数据
     * @version v1
     */
    @PostMapping("v1/monWeek/collect")
    public Result collectMonAndWeek( ReimburseShapeDTO reimburseShapeDTO, BindingResult bindingResult) throws ActException {
        //日/周/月/年每个人的报销的情况
        try {
            ReimShapeAllVO reimShapeVO = reimburseShapeAPI.collectSelfShape(reimburseShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
        //TODO 上面的要拆分
    }



    /**
     * 汇总任意两月的变化趋势图
     *
     * @param reimburseTrendShapeDTO 报销汇总条件
     * @return class ReimShapeMixVO
     * @des 汇总任意两月的变化趋势图如2017年9月和10月的报销情况变化趋势图
     * @version v1
     */
    @PostMapping("v1/trend/collect")
    public Result collectTrend(@Validated(ReimburseTrendShapeDTO.TESTCON.class) ReimburseTrendShapeDTO reimburseTrendShapeDTO, BindingResult bindingResult) throws ActException {
        //图四 XX（名字）2017年9月和10月的报销情况变化趋势图
        try {
            ReimShapeMixVO reimShapeVO = reimburseShapeAPI.collectSelfTrend(reimburseTrendShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总公司项目组时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO 报销汇总条件
     * @return class ReimCompanyShapeBarVO
     * @des 汇总公司项目组时间段内的特定指标统计图
     * @version v1
     */
    @GetMapping("v1/group/collectbar")
    public Result collectGroupBar( ReimCompanyShapeDTO reimCompanyShapeDTO, BindingResult bindingResult) throws ActException {
        //项目组/项目/地区/时间指标的统计表（特定指标统计表）
        try {
            ReimCompanyShapeBarVO reimShapeVO = reimburseShapeAPI.collectGroupBar(reimCompanyShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总公司项目名称时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO 报销汇总条件
     * @return class ReimCompanyShapeBarVO
     * @des 汇总公司项目名称时间段内的特定指标统计图
     * @version v1
     */
    @GetMapping("v1/project/collectbar")
    public Result collectProjectBar( ReimCompanyShapeDTO reimCompanyShapeDTO, BindingResult bindingResult) throws ActException {
        //项目组/项目/地区/时间指标的统计表（特定指标统计表）
        try {
            ReimCompanyShapeBarVO reimShapeVO = reimburseShapeAPI.collectProjectBar(reimCompanyShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总公司地区时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO 报销汇总条件
     * @return class ReimCompanyShapeBarVO
     * @des 汇总公司地区时间段内的特定指标统计图
     * @version v1
     */
    @GetMapping("v1/area/collectbar")
    public Result collectAreaBar( ReimCompanyShapeDTO reimCompanyShapeDTO, BindingResult bindingResult) throws ActException {
        //项目组/项目/地区/时间指标的统计表（特定指标统计表）
        try {
            ReimCompanyShapeBarVO reimShapeVO = reimburseShapeAPI.collectAreaBar(reimCompanyShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




}