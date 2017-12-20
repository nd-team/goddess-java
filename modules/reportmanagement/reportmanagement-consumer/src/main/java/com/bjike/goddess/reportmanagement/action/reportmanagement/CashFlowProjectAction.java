package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.CashFlowProjectAPI;
import com.bjike.goddess.reportmanagement.bo.CashFlowProjectBO;
import com.bjike.goddess.reportmanagement.dto.CashFlowProjectDTO;
import com.bjike.goddess.reportmanagement.to.CashRateListTO;
import com.bjike.goddess.reportmanagement.vo.CashFlowProjectVO;
import com.bjike.goddess.reportmanagement.vo.CashFormulaVO;
import com.bjike.goddess.reportmanagement.vo.CashRateVO;
import com.bjike.goddess.reportmanagement.vo.ReturnCashVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 现金流量项目表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:06 ]
 * @Description: [ 现金流量项目表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("cashflowproject")
public class CashFlowProjectAction {
    @Autowired
    private CashFlowProjectAPI cashFlowProjectAPI;

    /**
     * 列表
     *
     * @param dto 查询条件
     * @return class CashFlowProjectVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CashFlowProjectDTO dto) throws ActException {
        try {
            List<CashFlowProjectBO> bos = cashFlowProjectAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashFlowProjectVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目id查看项目对应的公式
     *
     * @return class CashFormulaVO
     * @version v1
     */
    @GetMapping("v1/findFormula/{projectId}")
    public Result findFormula(@PathVariable String projectId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cashFlowProjectAPI.findFormula(projectId), CashFormulaVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查询列表总条数
     *
     * @version v1
     */
    @GetMapping("v1/findTotal")
    public Result findTotal(CashFlowProjectDTO dto) throws ActException {
        try {
            return ActResult.initialize(cashFlowProjectAPI.findTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目id查询金额
     *
     * @param dto 查询条件
     * @return class ReturnCashVO
     * @version v1
     */
    @GetMapping("v1/findMoney/{projectId}")
    public Result findMoney(CashFlowProjectDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cashFlowProjectAPI.findMoney(dto), ReturnCashVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改金额
     *
     * @version v1
     */
    @PutMapping("v1/editMoney/{projectId}")
    public Result editMoney(@Validated(EDIT.class) CashFlowProjectDTO dto) throws ActException {
        try {
            cashFlowProjectAPI.editMoney(dto);
            return ActResult.initialize("修改成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目id查询比率
     *
     * @param projectId 查询条件
     * @return class CashRateVO
     * @version v1
     */
    @GetMapping("v1/findRate/{projectId}")
    public Result findRate(@PathVariable String projectId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cashFlowProjectAPI.findRate(projectId), CashRateVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改比率
     *
     * @version v1
     */
    @PostMapping("v1/editRate/{projectId}")
    public Result editRate( CashRateListTO to) throws ActException {
        try {
            cashFlowProjectAPI.editRate(to);
            return ActResult.initialize("修改成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}