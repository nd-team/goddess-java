package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.CapitalInvestAPI;
import com.bjike.goddess.moneyside.bo.ApplyInvestBO;
import com.bjike.goddess.moneyside.bo.CapitalInvestBO;
import com.bjike.goddess.moneyside.dto.ApplyInvestDTO;
import com.bjike.goddess.moneyside.dto.CapitalInvestDTO;
import com.bjike.goddess.moneyside.to.ApplyInvestTO;
import com.bjike.goddess.moneyside.to.CapitalInvestTO;
import com.bjike.goddess.moneyside.vo.ApplyInvestVO;
import com.bjike.goddess.moneyside.vo.CapitalInvestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金投资
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:00 ]
 * @Description: [ 资金投资 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("capitalinvest")
public class CapitalInvestAction {
    @Autowired
    private CapitalInvestAPI capitalInvestAPI;

    /**
     * 资金投资列表总条数
     *
     * @param capitalInvestDTO 资金投资dto
     * @des 获取所有资金投资总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CapitalInvestDTO capitalInvestDTO) throws ActException {
        try {
            Long count = capitalInvestAPI.countCapitalInvest(capitalInvestDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金投资
     *
     * @param id
     * @return class CapitalInvestVO
     * @des 获取一个资金投资
     * @version v1
     */
    @GetMapping("v1/capital/{id}")
    public Result capital(@PathVariable String id) throws ActException {
        try {
            CapitalInvestBO capitalInvestBO = capitalInvestAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(capitalInvestBO, CapitalInvestVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金投资列表
     *
     * @param capitalInvestDTO 资金投资dto
     * @return class CapitalInvestVO
     * @des 获取所有资金投资
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CapitalInvestDTO capitalInvestDTO, HttpServletRequest request) throws ActException {
        try {
            List<CapitalInvestVO> capitalInvestVOS = BeanTransform.copyProperties
                    (capitalInvestAPI.findListCapitalInvest(capitalInvestDTO), CapitalInvestVO.class, request);
            return ActResult.initialize(capitalInvestVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资金投资
     *
     * @param capitalInvestTO 资金投资数据to
     * @return class CapitalInvestVO
     * @des 添加资金投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) CapitalInvestTO capitalInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CapitalInvestBO capitalInvestBO = capitalInvestAPI.insertCapitalInvest(capitalInvestTO);
            return ActResult.initialize(capitalInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资金投资
     *
     * @param capitalInvestTO 资金投资数据to
     * @return class CapitalInvestVO
     * @des 编辑资金投资
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) CapitalInvestTO capitalInvestTO, BindingResult bindingResult) throws ActException {
        try {
            CapitalInvestBO capitalInvestBO = capitalInvestAPI.editCapitalInvest(capitalInvestTO);
            return ActResult.initialize(capitalInvestBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除资金投资
     *
     * @param id 用户id
     * @des 根据用户id删除资金投资记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            capitalInvestAPI.removeCapitalInvest(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}