package com.bjike.goddess.accruedtax.action.accruedtax;

import com.bjike.goddess.accruedtax.api.PayTaxAPI;
import com.bjike.goddess.accruedtax.bo.PayTaxBO;
import com.bjike.goddess.accruedtax.dto.PayTaxDTO;
import com.bjike.goddess.accruedtax.to.PayTaxTO;
import com.bjike.goddess.accruedtax.vo.PayTaxVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.auth.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 应交税金
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-24 09:17 ]
 * @Description: [ 应交税金 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("paytax")
public class PayTaxAction {

    @Autowired
    private PayTaxAPI payTaxAPI;

    /**
     *  列表总条数
     *
     * @param payTaxDTO  应交税金信息dto
     * @des 获取所有应交税金信息总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(PayTaxDTO payTaxDTO) throws ActException {
        try {
            Long count = payTaxAPI.countPayTax(payTaxDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 应交税金列表
     *
     * @param payTaxDTO 应交税金信息dto
     * @param request 前端过滤参数
     * @des 获取所有应交税金信息
     * @return  class PayTaxVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result findListPayTax(PayTaxDTO payTaxDTO, BindingResult bindingResult,HttpServletRequest request) throws ActException {
        try {
            List<PayTaxVO> payTaxVOList = BeanTransform.copyProperties(
                    payTaxAPI.listPayTax(payTaxDTO), PayTaxVO.class, request);
            return ActResult.initialize(payTaxVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加应交税金
     *
     * @param payTaxTO 应交税金基本信息数据to
     * @des 添加应交税金
     * @return  class PayTaxVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addPayTax(@Validated PayTaxTO payTaxTO, BindingResult bindingResult) throws ActException {
        try {
            PayTaxBO payTaxBO1 = payTaxAPI.addPayTax(payTaxTO);
            return ActResult.initialize(BeanTransform.copyProperties(payTaxBO1,PayTaxVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑应交税金
     *
     * @param payTaxTO 应交税金基本信息数据bo
     * @des 编辑应交税金
     * @return  class PayTaxVO
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editPayTax(@Validated PayTaxTO payTaxTO) throws ActException {
        try {
            PayTaxBO payTaxBO1 = payTaxAPI.editPayTax(payTaxTO);
            return ActResult.initialize(BeanTransform.copyProperties(payTaxBO1,PayTaxVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除应交税金信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deletePayTax(@PathVariable String id) throws ActException {
        try {
            payTaxAPI.deletePayTax(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException("删除失败："+e.getMessage());
        }
    }

    /**
     * 分摊
     *
     * @param payTaxTO 应交税金基本信息数据to
     * @des 添加应交税金
     * @return  class PayTaxVO
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/split")
    public Result split(@Validated(PayTaxTO.TestSplit.class) PayTaxTO payTaxTO, BindingResult bindingResult) throws ActException {
        try {
            PayTaxBO payTaxBO1 = payTaxAPI.splitTax(payTaxTO);
            return ActResult.initialize(BeanTransform.copyProperties(payTaxBO1,PayTaxVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 根据公司汇总
     *
     * @param payTaxDTO 应交税金信息dto
     * @des 根据公司汇总
     * @return  class PayTaxVO
     * @version v1
     */
    @GetMapping("v1/ctCompany")
    public Result collectCom(PayTaxDTO payTaxDTO) throws ActException {
        try {
            List<PayTaxVO> payTaxVOList = BeanTransform.copyProperties(
                    payTaxAPI.collectCompany(payTaxDTO), PayTaxVO.class);
            return ActResult.initialize(payTaxVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 根据税种汇总
     *
     * @param payTaxDTO 应交税金信息dto
     * @des 根据税种汇总
     * @return  class PayTaxVO
     * @version v1
     */
    @GetMapping("v1/ctTaxType")
    public Result ctTaxType(PayTaxDTO payTaxDTO) throws ActException {
        try {
            List<PayTaxVO> payTaxVOList = BeanTransform.copyProperties(
                    payTaxAPI.collectTaxType(payTaxDTO), PayTaxVO.class);
            return ActResult.initialize(payTaxVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 所有公司
     *
     * @des 查找汇总所有公司
     * @version v1
     */
    @GetMapping("v1/listCompany")
    public Result listCompany( ) throws ActException {
        try {
            List<String> list = payTaxAPI.listCompany( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 所有税种
     *
     * @des 查找汇总所有税种
     * @version v1
     */
    @GetMapping("v1/listTaxType")
    public Result listTaxType( ) throws ActException {
        try {
            List<String> list = payTaxAPI.listTaxType( );
            return ActResult.initialize(list);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    
}