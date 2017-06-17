package com.bjike.goddess.moneyside.action.moneyside;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.api.InvestTransferAPI;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.InvestFormDTO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.to.InvestFormTO;
import com.bjike.goddess.moneyside.to.InvestTransferTO;
import com.bjike.goddess.moneyside.vo.InvestFormVO;
import com.bjike.goddess.moneyside.vo.InvestTransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 投资转让
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("investtransfer")
public class InvestTransferAction {
    @Autowired
    private InvestTransferAPI investTransferAPI;
    /**
     * 投资转让列表总条数
     *
     * @param investTransferDTO 投资转让dto
     * @des 获取所有投资转让总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(InvestTransferDTO investTransferDTO) throws ActException {
        try {
            Long count = investTransferAPI.countInvestTransfer(investTransferDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个投资转让
     *
     * @param id
     * @return class InvestTransferVO
     * @des 获取一个投资转让
     * @version v1
     */
    @GetMapping("v1/form/{id}")
    public Result form(@PathVariable String id) throws ActException {
        try {
            InvestTransferBO investTransferBO = investTransferAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(investTransferBO, InvestTransferVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 投资转让列表
     *
     * @param investTransferDTO 投资转让dto
     * @return class InvestTransferVO
     * @des 获取所有投资转让
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(InvestTransferDTO investTransferDTO, HttpServletRequest request) throws ActException {
        try {
            List<InvestTransferVO> investTransferVOS = BeanTransform.copyProperties
                    (investTransferAPI.findListInvestTransfer(investTransferDTO), InvestTransferVO.class, request);
            return ActResult.initialize(investTransferVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferVO
     * @des 添加投资转让
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated(ADD.class) InvestTransferTO investTransferTO, BindingResult bindingResult) throws ActException {
        try {
            InvestTransferBO investTransferBO = investTransferAPI.insertInvestTransfer(investTransferTO);
            return ActResult.initialize(investTransferBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferVO
     * @des 编辑投资转让
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) InvestTransferTO investTransferTO, BindingResult bindingResult) throws ActException {
        try {
            InvestTransferBO investTransferBO = investTransferAPI.editInvestTransfer(investTransferTO);
            return ActResult.initialize(investTransferBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除投资转让
     *
     * @param id 用户id
     * @des 根据用户id删除投资转让记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            investTransferAPI.removeInvestTransfer(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}