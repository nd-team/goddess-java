package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.CashFlowDataBO;
import com.bjike.goddess.reportmanagement.bo.CashFormulaDataBO;
import com.bjike.goddess.reportmanagement.bo.ReturnCashDataBO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDataDTO;

import java.util.List;

/**
 * 现金流量资料表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:02 ]
 * @Description: [ 现金流量资料表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CashFlowDataAPI {

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CashFlowDataBO> list(CashFlowDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据项目id查看项目对应的公式
     *
     * @param dataId
     * @throws SerException
     */
    default CashFormulaDataBO findFormula(String dataId) throws SerException {
        return null;
    }



    /**
     * 查询列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long findTotal(CashFlowDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据项目id查询金额
     *
     * @param dto
     * @throws SerException
     */
    default ReturnCashDataBO findMoney(CashFlowDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 修改金额
     *
     * @param dto
     * @throws SerException
     */
    default void editMoney(CashFlowDataDTO dto) throws SerException {
        return;
    }
}