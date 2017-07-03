package com.bjike.goddess.receivable.action.receivable;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.receivable.api.ContractorAPI;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.to.ContractorTO;
import com.bjike.goddess.receivable.vo.ContractorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 承包商列表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("contractor")
public class ContractorAction {
    @Autowired
    private ContractorAPI contractorAPI;
    /**
     * 承包商列表总条数
     *
     * @param contractorDTO 承包商列表dto
     * @des 获取所有承包商列表总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(ContractorDTO contractorDTO) throws ActException {
        try {
            Long count = contractorAPI.countContractor(contractorDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个承包商列表
     *
     * @param id
     * @return class ContractorVO
     * @des 获取一个承包商列表
     * @version v1
     */
    @GetMapping("v1/contractor/{id}")
    public Result contractor(@PathVariable String id) throws ActException {
        try {
            ContractorBO contractorBO = contractorAPI.getOne(id);
            return ActResult.initialize(BeanTransform.copyProperties(contractorBO, ContractorVO.class));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 承包商列表
     *
     * @param contractorDTO 承包商列表dto
     * @return class ContractorVO
     * @des 获取所有承包商列表
     * @version v1
     */
    @GetMapping("v1/list")
    public Result list(ContractorDTO contractorDTO, HttpServletRequest request) throws ActException {
        try {
            List<ContractorVO> contractorVOS = BeanTransform.copyProperties
                    (contractorAPI.findListContractor(contractorDTO), ContractorVO.class,request);
            return ActResult.initialize(contractorVOS);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加承包商列表
     *
     * @param contractorTO 承包商列表数据to
     * @return class ContractorVO
     * @des 添加承包商列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result add(@Validated ContractorTO contractorTO, BindingResult bindingResult) throws ActException {
        try {
            ContractorBO contractorBO = contractorAPI.insertContractor(contractorTO);
            return ActResult.initialize(contractorBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 编辑承包商列表
     *
     * @param contractorTO 承包商列表数据to
     * @return class ContractorVO
     * @des 编辑承包商列表
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/edit")
    public Result edit(@Validated(EDIT.class) ContractorTO contractorTO, BindingResult bindingResult) throws ActException {
        try {
            ContractorBO contractorBO = contractorAPI.editContractor(contractorTO);
            return ActResult.initialize(contractorBO);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除承包商列表
     *
     * @param id 用户id
     * @des 根据用户id删除承包商列表记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result delete(@PathVariable String id) throws ActException {
        try {
            contractorAPI.removeContractor(id);
            return new ActResult("delete success");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

}