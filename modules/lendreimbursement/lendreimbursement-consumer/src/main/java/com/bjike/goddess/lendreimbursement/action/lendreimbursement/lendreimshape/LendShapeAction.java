package com.bjike.goddess.lendreimbursement.action.lendreimbursement.lendreimshape;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.financeinit.api.AccountAPI;
import com.bjike.goddess.financeinit.api.CategoryAPI;
import com.bjike.goddess.lendreimbursement.api.ApplyLendAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseAuditLogAPI;
import com.bjike.goddess.lendreimbursement.api.ReimburseRecordAPI;
import com.bjike.goddess.lendreimbursement.dto.reimshape.*;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimCompanyMixShapeVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimShapeAllVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimShapeMixVO;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.ReimShapeVO;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import com.bjike.goddess.storage.api.FileAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 借款记录图形
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 借款记录图形 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("lendshape")
public class LendShapeAction extends BaseFileAction {

    @Autowired
    private ApplyLendAPI applyLendAPI;



    /**
     * 汇总个人年和月和周和日数据
     *
     * @param reimburseShapeDTO 借款汇总条件
     * @return class ReimShapeAllVO
     * @des 汇总年和月和周和日数据
     * @version v1
     */
    @PostMapping("v1/monWeek/collect")
    public Result collectMonAndWeek( ReimburseShapeDTO reimburseShapeDTO, BindingResult bindingResult) throws ActException {
        //日/周/月/年每个人的借款的情况
        try {
            ReimShapeAllVO reimShapeVO = applyLendAPI.collectSelfShape(reimburseShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }



    /**
     * 汇总个人任意两月的变化趋势图
     *
     * @param reimburseTrendShapeDTO 借款汇总条件
     * @return class ReimShapeMixVO
     * @des 汇总任意两月的变化趋势图如2017年9月和10月的借款情况变化趋势图
     * @version v1
     */
    @PostMapping("v1/trend/collect")
    public Result collectTrend(@Validated(ReimburseTrendShapeDTO.TESTCON.class) ReimburseTrendShapeDTO reimburseTrendShapeDTO, BindingResult bindingResult) throws ActException {
        //图四 XX（名字）2017年9月和10月的借款情况变化趋势图
        try {
            ReimShapeMixVO reimShapeVO = applyLendAPI.collectSelfTrend(reimburseTrendShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总公司项目组时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO 借款汇总条件
     * @return class ReimCompanyMixShapeVO
     * @des 汇总公司项目组时间段内的特定指标统计图
     * @version v1
     */
    @GetMapping("v1/group/collectbar")
    public Result collectGroupBar( ReimCompanyShapeDTO reimCompanyShapeDTO, BindingResult bindingResult) throws ActException {
        //项目组/项目/地区/时间指标的统计表（特定指标统计表）
        try {
            ReimCompanyMixShapeVO reimShapeVO = applyLendAPI.collectGroupBar(reimCompanyShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总公司项目名称时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO 借款汇总条件
     * @return class ReimCompanyMixShapeVO
     * @des 汇总公司项目名称时间段内的特定指标统计图
     * @version v1
     */
    @GetMapping("v1/project/collectbar")
    public Result collectProjectBar( ReimCompanyShapeDTO reimCompanyShapeDTO, BindingResult bindingResult) throws ActException {
        //项目组/项目/地区/时间指标的统计表（特定指标统计表）
        try {
            ReimCompanyMixShapeVO reimShapeVO = applyLendAPI.collectProjectBar(reimCompanyShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总公司地区时间段内的特定指标统计图
     *
     * @param reimCompanyShapeDTO 借款汇总条件
     * @return class ReimCompanyMixShapeVO
     * @des 汇总公司地区时间段内的特定指标统计图
     * @version v1
     */
    @GetMapping("v1/area/collectbar")
    public Result collectAreaBar( ReimCompanyShapeDTO reimCompanyShapeDTO, BindingResult bindingResult) throws ActException {
        //项目组/项目/地区/时间指标的统计表（特定指标统计表）
        try {
            ReimCompanyMixShapeVO reimShapeVO = applyLendAPI.collectAreaBar(reimCompanyShapeDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 详细图之汇总公司地区时间段内的特定指标统计图
     *
     * @param lendShapeDetailDTO 借款汇总条件
     * @return class ReimShapeVO
     * @des 详细图之汇总公司地区时间段内的特定指标统计图
     * @version v1
     */
    @GetMapping("v1/detail/collectbar")
    public Result collectCompanyDetailBar(LendShapeDetailDTO lendShapeDetailDTO, BindingResult bindingResult) throws ActException {
        //项目组/项目/地区/时间指标的统计表（特定指标统计表）
        try {
            ReimShapeVO reimShapeVO = applyLendAPI.collectAreaDetailBar(lendShapeDetailDTO);
            return ActResult.initialize(reimShapeVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }




}