package com.bjike.goddess.contractquotemanager.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.contractquotemanager.bo.ContractNodeStandardBO;
import com.bjike.goddess.contractquotemanager.entity.ContractNodeStandard;
import com.bjike.goddess.contractquotemanager.dto.ContractNodeStandardDTO;
import com.bjike.goddess.contractquotemanager.to.ContractNodeStandardTO;

import java.util.List;

/**
 * 合同节点标准信息业务接口
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-20T17:03:20.720 ]
 * @Description: [ 合同节点标准信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractNodeStandardSer extends Ser<ContractNodeStandard, ContractNodeStandardDTO> {


    default ContractNodeStandardBO save(ContractNodeStandardTO contractQuoteDataTO) throws SerException {
        return null;
    }

    default List<ContractNodeStandardBO> list(ContractNodeStandardDTO dto) throws SerException {
        return null;
    }

    default void update(ContractNodeStandardTO contractNodeStandardTO) throws SerException {
    }

    default void remove(String id) throws SerException {

    }

    default List<ContractNodeStandardBO> collect(ContractNodeStandardBO bo) throws SerException {
        return null;
    }

    default List<ContractNodeStandardBO> searchContractNodeStandard(ContractNodeStandardBO bo) throws SerException {
        return null;
    }
}