package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.CashInvestAPI;
import com.bjike.goddess.moneyside.bo.CashInvestBO;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.dto.CashInvestDTO;
import com.bjike.goddess.moneyside.dto.InvestFormDTO;
import com.bjike.goddess.moneyside.to.CashInvestTO;
import com.bjike.goddess.moneyside.to.InvestFormTO;
import com.bjike.goddess.moneyside.vo.CashInvestVO;
import com.bjike.goddess.moneyside.vo.InvestFormVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 投资条件-现金投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:40 ]
 * @Description: [ 投资条件-现金投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("cashinvest")
public class CashInvestAction {
    @Autowired
    private CashInvestAPI cashInvestAPI;

    /**
     * 现金投资列表总条数
     *
     * @param cashInvestDTO 现金投资dto
     * @des 获取所有现金投资总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CashInvestDTO cashInvestDTO) throws ActException {
        try {
            Long count = cashInvestAPI.countCashInvest(cashInvestDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个现金投资
     *
     * @param id
     * @return class CashInvestVO
     * @des 获取一个现金投资
     * @version v1
     */
    @GetMapping("v1/cash/{id}")
    public Result cash(@PathVariable String id) throws ActException {
        try {
            CashInvestBO cashInvestBO = cashInvestAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(cashInvestBO, CashInvestVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 现金投资列表
     *
     * @param cashInvestDTO 现金投资dto
     * @return class CashInvestVO
     * @des 获取所有现金投资
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CashInvestDTO cashInvestDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvestFormVO> marketInfoVOS = BeanTransform.copyProperties
                    (cashInvestAPI.findListCashInvest(cashInvestDTO), CashInvestVO.class, request);
            return ActResult.initialize(marketInfoVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加现金投资
     *
     * @param cashInvestTO 现金投资数据to
     * @return class CashInvestVO
     * @des 添加现金投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CashInvestTO cashInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CashInvestBO cashInvestBO = cashInvestAPI.insertCashInvest(cashInvestTO);
            return ActResult.initialize(cashInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑现金投资
     *
     * @param cashInvestTO 现金投资数据to
     * @return class CashInvestVO
     * @des 编辑现金投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CashInvestTO cashInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CashInvestBO cashInvestBO = cashInvestAPI.editCashInvest(cashInvestTO);
            return ActResult.initialize(cashInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除现金投资
     *
     * @param id 用户id
     * @des 根据用户id删除现金投资记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            cashInvestAPI.removeCashInvest(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}