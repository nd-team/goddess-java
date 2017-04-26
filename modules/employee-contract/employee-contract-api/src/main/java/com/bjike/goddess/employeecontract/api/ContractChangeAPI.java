package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.ContractChangeBO;
import com.bjike.goddess.employeecontract.dto.ContractChangeDTO;
import com.bjike.goddess.employeecontract.entity.ContractChange;
import com.bjike.goddess.employeecontract.to.ContractChangeTO;

import java.util.List;

/**
 * 合同变更详细业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-21 04:23 ]
 * @Description: [ 合同变更详细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ContractChangeAPI {

    /**
     * 修改
     *
     * @param to 合同变更详细传输对象
     * @return
     * @throws SerException
     */
    default ContractChangeBO update(ContractChangeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 合同变更详细数据id
     * @return
     * @throws SerException
     */
    default ContractChangeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 合同变更详细数据传输对象
     * @return
     * @throws SerException
     */
    default List<ContractChangeBO> maps(ContractChangeDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id获取合同变更详细数据
     *
     * @param id 合同变更详细数据id
     * @return
     * @throws SerException
     */
    default ContractChangeBO getById(String id) throws SerException {
        return null;
    }

}