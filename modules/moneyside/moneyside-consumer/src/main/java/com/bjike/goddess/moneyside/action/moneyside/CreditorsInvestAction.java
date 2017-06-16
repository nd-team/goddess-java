package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.CreditorsInvestAPI;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.dto.CreditorsInvestDTO;
import com.bjike.goddess.moneyside.to.CreditorsInvestTO;
import com.bjike.goddess.moneyside.vo.CreditorsInvestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 投资条件-债权投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:33 ]
 * @Description: [ 投资条件-债权投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("creditorsinvest")
public class CreditorsInvestAction {
    @Autowired
    private CreditorsInvestAPI creditorsInvestAPI;

    /**
     * 债权投资列表总条数
     *
     * @param creditorsInvestDTO 债权投资dto
     * @des 获取所有债权投资总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CreditorsInvestDTO creditorsInvestDTO) throws ActException {
        try {
            Long count = creditorsInvestAPI.countCreditorsInvest(creditorsInvestDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个债权投资
     *
     * @param id
     * @return class CreditorsInvestVO
     * @des 获取一个债权投资
     * @version v1
     */
    @GetMapping("v1/creditors/{id}")
    public Result creditors(@PathVariable String id) throws ActException {
        try {
            CreditorsInvestBO creditorsInvestBO = creditorsInvestAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(creditorsInvestBO, CreditorsInvestVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 债权投资列表
     *
     * @param creditorsInvestDTO 债权投资dto
     * @return class CreditorsInvestVO
     * @des 获取所有债权投资
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CreditorsInvestDTO creditorsInvestDTO, HttpServletRequest request) throws ActException {
        try {
            List<CreditorsInvestVO> creditorsInvestVOS = BeanTransform.copyProperties
                    (creditorsInvestAPI.findListCreditorsInvest(creditorsInvestDTO), CreditorsInvestVO.class, request);
            return ActResult.initialize(creditorsInvestVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加债权投资
     *
     * @param creditorsInvestTO 债权投资数据to
     * @return class CreditorsInvestVO
     * @des 添加债权投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CreditorsInvestTO creditorsInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CreditorsInvestBO creditorsInvestBO = creditorsInvestAPI.insertCreditorsInvest(creditorsInvestTO);
            return ActResult.initialize(creditorsInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑债权投资
     *
     * @param creditorsInvestTO 债权投资数据to
     * @return class CreditorsInvestVO
     * @des 编辑债权投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CreditorsInvestTO creditorsInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CreditorsInvestBO creditorsInvestBO = creditorsInvestAPI.editCreditorsInvest(creditorsInvestTO);
            return ActResult.initialize(creditorsInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除债权投资
     *
     * @param id 用户id
     * @des 根据用户id删除债权投资记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            creditorsInvestAPI.removeCreditorsInvest(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}