package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.MoneyExitApplyConfirmedAPI;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyBO;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyConfirmedBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyConfirmedDTO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApplyConfirmed;
import com.bjike.goddess.moneyside.vo.MoneyExitApplyConfirmedVO;
import com.bjike.goddess.moneyside.vo.MoneyExitApplyVO;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金退出申请确认表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:57 ]
 * @Description: [ 资金退出申请确认表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moneyexitapplyconfirmed")
public class MoneyExitApplyConfirmedAction {
    @Autowired
    private MoneyExitApplyConfirmedAPI moneyExitApplyConfirmedAPI;
    /**
     * 资金退出申请确认表列表总条数
     *
     * @param moneyExitApplyConfirmedDTO 资金退出申请确认表dto
     * @des 获取所有资金退出申请确认表总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO) throws ActException {
        try {
            Long count = moneyExitApplyConfirmedAPI.countMoneyExitApplyConfirmed(moneyExitApplyConfirmedDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金退出申请确认表
     *
     * @param id
     * @return class MoneyExitApplyConfirmedVO
     * @des 获取一个资金退出申请确认表
     * @version v1
     */
    @GetMapping("v1/fund/{id}")
    public Result fund(@PathVariable String id) throws ActException {
        try {
            MoneyExitApplyConfirmedBO moneyExitApplyConfirmedBO = moneyExitApplyConfirmedAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(moneyExitApplyConfirmedBO, MoneyExitApplyConfirmedVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金退出申请确认表列表
     *
     * @param moneyExitApplyConfirmedDTO 资金退出申请确认表dto
     * @return class MoneyExitApplyConfirmedVO
     * @des 获取所有资金退出申请确认表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO, HttpServletRequest request) throws ActException {
        try {
            List<MoneyExitApplyConfirmedVO> moneyExitApplyConfirmedVOS = BeanTransform.copyProperties
                    (moneyExitApplyConfirmedAPI.findListMoneyExitApplyConfirmed(moneyExitApplyConfirmedDTO), MoneyExitApplyConfirmedVO.class, request);
            return ActResult.initialize(moneyExitApplyConfirmedVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
    /**
     * 删除资金退出申请确认表
     *
     * @param id 用户id
     * @des 根据用户id删除资金退出申请确认表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            moneyExitApplyConfirmedAPI.removeMoneyExitApplyConfirmed(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}