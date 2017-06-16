package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.EquityInvestAPI;
import com.bjike.goddess.moneyside.bo.EquityInvestBO;
import com.bjike.goddess.moneyside.dto.EquityInvestDTO;
import com.bjike.goddess.moneyside.to.EquityInvestTO;
import com.bjike.goddess.moneyside.vo.EquityInvestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 投资条件-股权投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:38 ]
 * @Description: [ 投资条件-股权投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("equityinvest")
public class EquityInvestAction {
    @Autowired
    private EquityInvestAPI equityInvestAPI;

    /**
     * 股权投资列表总条数
     *
     * @param equityInvestDTO 股权投资dto
     * @des 获取所有股权投资总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(EquityInvestDTO equityInvestDTO) throws ActException {
        try {
            Long count = equityInvestAPI.countEquityInvest(equityInvestDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个股权投资
     *
     * @param id
     * @return class EquityInvestVO
     * @des 获取一个股权投资
     * @version v1
     */
    @GetMapping("v1/form/{id}")
    public Result form(@PathVariable String id) throws ActException {
        try {
            EquityInvestBO equityInvestBO = equityInvestAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(equityInvestBO, EquityInvestVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 股权投资列表
     *
     * @param equityInvestDTO 股权投资dto
     * @return class EquityInvestVO
     * @des 获取所有股权投资
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(EquityInvestDTO equityInvestDTO, HttpServletRequest request) throws ActException {
        try {
            List<EquityInvestVO> equityInvestVOS = BeanTransform.copyProperties
                    (equityInvestAPI.findListEquityInvest(equityInvestDTO), EquityInvestVO.class, request);
            return ActResult.initialize(equityInvestVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加股权投资
     *
     * @param equityInvestTO 股权投资数据to
     * @return class EquityInvestVO
     * @des 添加股权投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) EquityInvestTO equityInvestTO, BindingResult bindingResult) throws ActException {
        try {
            EquityInvestBO equityInvestBO = equityInvestAPI.insertEquityInvest(equityInvestTO);
            return ActResult.initialize(equityInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑股权投资
     *
     * @param equityInvestTO 股权投资数据to
     * @return class EquityInvestVO
     * @des 编辑投资形式
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) EquityInvestTO equityInvestTO, BindingResult bindingResult) throws ActException {
        try {
            EquityInvestBO equityInvestBO = equityInvestAPI.editEquityInvest(equityInvestTO);
            return ActResult.initialize(equityInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除股权投资
     *
     * @param id 用户id
     * @des 根据用户id删除股权投资记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            equityInvestAPI.removeEquityInvest(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}