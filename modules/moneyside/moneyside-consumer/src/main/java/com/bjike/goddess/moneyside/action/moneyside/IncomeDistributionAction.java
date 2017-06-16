package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.IncomeDistributionAPI;
import com.bjike.goddess.moneyside.bo.IncomeDistributionBO;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.IncomeDistributionDTO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.to.IncomeDistributionTO;
import com.bjike.goddess.moneyside.to.InvestTransferTO;
import com.bjike.goddess.moneyside.vo.IncomeDistributionVO;
import com.bjike.goddess.moneyside.vo.InvestTransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收益比例分配
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:18 ]
 * @Description: [ 收益比例分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("incomedistribution")
public class IncomeDistributionAction {
    @Autowired
    private IncomeDistributionAPI incomeDistributionAPI;

    /**
     * 收益比例分配列表总条数
     *
     * @param incomeDistributionDTO 收益比例分配dto
     * @des 获取所有收益比例分配总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(IncomeDistributionDTO incomeDistributionDTO) throws ActException {
        try {
            Long count = incomeDistributionAPI.countIncomeDistribution(incomeDistributionDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个收益比例分配
     *
     * @param id
     * @return class IncomeDistributionVO
     * @des 获取一个收益比例分配
     * @version v1
     */
    @GetMapping("v1/form/{id}")
    public Result form(@PathVariable String id) throws ActException {
        try {
            IncomeDistributionBO incomeDistributionBO = incomeDistributionAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(incomeDistributionBO, IncomeDistributionVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 收益比例分配列表
     *
     * @param incomeDistributionDTO 收益比例分配dto
     * @return class InvestTransferVO
     * @des 获取所有收益比例分配
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(IncomeDistributionDTO incomeDistributionDTO, HttpServletRequest request) throws ActException {
        try {
            List<IncomeDistributionVO> incomeDistributionVOS = BeanTransform.copyProperties
                    (incomeDistributionAPI.findListIncomeDistribution(incomeDistributionDTO), IncomeDistributionVO.class, request);
            return ActResult.initialize(incomeDistributionVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加收益比例分配
     *
     * @param incomeDistributionTO 收益比例分配数据to
     * @return class IncomeDistributionVO
     * @des 添加收益比例分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) IncomeDistributionTO incomeDistributionTO, BindingResult bindingResult) throws ActException {
        try {
            IncomeDistributionBO incomeDistributionBO = incomeDistributionAPI.insertIncomeDistribution(incomeDistributionTO);
            return ActResult.initialize(incomeDistributionBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑收益比例分配
     *
     * @param incomeDistributionTO 收益比例分配数据to
     * @return class IncomeDistributionVO
     * @des 编辑收益比例分配
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) IncomeDistributionTO incomeDistributionTO, BindingResult bindingResult) throws ActException {
        try {
            IncomeDistributionBO incomeDistributionBO = incomeDistributionAPI.editIncomeDistribution(incomeDistributionTO);
            return ActResult.initialize(incomeDistributionBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除收益比例分配
     *
     * @param id 用户id
     * @des 根据用户id删除收益比例分配记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            incomeDistributionAPI.removeIncomeDistribution(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}