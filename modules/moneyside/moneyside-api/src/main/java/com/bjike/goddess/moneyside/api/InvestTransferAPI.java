package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.to.InvestTransferTO;

import java.util.List;

/**
 * 投资转让业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InvestTransferAPI {
    /**
     * 投资转让列表总条数
     */
    default Long countInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        return null;
    }

    /**
     * 一个投资转让
     *
     * @return class InvestTransferBO
     */
    default InvestTransferBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 投资转让
     *
     * @param investTransferDTO 投资转让dto
     * @return class InvestTransferBO
     * @throws SerException
     */
    default List<InvestTransferBO> findListInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        return null;
    }

    /**
     * 添加投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferBO
     * @throws SerException
     */
    default InvestTransferBO insertInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        return null;
    }

    /**
     * 编辑投资转让
     *
     * @param investTransferTO 投资转让数据to
     * @return class InvestTransferBO
     * @throws SerException
     */
    default InvestTransferBO editInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除投资转让
     *
     * @param id
     * @throws SerException
     */
    default void removeInvestTransfer(String id) throws SerException {

    }
}