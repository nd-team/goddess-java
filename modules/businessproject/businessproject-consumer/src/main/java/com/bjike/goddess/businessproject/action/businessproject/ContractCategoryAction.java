package com.bjike.goddess.businessproject.action.businessproject;

import com.bjike.goddess.businessproject.api.ContractCategoryAPI;
import com.bjike.goddess.businessproject.bo.ContractCategoryBO;
import com.bjike.goddess.businessproject.dto.ContractCategoryDTO;
import com.bjike.goddess.businessproject.to.ContractCategoryTO;
import com.bjike.goddess.businessproject.vo.ContractCategoryVO;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商务项目合同类型
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 04:24 ]
 * @Description: [ 商务项目合同类型 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("businessproject/contractcategory")
public class ContractCategoryAction {

    @Autowired
    private ContractCategoryAPI contractCategoryAPI;

    /**
     * 项目合同类型列表
     *
     * @param contractCategoryDTO 项目合同类型信息dto
     * @return class ContractCategoryVO
     * @des 获取所有项目合同类型信息
     * @version v1
     */
    @GetMapping("v1/listContractCategory")
    public Result findListContractCategory(ContractCategoryDTO contractCategoryDTO) throws ActException {
        try {
            List<ContractCategoryVO> contractCategoryVOList = BeanTransform.copyProperties(
                    contractCategoryAPI.listContractCategory(contractCategoryDTO), ContractCategoryVO.class, true);
            return ActResult.initialize(contractCategoryVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加项目合同类型
     *
     * @param contractCategoryTO 项目合同类型基本信息数据to
     * @return class ContractCategoryVO
     * @des 添加项目合同类型
     * @version v1
     */
    @PostMapping("v1/add")
    public Result addContractCategory(@Validated ContractCategoryTO contractCategoryTO) throws ActException {
        try {
            ContractCategoryBO contractCategoryBO1 = contractCategoryAPI.addContractCategory(contractCategoryTO);
            return ActResult.initialize(BeanTransform.copyProperties(contractCategoryBO1, ContractCategoryVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑项目合同类型
     *
     * @param contractCategoryTO 项目合同类型基本信息数据bo
     * @return class ContractCategoryVO
     * @des 添加项目合同类型
     * @version v1
     */
    @PostMapping("v1/edit")
    public Result editContractCategory(@Validated ContractCategoryTO contractCategoryTO) throws ActException {
        try {
            ContractCategoryBO contractCategoryBO1 = contractCategoryAPI.editContractCategory(contractCategoryTO);
            return ActResult.initialize(BeanTransform.copyProperties(contractCategoryBO1, ContractCategoryVO.class, true));
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除项目合同类型信息记录
     * @version v1
     */
    @DeleteMapping("v1/delete/{id}")
    public Result deleteContractCategory(@PathVariable String id) throws ActException {
        try {
            contractCategoryAPI.deleteContractCategory(id);
            return new ActResult("delete success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}