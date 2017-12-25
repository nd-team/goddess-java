package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.InvestFormAPI;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.dto.InvestFormDTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.InvestFormTO;
import com.bjike.goddess.moneyside.vo.InvestFormVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 投资形式
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:28 ]
 * @Description: [ 投资形式 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("investform")
public class InvestFormAction {
    @Autowired
    private InvestFormAPI investFormAPI;
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

            Boolean isHasPermission = investFormAPI.guidePermission(guidePermissionTO);
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
     * 投资形式列表总条数
     *
     * @param investFormDTO 投资形式dto
     * @des 获取所有投资形式总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvestFormDTO investFormDTO) throws ActException {
        try {
            Long count = investFormAPI.countInvestForm(investFormDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个投资形式
     *
     * @param id
     * @return class InvestFormVO
     * @des 获取一个投资形式
     * @version v1
     */
    @GetMapping("v1/form/{id}")
    public Result form(@PathVariable String id) throws ActException {
        try {
            InvestFormBO investFormBO = investFormAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(investFormBO, InvestFormVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投资形式列表
     *
     * @param investFormDTO 投资形式dto
     * @return class InvestFormVO
     * @des 获取所有投资形式
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InvestFormDTO investFormDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvestFormVO> investFormVOS = BeanTransform.copyProperties
                    (investFormAPI.findListInvestForm(investFormDTO), InvestFormVO.class, request);
            return ActResult.initialize(investFormVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加投资形式
     *
     * @param investFormTO 投资形式数据to
     * @return class InvestFormVO
     * @des 添加投资形式
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) InvestFormTO investFormTO, BindingResult bindingResult) throws ActException {
        try {
            InvestFormBO investFormBO = investFormAPI.insertInvestForm(investFormTO);
            return ActResult.initialize(investFormBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑投资形式
     *
     * @param investFormTO 投资形式数据to
     * @return class InvestFormVO
     * @des 编辑投资形式
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) InvestFormTO investFormTO, BindingResult bindingResult) throws ActException {
        try {
            InvestFormBO investFormBO = investFormAPI.editInvestForm(investFormTO);
            return ActResult.initialize(investFormBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除投资形式
     *
     * @param id 用户id
     * @des 根据用户id删除投资形式记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            investFormAPI.removeInvestForm(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}