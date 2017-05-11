package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.ContractCategoryBO;
import com.bjike.goddess.businessproject.dto.ContractCategoryDTO;
import com.bjike.goddess.businessproject.service.ContractCategorySer;
import com.bjike.goddess.businessproject.to.ContractCategoryTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商务项目合同类型业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 04:24 ]
 * @Description: [ 商务项目合同类型业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("contractCategoryApiImpl")
public class ContractCategoryApiImpl implements ContractCategoryAPI {

    @Autowired
    private ContractCategorySer contractCategorySer;

    @Override
    public Long countContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {
        return contractCategorySer.countContractCategory( contractCategoryDTO);
    }

    @Override
    public ContractCategoryBO getOneById(String id) throws SerException {
        return contractCategorySer.getOneById(id);
    }

    @Override
    public List<ContractCategoryBO> listContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {
        return contractCategorySer.listContractCategory( contractCategoryDTO);
    }

    @Override
    public ContractCategoryBO addContractCategory(ContractCategoryTO contractCategoryTO) throws SerException {
        return contractCategorySer.addContractCategory( contractCategoryTO);
    }

    @Override
    public ContractCategoryBO editContractCategory(ContractCategoryTO contractCategoryTO) throws SerException {
        return contractCategorySer.editContractCategory(contractCategoryTO);
    }

    @Override
    public void deleteContractCategory(String id) throws SerException {
        contractCategorySer.deleteContractCategory(id);
    }
}