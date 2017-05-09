package com.bjike.goddess.businessproject.api;

import com.bjike.goddess.businessproject.bo.ContractCategoryBO;
import com.bjike.goddess.businessproject.dto.ContractCategoryDTO;
import com.bjike.goddess.businessproject.to.ContractCategoryTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 商务项目合同类型业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-21 04:24 ]
 * @Description: [ 商务项目合同类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractCategoryAPI {

    /**
     * 项目合同类型列表总条数
     *
     */
    default Long countContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取项目合同类型列表
     * @return class ContractCategoryBO
     */
    default ContractCategoryBO getOneById(String id) throws SerException {return null;}


    /**
     * 合同类型列表
     * @return class ContractCategoryBO
     */
    default List<ContractCategoryBO> listContractCategory(ContractCategoryDTO contractCategoryDTO) throws SerException {return null;}
    /**
     *  添加
     * @param contractCategoryTO 合同类型
     * @return class ContractCategoryBO
     */
    default ContractCategoryBO addContractCategory(ContractCategoryTO contractCategoryTO) throws SerException { return null;}

    /**
     *  编辑
     * @param contractCategoryTO 合同类型
     * @return class ContractCategoryBO
     */
    default ContractCategoryBO editContractCategory(ContractCategoryTO contractCategoryTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteContractCategory(String id ) throws SerException {return;};

}