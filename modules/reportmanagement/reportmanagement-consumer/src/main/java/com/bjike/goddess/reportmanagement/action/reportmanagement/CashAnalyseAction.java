package com.bjike.goddess.reportmanagement.action.reportmanagement;

import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.reportmanagement.api.CashAnalyseAPI;
import com.bjike.goddess.reportmanagement.bo.CashAnalyse1BO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseCashBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseManaBO;
import com.bjike.goddess.reportmanagement.dto.CashAnalyseDTO;
import com.bjike.goddess.reportmanagement.to.CashAnalyse1TO;
import com.bjike.goddess.reportmanagement.vo.CashAnalyse1VO;
import com.bjike.goddess.reportmanagement.vo.CashAnalyseCashVO;
import com.bjike.goddess.reportmanagement.vo.CashAnalyseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 现金流量分析
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("cashanalyse")
public class CashAnalyseAction {
    @Autowired
    private CashAnalyseAPI cashAnalyseAPI;

    /**
     * 经营活动产生的现金流量分析1
     *
     * @param dto 查询条件
     * @return class CashAnalyseVO
     * @version v1
     */
    @GetMapping("v1/analyse/manager1")
    public Result managerAnalyse1(CashAnalyseDTO dto) throws ActException {
        try {
            List<CashAnalyseBO> bos = cashAnalyseAPI.managerAnalyse1(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 经营活动产生的现金流量分析2
     *
     * @param dto 查询条件
     * @return class CashAnalyseVO
     * @version v1
     */
    @GetMapping("v1/analyse/manager2")
    public Result managerAnalyse2(CashAnalyseDTO dto) throws ActException {
        try {
            List<CashAnalyseBO> bos = cashAnalyseAPI.managerAnalyse2(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 经营活动产生的现金流量分析3
     *
     * @param dto 查询条件
     * @return class CashAnalyseVO
     * @version v1
     */
    @GetMapping("v1/analyse/manager3")
    public Result managerAnalyse3(CashAnalyseDTO dto) throws ActException {
        try {
            List<CashAnalyseManaBO> bos = cashAnalyseAPI.managerAnalyse3(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投资活动产生的现金流量分析
     *
     * @param dto 查询条件
     * @return class CashAnalyseVO
     * @version v1
     */
    @GetMapping("v1/analyse/investment")
    public Result investmentAnalyse1(CashAnalyseDTO dto) throws ActException {
        try {
            List<CashAnalyseBO> bos = cashAnalyseAPI.investmentAnalyse1(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 筹资活动产生的现金流量分析
     *
     * @param dto 查询条件
     * @return class CashAnalyseVO
     * @version v1
     */
    @GetMapping("v1/analyse/financing")
    public Result financingAnalyse(CashAnalyseDTO dto) throws ActException {
        try {
            List<CashAnalyseBO> bos = cashAnalyseAPI.financingAnalyse(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashAnalyseVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * （四）现金流量构成分析
     *
     * @param dto 查询条件
     * @return class CashAnalyseCashVO
     * @version v1
     */
    @GetMapping("v1/analyse/cash")
    public Result cashAnalyse(CashAnalyseDTO dto) throws ActException {
        try {
            List<CashAnalyseCashBO> bos = cashAnalyseAPI.cashAnalyse(dto);
            return ActResult.initialize(BeanTransform.copyProperties(bos, CashAnalyseCashVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 根据id获取对象
     *
     * @return class CashAnalyse1VO
     * @version v1
     */
    @GetMapping("v1/findById/{id}")
    public Result findById(@PathVariable String id) throws ActException {
        try {
            CashAnalyse1BO bo = cashAnalyseAPI.findById(id);
            return ActResult.initialize(BeanTransform.copyProperties(bo, CashAnalyse1VO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑
     *
     * @version v1
     */
    @PutMapping("v1/edit/{id}")
    public Result edit(@Validated(EDIT.class) CashAnalyse1TO to, BindingResult bindingResult) throws ActException {
        try {
            cashAnalyseAPI.edit(to);
            return ActResult.initialize("edit success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}