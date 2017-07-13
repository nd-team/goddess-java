package com.bjike.goddess.bonusmoneyperparepay.action.bonusmoneyperparepay;

import com.bjike.goddess.bonusmoneyperparepay.api.WaitingPayAPI;
import com.bjike.goddess.bonusmoneyperparepay.bo.PerpareActualDifferencesBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.excel.SonPermissionObject;
import com.bjike.goddess.bonusmoneyperparepay.to.GuidePermissionTO;
import com.bjike.goddess.bonusmoneyperparepay.vo.PerpareActualDifferencesVO;
import com.bjike.goddess.bonusmoneyperparepay.vo.WaitingPayVO;
import com.bjike.goddess.bonusmoneyperparepay.vo.WaitingVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.action.BaseFileAction;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.UserSetPermissionAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 付款
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 付款 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("waitingpay")
public class WaitingPayAct extends BaseFileAction {

    @Autowired
    private WaitingPayAPI waitingPayAPI;

    @Autowired
    private UserSetPermissionAPI userSetPermissionAPI;

    /**
     * 模块设置导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/setButtonPermission")
    public Result setButtonPermission() throws ActException {
        List<SonPermissionObject> list = new ArrayList<>();
        try {
            SonPermissionObject obj = new SonPermissionObject();
            obj.setName("cuspermission");
            obj.setDescribesion("设置");
            Boolean isHasPermission = userSetPermissionAPI.checkSetPermission();
            if (!isHasPermission) {
                //int code, String msg
                obj.setFlag(false);
            } else {
                obj.setFlag(true);
            }
            list.add(obj);
            return new ActResult(0, "设置权限", list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 下拉导航权限
     *
     * @throws ActException
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/sonPermission")
    public Result sonPermission() throws ActException {
        try {

            List<SonPermissionObject> hasPermissionList = waitingPayAPI.sonPermission();
            return new ActResult(0, "有权限", hasPermissionList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

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

            Boolean isHasPermission = waitingPayAPI.guidePermission(guidePermissionTO);
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
     * 等待付款列表总条数
     *
     * @param waitingPayDTO 等待付款
     * @des 获取所有等待付款总条数
     * @version v1
     */
    @GetMapping("v1/countWaiting")
    public Result countWaiting(WaitingPayDTO waitingPayDTO) throws ActException {
        try {
            Long count = waitingPayAPI.countWaiting(waitingPayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款列表总条数
     *
     * @param waitingPayDTO 已付款
     * @des 获取所有已付款总条数
     * @version v1
     */
    @GetMapping("v1/countAlready")
    public Result countAlready(WaitingPayDTO waitingPayDTO) throws ActException {
        try {
            Long count = waitingPayAPI.countAlready(waitingPayDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个等待付款
     *
     * @param id 等待付款id
     * @return class WaitingPayVO
     * @des 根据id获取等待付款
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            WaitingPayVO waitingPayVO = BeanTransform.copyProperties(
                    waitingPayAPI.getOneById(id), WaitingPayVO.class);
            return ActResult.initialize(waitingPayVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款列表
     *
     * @param waitingPayDTO 等待付款dto
     * @return class WaitingPayVO
     * @des 获取所有等待付款
     * @version v1
     */
    @GetMapping("v1/listWaiting")
    public Result findListWaiting(WaitingPayDTO waitingPayDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<WaitingPayVO> waitingPayVOS = BeanTransform.copyProperties(
                    waitingPayAPI.listWaiting(waitingPayDTO), WaitingPayVO.class, request);
            return ActResult.initialize(waitingPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款列表
     *
     * @param waitingPayDTO 等待付款dto
     * @return class WaitingPayVO
     * @des 获取所有已付款数据
     * @version v1
     */
    @GetMapping("v1/listAlready")
    public Result findListAlready(WaitingPayDTO waitingPayDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<WaitingPayVO> waitingPayVOS = BeanTransform.copyProperties(
                    waitingPayAPI.list(waitingPayDTO), WaitingPayVO.class, request);
            return ActResult.initialize(waitingPayVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除等待付款
     *
     * @param id id
     * @des 根据id删除等待付款
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBaseInfoManage(@PathVariable String id) throws ActException {
        try {
            waitingPayAPI.deleteWaiting(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款项目组汇总
     *
     * @param projectGroup 部门
     * @param month 月份
     * @return class WaitingVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/proCompare")
    public Result proCompare(Integer years, Integer month, String[] projectGroup, HttpServletRequest request) throws ActException {
        try {
            List<WaitingBO> boList = waitingPayAPI.projectCompare(years, month, projectGroup);
            List<WaitingVO> voList = BeanTransform.copyProperties(boList, WaitingVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款月汇总
     *
     * @param month 月份
     * @return class WaitingVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/monCompare")
    public Result monCompare(Integer years, Integer month, HttpServletRequest request) throws ActException {
        try {
            List<WaitingBO> boList = waitingPayAPI.monthCompare(years, month);
            List<WaitingVO> voList = BeanTransform.copyProperties(boList, WaitingVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 已付款年汇总
     *
     * @param years 年份
     * @return class WaitingVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/yeCompare")
    public Result yeCompare(Integer years, HttpServletRequest request) throws ActException {
        try {
            List<WaitingBO> boList = waitingPayAPI.yearsCompare(years);
            List<WaitingVO> voList = BeanTransform.copyProperties(boList, WaitingVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 等待付款导出Excel
     *
     * @param startMonth 月份
     * @param endMonth 月份
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportExcel")
    public Result exportExcel(Integer years,Integer startMonth, Integer endMonth, HttpServletResponse response) throws ActException {
        try {
            String fileName = "等待付款.xlsx";
            super.writeOutFile(response, waitingPayAPI.exportExcel(years,startMonth, endMonth), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 已付款导出Excel
     *
     * @param startMonth 月份
     * @param endMonth 月份
     * @version v1
     */
    @LoginAuth
    @GetMapping("v1/exportAlreadyExcel")
    public Result exportAlreadyExcel(Integer years,Integer startMonth, Integer endMonth, HttpServletResponse response) throws ActException {
        try {
            String fileName = "已付款.xlsx";
            super.writeOutFile(response, waitingPayAPI.exportArealdyExcel(years,startMonth, endMonth), fileName);
            return new ActResult("导出成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        } catch (IOException e1) {
            throw new ActException(e1.getMessage());
        }
    }

    /**
     * 支付
     *
     * @param payMoney 支付金额
     * @des 支付奖金
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/payMoney/{id}")
    public Result payMoney(@PathVariable String id, @RequestParam Double payMoney) throws ActException {
        try {
            waitingPayAPI.payMoney(id, payMoney);
            return new ActResult("executiveOpinion success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金准备与实际支付差异
     *
     * @param month 月份
     * @return class PerpareActualDifferencesVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collectActual")
    public Result collect(Integer years, @RequestParam Integer month) throws ActException {
        try {
            List<PerpareActualDifferencesBO> moneyPerpareContrastBOS = waitingPayAPI.differencesCompare(years, month);
            return ActResult.initialize(BeanTransform.copyProperties(moneyPerpareContrastBOS, PerpareActualDifferencesVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 查询所有项目组
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/findProjectGroup")
    public Result findProjectGroup() throws ActException {
        try {
            List<String> projectGroup = new ArrayList<>();
            projectGroup = waitingPayAPI.findAllProject();
            return ActResult.initialize(projectGroup);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}