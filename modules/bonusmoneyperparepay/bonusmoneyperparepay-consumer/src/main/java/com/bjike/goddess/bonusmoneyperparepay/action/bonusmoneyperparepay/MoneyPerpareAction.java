package com.bjike.goddess.bonusmoneyperparepay.action.bonusmoneyperparepay;

import com.bjike.goddess.bonusmoneyperparepay.api.MoneyPerpareAPI;
import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareContrastBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.PerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.MoneyPerpareDTO;
import com.bjike.goddess.bonusmoneyperparepay.to.MoneyPerpareTO;
import com.bjike.goddess.bonusmoneyperparepay.vo.MoneyPerpareContrastVO;
import com.bjike.goddess.bonusmoneyperparepay.vo.MoneyPerpareVO;
import com.bjike.goddess.bonusmoneyperparepay.vo.PerpareVO;
import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 奖金资金准备与支付
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备与支付 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moneyperpare")
public class MoneyPerpareAction {

    @Autowired
    private MoneyPerpareAPI moneyPerpareAPI;


    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    //@GetMapping("v1/guidePermission")
// public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
//  try {
//
//   Boolean isHasPermission = baseInfoManageAPI.guidePermission(guidePermissionTO);
//   if (!isHasPermission) {
//    //int code, String msg
//    return new ActResult(0, "没有权限", false);
//   } else {
//    return new ActResult(0, "有权限", true);
//   }
//  } catch (SerException e) {
//   throw new ActException(e.getMessage());
//  }
// }

    /**
     * 列表总条数
     *
     * @param moneyPerpareDTO 奖金资金准备
     * @des 获取所有奖金资金准备总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MoneyPerpareDTO moneyPerpareDTO) throws ActException {
        try {
            Long count = moneyPerpareAPI.countMoney(moneyPerpareDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个奖金资金准备
     *
     * @param id 奖金资金准备id
     * @return class MoneyPerpareVO
     * @des 根据id获取奖金资金准备
     * @version v1
     */
    @GetMapping("v1/getOneById/{id}")
    public Result getOneById(@PathVariable String id) throws ActException {
        try {
            MoneyPerpareVO moneyPerpareVO = BeanTransform.copyProperties(
                    moneyPerpareAPI.getOneById(id), MoneyPerpareVO.class);
            return ActResult.initialize(moneyPerpareVO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 奖金资金准备列表
     *
     * @param moneyPerpareDTO 奖金资金准备dto
     * @return class BaseInfoManageVO
     * @des 获取所有奖金资金准备
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListMoneyPerpare(MoneyPerpareDTO moneyPerpareDTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {
            List<MoneyPerpareVO> MoneyPerpareVOList = BeanTransform.copyProperties(
                    moneyPerpareAPI.listMoneyPerpare(moneyPerpareDTO), MoneyPerpareVO.class, request);
            return ActResult.initialize(MoneyPerpareVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加奖金资金准备
     *
     * @param moneyPerpareTO 奖金资金准备to
     * @return class BaseInfoManageVO
     * @des 添加奖金资金准备
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBaseInfoManage(@Validated({ADD.class}) MoneyPerpareTO moneyPerpareTO,BindingResult result) throws ActException {
        try {
            MoneyPerpareBO moneyPerpareBO = moneyPerpareAPI.addMoneyPerpare(moneyPerpareTO);
            return ActResult.initialize(BeanTransform.copyProperties(moneyPerpareBO, MoneyPerpareVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑奖金资金准备
     *
     * @param moneyPerpareTO 奖金资金准备数据bo
     * @return class BaseInfoManageVO
     * @des 编辑奖金资金准备
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result editBaseInfoManage(@Validated({EDIT.class}) MoneyPerpareTO moneyPerpareTO,BindingResult result) throws ActException {
        try {
            MoneyPerpareBO moneyPerpareBO = moneyPerpareAPI.editMoneyPerpare(moneyPerpareTO);
            return ActResult.initialize(BeanTransform.copyProperties(moneyPerpareBO, MoneyPerpareVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除奖金资金准备
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBaseInfoManage(@PathVariable String id) throws ActException {
        try {
            moneyPerpareAPI.deleteMoneyPerpare(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 项目组汇总
     *
     * @param projectGroup   部门/项目组
     * @param month 月份
     * @return class PerpareVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/proCompare")
    public Result proCompare(Integer month, String[] projectGroup, HttpServletRequest request) throws ActException {
        try {
            List<PerpareBO> boList = moneyPerpareAPI.projectCompare(month, projectGroup);
            List<PerpareVO> voList = BeanTransform.copyProperties(boList, PerpareVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 月汇总
     *
     * @param month 月份
     * @return class PerpareVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/monCompare")
    public Result monCompare(Integer month, HttpServletRequest request) throws ActException {
        try {
            List<PerpareBO> boList = moneyPerpareAPI.monthCompare(month);
            List<PerpareVO> voList = BeanTransform.copyProperties(boList, PerpareVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 年汇总
     *
     * @param years 年份
     * @return class PerpareVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/yeCompare")
    public Result yeCompare(Integer years, HttpServletRequest request) throws ActException {
        try {
            List<PerpareBO> boList = moneyPerpareAPI.yearsCompare(years);
            List<PerpareVO> voList = BeanTransform.copyProperties(boList, PerpareVO.class, request);
            return ActResult.initialize(voList);
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
            List<String> areas = new ArrayList<>();
            areas = moneyPerpareAPI.findAllProject();
            return ActResult.initialize(areas);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     *  资金准备对比
     *
     * @param month 月份
     * @return class MoneyPerpareContrastVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam Integer month) throws ActException {
        try {
            List<MoneyPerpareContrastBO> moneyPerpareContrastBOS = moneyPerpareAPI.contrastCompare(month);
            return ActResult.initialize(BeanTransform.copyProperties(moneyPerpareContrastBOS, MoneyPerpareContrastVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}