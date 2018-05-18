package com.bjike.goddess.marketdevelopment.action.marketdevelopment;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.api.MonthSubjectProvincialAPI;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectProvincialDTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectProvincialADDTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectProvincialUpdateTO;
import com.bjike.goddess.marketdevelopment.vo.MonthMoneyProvincialVO;
import com.bjike.goddess.marketdevelopment.vo.MonthSubjectProvincialUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 月计划省市方向
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-07 10:29 ]
 * @Description: [ 月计划省市方向 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("monthsubjectprovincial")
public class MonthSubjectProvincialAction {
    @Autowired
    private MonthSubjectProvincialAPI monthSubjectProvincialAPI;

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

            Boolean isHasPermission = monthSubjectProvincialAPI.guidePermission(guidePermissionTO);
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
     * 列表
     *
     * @param dto 月计划省市方向数据传输对象
     * @return class MonthMoneyProvincialVO
     * @version v1
     */
    @GetMapping("v1/maps")
    public Result maps(MonthSubjectProvincialDTO dto, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthSubjectProvincialAPI.maps(dto), MonthMoneyProvincialVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加
     *
     * @param to 月计划省市方向传输对象
     * @version v1
     */
    @PostMapping("v1/save")
    public Result save(@Validated(ADD.class) MonthSubjectProvincialADDTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            monthSubjectProvincialAPI.save(to);
            return ActResult.initialize("add success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改月计划省市方向数据
     *
     * @param to 月计划省市方向传输对象
     * @version v1
     */
    @PutMapping("v1/update/{provincialAreaId}")
    public Result update(@Validated(EDIT.class) MonthSubjectProvincialUpdateTO to, BindingResult result, HttpServletRequest request) throws ActException {
        try {
            monthSubjectProvincialAPI.update(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除月计划省市方向数据
     *
     * @param provincialAreaId 月计划省市方向传输对象
     * @version v1
     */
    @DeleteMapping("v1/delete/{provincialAreaId}")
    public Result delete(@PathVariable String provincialAreaId, HttpServletRequest request) throws ActException {
        try {
            monthSubjectProvincialAPI.delete(provincialAreaId);
            return ActResult.initialize("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取月计划省市方向数据
     *
     * @param provincialAreaId 业务方向数据id
     * @return class MonthSubjectProvincialUpdateVO
     * @version v1
     */
    @GetMapping("v1/findById/{provincialAreaId}")
    public Result findById(@PathVariable String provincialAreaId, HttpServletRequest request) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(monthSubjectProvincialAPI.getById(provincialAreaId), MonthSubjectProvincialUpdateVO.class, request));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 获取总条数
     *
     * @version v1
     */
    @GetMapping("v1/getTotal")
    public Result getTotal(MonthSubjectProvincialDTO dto) throws ActException {
        try {
            return ActResult.initialize(monthSubjectProvincialAPI.getTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 冻结
     *
     * @version v1
     */
    @PutMapping("v1/congeal/{monthmoneyId}")
    public Result congeal(@PathVariable String monthmoneyId) throws ActException {
        try {
            monthSubjectProvincialAPI.congeal(monthmoneyId);
            return ActResult.initialize("冻结成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 解冻
     *
     * @version v1
     */
    @PutMapping("v1/thaw/{monthmoneyId}")
    public Result thaw(@PathVariable String monthmoneyId) throws ActException {
        try {
            monthSubjectProvincialAPI.thaw(monthmoneyId);
            return ActResult.initialize("解冻成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年月份获取实际金额
     *
     * @param year  年份
     * @param month 月份年
     * @version v1
     */
    @GetMapping("v1/find/actualMoney")
    public Result findActualMoney(@RequestParam String year, @RequestParam String month) throws ActException {
        try {
            return ActResult.initialize(monthSubjectProvincialAPI.findActualMoney(year, month));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据年月份和业务类型获取实际金额
     *
     * @param businessType 业务类型
     * @param year  年份
     * @param month 月份年
     * @version v1
     */
    @GetMapping("v1/find/actualMoney1")
    public Result findActualMoney1(@RequestParam String year, @RequestParam String month, @RequestParam String businessType) throws ActException {
        try {
            return ActResult.initialize(monthSubjectProvincialAPI.findActualMoney1(year, month,businessType));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}