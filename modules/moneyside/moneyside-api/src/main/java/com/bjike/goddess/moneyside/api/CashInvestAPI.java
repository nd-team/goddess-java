package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CashInvestBO;
import com.bjike.goddess.moneyside.dto.CashInvestDTO;
import com.bjike.goddess.moneyside.to.CashInvestTO;

import java.util.List;

/**
 * 投资条件-现金投资业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:40 ]
 * @Description: [ 投资条件-现金投资业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CashInvestAPI {

    /**
     * 现金投资列表总条数
     */
    default Long countCashInvest(CashInvestDTO cashInvestDTO) throws SerException {
        return null;
    }

    /**
     * 一个现金投资
     *
     * @return class CashInvestBO
     */
    default CashInvestBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 现金投资
     *
     * @param cashInvestDTO 现金投资dto
     * @return class CashInvestBO
     * @throws SerException
     */
    default List<CashInvestBO> findListCashInvest(CashInvestDTO cashInvestDTO) throws SerException {
        return null;
    }

    /**
     * 添加现金投资
     *
     * @param cashInvestTO 现金投资数据to
     * @return class CashInvestBO
     * @throws SerException
     */
    default CashInvestBO insertCashInvest(CashInvestTO cashInvestTO) throws SerException {
        return null;
    }

    /**
     * 编辑现金投资
     *
     * @param cashInvestTO 现金投资数据to
     * @return class CashInvestBO
     * @throws SerException
     */
    default CashInvestBO editCashInvest(CashInvestTO cashInvestTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除现金投资
     *
     * @param id
     * @throws SerException
     */
    default void removeCashInvest(String id) throws SerException {

    }
}