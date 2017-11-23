package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.CashFlowDataAPI;
import com.bjike.goddess.reportmanagement.bo.CashFlowDataBO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDataDTO;
import com.bjike.goddess.reportmanagement.vo.CashFlowDataVO;
import com.bjike.goddess.reportmanagement.vo.CashFormulaDataVO;
import com.bjike.goddess.reportmanagement.vo.ReturnCashDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 现金流量资料表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:02 ]
 * @Description: [ 现金流量资料表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("cashflowdata")
public class CashFlowDataAction {

    @Autowired
    private CashFlowDataAPI cashFlowDataAPI;

    /**
     * 列表
     *
     * @param dto 查询条件
     * @return class CashFlowDataVO
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(CashFlowDataDTO dto) throws ActException {
        try {
            List<CashFlowDataBO> bos = cashFlowDataAPI.list(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashFlowDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目id查看项目对应的公式
     *
     * @return class CashFormulaDataVO
     * @version v1
     */
    @GetMapping("v1/findFormula/{dataId}")
    public Result findFormula(@PathVariable String dataId) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cashFlowDataAPI.findFormula(dataId), CashFormulaDataVO.class));
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
    public Result findTotal(CashFlowDataDTO dto) throws ActException {
        try {
            return ActResult.initialize(cashFlowDataAPI.findTotal(dto));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据项目id查询金额
     *
     * @param dto 查询条件
     * @return class ReturnCashDataVO
     * @version v1
     */
    @GetMapping("v1/findMoney/{dataId}")
    public Result findMoney(CashFlowDataDTO dto) throws ActException {
        try {
            return ActResult.initialize(BeanTransform.copyProperties(cashFlowDataAPI.findMoney(dto), ReturnCashDataVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 修改金额
     *
     * @version v1
     */
    @PutMapping("v1/editMoney/{dataId}")
    public Result editMoney(@Validated(EDIT.class) CashFlowDataDTO dto) throws ActException {
        try {
            cashFlowDataAPI.editMoney(dto);
            return ActResult.initialize("修改成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

//    /**
//     * 根据项目id查询比率
//     *
//     * @param dataId 查询条件
//     * @return class CashRateVO
//     * @version v1
//     */
//    @GetMapping("v1/findRate/{dataId}")
//    public Result findRate(@PathVariable String dataId) throws ActException {
//        try {
//            return ActResult.initialize(BeanTransform.copyProperties(cashFlowDataAPI.findRate(dataId), CashRateVO.class));
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }
//
//    /**
//     * 修改比率
//     *
//     * @version v1
//     */
//    @PutMapping("v1/editRate/{dataId}")
//    public Result editRate(@Validated(EDIT.class) CashRateTO to) throws ActException {
//        try {
//            cashFlowDataAPI.editRate(to);
//            return ActResult.initialize("修改成功");
//        } catch (SerException e) {
//            throw new ActException(e.getMessage());
//        }
//    }

}