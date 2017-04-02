package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.receivable.bo.ContractorBO;
import com.bjike.goddess.receivable.dto.ContractorDTO;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.to.ContractorTO;

import java.util.List;

/**
 * 承包商列表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 03:14 ]
 * @Description: [ 承包商列表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractorAPI {
    /**
     * 获取承包商列表
     *
     * @param contractorDTO 承包商列表dto
     * @return class contractorBO
     * @throws SerException
     */
    default List<ContractorBO> findListContractor(ContractorDTO contractorDTO) throws SerException {
        return null;
    }

    /**
     * 添加承包商列表
     *
     * @param contractorTO 承包商列表数据to
     * @throws SerException
     */
    default ContractorBO insertContractor(ContractorTO contractorTO) throws SerException {
        return null;
    }

    /**
     * 编辑承包商列表
     *
     * @param contractorTO 承包商列表数据to
     * @return class contractorBO
     * @throws SerException
     */
    default ContractorBO editContractor(ContractorTO contractorTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除承包商列表
     *
     * @param id
     * @throws SerException
     */
    default void removeContractor(String id) throws SerException {

    }
    /**
     * 根据名字查询
     *
     * @param name
     * @throws SerException
     */
    default Contractor findByName(String name) throws SerException{
        return null;
    }

}