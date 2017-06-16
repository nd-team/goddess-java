package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CapitalInvestBO;
import com.bjike.goddess.moneyside.dto.CapitalInvestDTO;
import com.bjike.goddess.moneyside.to.CapitalInvestTO;

import java.util.List;

/**
 * 资金投资业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:00 ]
 * @Description: [ 资金投资业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CapitalInvestAPI {
    /**
     * 资金投资列表总条数
     */
    default Long countCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金投资
     *
     * @return class CapitalInvestBO
     */
    default CapitalInvestBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金投资
     *
     * @param capitalInvestDTO 资金投资dto
     * @return class CapitalInvestBO
     * @throws SerException
     */
    default List<CapitalInvestBO> findListCapitalInvest(CapitalInvestDTO capitalInvestDTO) throws SerException {
        return null;
    }

    /**
     * 添加资金投资
     *
     * @param capitalInvestTO 资金投资数据to
     * @return class CapitalInvestBO
     * @throws SerException
     */
    default CapitalInvestBO insertCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        return null;
    }

    /**
     * 编辑资金投资
     *
     * @param capitalInvestTO 资金投资数据to
     * @return class CapitalInvestBO
     * @throws SerException
     */
    default CapitalInvestBO editCapitalInvest(CapitalInvestTO capitalInvestTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除资金投资
     *
     * @param id
     * @throws SerException
     */
    default void removeCapitalInvest(String id) throws SerException {

    }
}