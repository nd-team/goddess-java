package com.bjike.goddess.regularization.action.regularization;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.regularization.api.TransferInfoAPI;
import com.bjike.goddess.regularization.bo.SummationBO;
import com.bjike.goddess.regularization.bo.TransferInfoBO;
import com.bjike.goddess.regularization.dto.TransferInfoDTO;
import com.bjike.goddess.regularization.entity.TransferInfo;
import com.bjike.goddess.regularization.service.TransferInfoSer;
import com.bjike.goddess.regularization.to.GuidePermissionTO;
import com.bjike.goddess.regularization.to.TransferInfoTO;
import com.bjike.goddess.regularization.vo.SummationVO;
import com.bjike.goddess.regularization.vo.TransferInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 转正人员信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-12 02:20 ]
 * @Description: [ 转正人员信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("transferinfo")
public class TransferInfoAction {
    @Autowired
    private TransferInfoAPI transferInfoAPI;

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

            Boolean isHasPermission = transferInfoAPI.guidePermission(guidePermissionTO);
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
     * 根据id查询转正人员信息
     *
     * @param id 转正人员信息唯一标识
     * @return class TransferInfoVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/transferInfo/{id}")
    public Result findById(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            TransferInfoBO bo = transferInfoAPI.getOne(id);
            TransferInfoVO vo = BeanTransform.copyProperties(bo, TransferInfoVO.class, request);
            return ActResult.initialize(vo);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 计算总数量
     *
     * @param dto 转正人员信息模块dto
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/count")
    public Result count(@Validated TransferInfoDTO dto, BindingResult result) throws ActException {
        try {
            Long count = transferInfoAPI.countTrans(dto);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 分页查询转正人员信息
     *
     * @param dto 转正人员信息dto
     * @return class TransferInfoVO
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/list")
    public Result list(@Validated TransferInfoDTO dto, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            List<TransferInfoBO> boList = transferInfoAPI.list(dto);
            List<TransferInfoVO> voList = BeanTransform.copyProperties(boList, TransferInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 跟进
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/followUp")
    public Result followUp(@Validated(value = {TransferInfoTO.testFollowUp.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.followUp(to);
            return new ActResult("followUp success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 福利模块考核
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/welfareAssess")
    public Result welfareAssess(@Validated(value = {TransferInfoTO.testWelfareAssess.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.welfareAssess(to);
            return new ActResult("welfareAssess success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 规划模块考察
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/planAssess")
    public Result planAssess(@Validated(value = {TransferInfoTO.testPlanAssess.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.planAssess(to);
            return new ActResult("planAssess success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 预算模块考察填写
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/budgetAssess")
    public Result budgetAssess(@Validated(value = {TransferInfoTO.testBudgetAssess.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.budgetAssess(to);
            return new ActResult("budgetAssess success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 模块负责人审核
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/moduleRespon")
    public Result moduleRespon(@Validated(value = {TransferInfoTO.testModuleRespon.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.moduleRespon(to);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 项目经理审核
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/projectManage")
    public Result projectManage(@Validated(value = {TransferInfoTO.testProjectManage.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.projectManage(to);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 总经理审核
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/genManage")
    public Result genManage(@Validated(value = {TransferInfoTO.testGenManage.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.genManage(to);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 面谈记录填写
     *
     * @param to 转正人员信息to
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/interview")
    public Result interview(@Validated(value = {TransferInfoTO.testInterview.class}) TransferInfoTO to, BindingResult result) throws ActException {
        try {
            transferInfoAPI.interview(to);
            return new ActResult("audit success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 转正管理日汇总
     *
     * @param date 日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/day")
    public Result summarizeDay(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = transferInfoAPI.summaDay(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 转正管理周汇总
     *
     * @param year 年份
     * @param month 月份
     * @param week 周期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/week")
    public Result summarizeDay(Integer year,Integer month,Integer week, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = transferInfoAPI.summaWeek(year,month,week);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 转正管理月汇总
     *
     * @param year 年份
     * @param month 月份
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/month")
    public Result summarizeMonth(Integer year,Integer month, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = transferInfoAPI.summaMonth(year,month);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 转正管理累计汇总
     *
     * @param date 截止日期
     * @return class SummationVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/summarize/total")
    public Result summarizeMonth(String date, HttpServletRequest request) throws ActException {
        try {
            List<SummationBO> boList = transferInfoAPI.summaTotal(date);
            List<SummationVO> voList = BeanTransform.copyProperties(boList, SummationVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}