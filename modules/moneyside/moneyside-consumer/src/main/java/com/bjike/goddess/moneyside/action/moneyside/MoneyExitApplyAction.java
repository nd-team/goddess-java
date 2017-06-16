package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.MoneyExitApplyAPI;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyDTO;
import com.bjike.goddess.moneyside.to.MoneyExitApplyTO;
import com.bjike.goddess.moneyside.vo.MoneyExitApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 资金退出申请表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:37 ]
 * @Description: [ 资金退出申请表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("moneyexitapply")
public class MoneyExitApplyAction {
    @Autowired
    private MoneyExitApplyAPI moneyExitApplyAPI;

    /**
     * 资金退出申请表列表总条数
     *
     * @param moneyExitApplyDTO 资金退出申请表dto
     * @des 获取所有资金退出申请表总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(MoneyExitApplyDTO moneyExitApplyDTO) throws ActException {
        try {
            Long count = moneyExitApplyAPI.countMoneyExitApply(moneyExitApplyDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个资金退出申请表
     *
     * @param id
     * @return class MoneyExitApplyVO
     * @des 获取一个资金退出申请表
     * @version v1
     */
    @GetMapping("v1/fund/{id}")
    public Result fund(@PathVariable String id) throws ActException {
        try {
            MoneyExitApplyBO moneyExitApplyBO = moneyExitApplyAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(moneyExitApplyBO, MoneyExitApplyVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 资金退出申请表列表
     *
     * @param moneyExitApplyDTO 资金退出申请表dto
     * @return class MoneyExitApplyVO
     * @des 获取所有资金退出申请表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(MoneyExitApplyDTO moneyExitApplyDTO, HttpServletRequest request) throws ActException {
        try {
            List<MoneyExitApplyVO> moneyExitApplyVOS = BeanTransform.copyProperties
                    (moneyExitApplyAPI.findListMoneyExitApply(moneyExitApplyDTO), MoneyExitApplyVO.class, request);
            return ActResult.initialize(moneyExitApplyVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加资金退出申请表
     *
     * @param moneyExitApplyTO 资金退出申请表数据to
     * @return class MoneyExitApplyVO
     * @des 添加资金退出申请表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) MoneyExitApplyTO moneyExitApplyTO, BindingResult bindingResult) throws ActException {
        try {
            MoneyExitApplyBO moneyExitApplyBO = moneyExitApplyAPI.insertMoneyExitApply(moneyExitApplyTO);
            return ActResult.initialize(moneyExitApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑资金退出申请表
     *
     * @param moneyExitApplyTO 资金退出申请表数据to
     * @return class MoneyExitApplyVO
     * @des 编辑资金退出申请表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) MoneyExitApplyTO moneyExitApplyTO, BindingResult bindingResult) throws ActException {
        try {
            MoneyExitApplyBO moneyExitApplyBO = moneyExitApplyAPI.editMoneyExitApply(moneyExitApplyTO);
            return ActResult.initialize(moneyExitApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除资金退出申请表
     *
     * @param id 用户id
     * @des 根据用户id删除资金退出申请表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            moneyExitApplyAPI.removeMoneyExitApply(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核
     *
     * @param moneyExitApplyTO 审核数据to
     * @return class MoneyExitApplyVO
     * @des 审核
     * @version v1
     */
    @PostMapping("v1/audit")
    public Result audit(@Validated MoneyExitApplyTO moneyExitApplyTO, BindingResult bindingResult) throws ActException {
        try {
            MoneyExitApplyBO moneyExitApplyBO = moneyExitApplyAPI.audit(moneyExitApplyTO);
            return ActResult.initialize(moneyExitApplyBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}