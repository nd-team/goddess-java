package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.FunPowerWeightFactorAPI;
import com.bjike.goddess.customer.bo.CustomerWeightFirstFactorBO;
import com.bjike.goddess.customer.bo.FunPowerWeightFactorBO;
import com.bjike.goddess.customer.dto.CustomerWeightFirstFactorDTO;
import com.bjike.goddess.customer.dto.FunPowerWeightFactorDTO;
import com.bjike.goddess.customer.to.FunPowerWeightFactorTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.vo.FunPowerWeightFactorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 职权因素层权重
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:50 ]
 * @Description: [ 职权因素层权重 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("funpowerweight")
public class FunPowerWeightAction {
    @Autowired
    private FunPowerWeightFactorAPI funPowerWeightFactorAPI;

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

            Boolean isHasPermission = funPowerWeightFactorAPI.guidePermission(guidePermissionTO);
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
     * 职权因素层权重列表总条数
     *
     * @param funPowerWeightFactorDTO 职权因素层权重dto
     * @des 获取所有职权因素层权重总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws ActException {
        try {
            Long count = funPowerWeightFactorAPI.countFunPower(funPowerWeightFactorDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个职权因素层权重
     *
     * @param id  职权因素层权重id
     * @return class FunPowerWeightFactorVO
     * @des 一个职权因素层权重
     * @version v1
     */
    @GetMapping("v1/getOne/{id}")
    public Result findCustomerWeiOne(@PathVariable String id) throws ActException {
        try {

            FunPowerWeightFactorVO funPowerWeightFactorVO = BeanTransform.copyProperties(
                    funPowerWeightFactorAPI.getOneFunPower(id), FunPowerWeightFactorVO .class, true);
            return ActResult.initialize(funPowerWeightFactorVO);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 职权因素层权重设置列表
     *
     * @param funPowerWeightFactorDTO 职权因素层权重设置dto
     * @return class FunPowerWeightFactorVO
     * @des 获取所有职权因素层权重
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findList(FunPowerWeightFactorDTO funPowerWeightFactorDTO) throws ActException {
        try {

            List<FunPowerWeightFactorBO> funPowerWeightFactorBOS = funPowerWeightFactorAPI.listFunPower(funPowerWeightFactorDTO);
            List<FunPowerWeightFactorVO> funPowerWeightFactorVOS = BeanTransform.copyProperties(funPowerWeightFactorBOS, FunPowerWeightFactorVO.class);
            return ActResult.initialize(funPowerWeightFactorVOS);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 添加职权因素层权重
     *
     * @param customerWeightFirstFactorTO 职权因素层权重to
     * @return class FunPowerWeightFactorVO
     * @des 添加职权因素层权重
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addBussWeight(@Validated(ADD.class) FunPowerWeightFactorTO customerWeightFirstFactorTO, BindingResult bindingResult) throws ActException {
        try {

            FunPowerWeightFactorBO funPowerWeightFactorBO = funPowerWeightFactorAPI.addFunPower(customerWeightFirstFactorTO);
            return ActResult.initialize(BeanTransform.copyProperties(funPowerWeightFactorBO, FunPowerWeightFactorVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户权重一层因素层设置
     *
     * @param funPowerWeightFactorTO 客户权重一层因素层设置数据bo
     * @return class FunPowerWeightFactorVO
     * @des 添加客户权重一层因素层设置
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editBussWeight(@Validated(EDIT.class) FunPowerWeightFactorTO funPowerWeightFactorTO, BindingResult bindingResult) throws ActException {
        try {

            FunPowerWeightFactorBO funPowerWeightFactorBO = funPowerWeightFactorAPI.editFunPower(funPowerWeightFactorTO);
            return ActResult.initialize(BeanTransform.copyProperties(funPowerWeightFactorBO, FunPowerWeightFactorVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户权重一层因素层设置
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteBussWeight(@PathVariable String id) throws ActException {
        try {

            funPowerWeightFactorAPI.deleteFunPower(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException("删除失败：" + e.getMessage());
        }
    }
}