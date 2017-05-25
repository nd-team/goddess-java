package com.bjike.goddess.staffpay.action.staffpay;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.api.MoneyReadyAPI;
import com.bjike.goddess.staffpay.bo.CollectCompareBO;
import com.bjike.goddess.staffpay.bo.MoneyReadyBO;
import com.bjike.goddess.staffpay.dto.MoneyReadyDTO;
import com.bjike.goddess.staffpay.to.MoneyReadyTO;
import com.bjike.goddess.staffpay.vo.CollectCompareVO;
import com.bjike.goddess.staffpay.vo.MoneyReadyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金准备审核表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:03 ]
 * @Description: [ 资金准备审核表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moneyready")
public class MoneyReadyAction {

    @Autowired
    private MoneyReadyAPI moneyReadyAPI;

    /**
     * 资金准备审核表列表总条数
     *
     * @param moneyReadyDTO 资金准备审核表记录dto
     * @des 获取所有资金准备审核表
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MoneyReadyDTO moneyReadyDTO) throws ActException {
        try {
            Long count = moneyReadyAPI.countMoneyReady(moneyReadyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金准备审核表
     *
     * @param id
     * @return class MoneyReadyVO
     * @des 获取一个资金准备审核表
     * @version v1
     */
    @GetMapping("v1/money/{id}")
    public Result money(@PathVariable String id) throws ActException {
        try {
            MoneyReadyBO moneyReadyBO = moneyReadyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(moneyReadyBO, MoneyReadyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 资金准备审核表列表
     *
     * @param moneyReadyDTO 资金准备审核表记录dto
     * @return class MoneyReadyVO
     * @des 获取所有资金准备审核表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MoneyReadyDTO moneyReadyDTO, HttpServletRequest request) throws ActException {
        try {
            List<MoneyReadyVO> moneyReadyVOS = BeanTransform.copyProperties(
                    moneyReadyAPI.findListMoneyReady(moneyReadyDTO), MoneyReadyVO.class, request);
            return ActResult.initialize(moneyReadyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资金准备审核表
     *
     * @param moneyReadyTO 资金准备审核表to
     * @return class MoneyReadyVO
     * @des 添加资金准备审核表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MoneyReadyTO moneyReadyTO, BindingResult bindingResult) throws ActException {
        try {
            MoneyReadyBO moneyReadyBO = moneyReadyAPI.insertMoneyReady(moneyReadyTO);
            return ActResult.initialize(moneyReadyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资金准备审核表
     *
     * @param moneyReadyTO 资金准备审核表数据to
     * @return class MoneyReadyVO
     * @des 添加资金准备审核表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MoneyReadyTO moneyReadyTO, BindingResult bindingResult) throws ActException {
        try {
            MoneyReadyBO moneyReadyBO = moneyReadyAPI.editMoneyReady(moneyReadyTO);
            return ActResult.initialize(moneyReadyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除资金准备审核表
     *
     * @param id 用户id
     * @des 根据用户id删除资金准备审核表
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            moneyReadyAPI.removeMoneyReady(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 对比汇总
     *
     * @param month 月份
     * @return class CollectCompareVO
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result collect(@RequestParam Integer month) throws ActException {
        try {
            List<CollectCompareBO> collectCompareBOS = moneyReadyAPI.collectCompare(month);
            return ActResult.initialize(BeanTransform.copyProperties(collectCompareBOS, CollectCompareVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}