package com.bjike.goddess.dispatchcar.action.dispatchcar;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.dispatchcar.api.DispatchCarInfoAPI;
import com.bjike.goddess.dispatchcar.dto.DispatchCarInfoDTO;
import com.bjike.goddess.dispatchcar.enums.FindType;
import com.bjike.goddess.dispatchcar.to.DispatchCarInfoTO;
import com.bjike.goddess.dispatchcar.vo.AuditResultVO;
import com.bjike.goddess.dispatchcar.vo.DispatchCarInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 等待付款
 *
 * @Author: [Jason]
 * @Date: [17-4-14 下午2:35]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@RestController
@RequestMapping("waitpay")
public class WaitPayAct {

    @Autowired
    private DispatchCarInfoAPI dispatchCarInfoAPI;

    /**
     * 列表分页查询
     *
     * @param dto 分页条件
     * @return class DispatchCarInfoVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result pageList(DispatchCarInfoDTO dto, HttpServletRequest request) throws ActException {
        try {
            dto.getConditions().add(Restrict.eq("findType", FindType.WAITPAY));
            List<DispatchCarInfoVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.pageList(dto), DispatchCarInfoVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 审核详情
     *
     * @param id 出车记录id
     * @return class AuditResultVO
     * @version v1
     */
    @GetMapping("v1/audit/{id}")
    public Result findAudit(@PathVariable String id, HttpServletRequest request) throws ActException {
        try {
            List<AuditResultVO> voList = BeanTransform.copyProperties(dispatchCarInfoAPI.findAuditResult(id), AuditResultVO.class, request);
            return ActResult.initialize(voList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 预计付款
     *
     * @param id 出车记录id
     * @param budgetPayDate 预计付款日期
     * @param payPlan       付款计划
     * @version v1
     */
    @PostMapping("v1/predict")
    public Result predict(@RequestParam String id, @RequestParam String budgetPayDate, @RequestParam String payPlan) throws ActException {
        try {
            DispatchCarInfoTO to = new DispatchCarInfoTO();
            to.setId(id);
            to.setBudgetPayDate(budgetPayDate);
            to.setPayPlan(payPlan);
            dispatchCarInfoAPI.editModel(to);
            return new ActResult("审核成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 付款
     *
     * @param id 出车记录id
     * @version v1
     */
    @GetMapping("v1/pay/{id}")
    public Result pay(@PathVariable String id) throws ActException {
        try {
            dispatchCarInfoAPI.pay(id);
            return new ActResult("付款成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}
