package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.CashFlowProjectBO;
import com.bjike.goddess.reportmanagement.bo.CashFormulaBO;
import com.bjike.goddess.reportmanagement.bo.CashRateBO;
import com.bjike.goddess.reportmanagement.bo.ReturnCashBO;
import com.bjike.goddess.reportmanagement.dto.CashFlowProjectDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlowProject;
import com.bjike.goddess.reportmanagement.to.CashRateListTO;

import java.util.List;

/**
 * 现金流量项目表业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:06 ]
 * @Description: [ 现金流量项目表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CashFlowProjectSer extends Ser<CashFlowProject, CashFlowProjectDTO> {

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CashFlowProjectBO> list(CashFlowProjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 查看项目对应的公式
     *
     * @param id
     * @return
     */
    default CashFormulaBO findFormula(String id) throws SerException {
        return null;
    }

    /**
     * 查询列表总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long findTotal(CashFlowProjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据项目id查询金额
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default ReturnCashBO findMoney(CashFlowProjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 修改金额
     *
     * @param dto
     * @throws SerException
     */
    default void editMoney(CashFlowProjectDTO dto) throws SerException {
        return;
    }

    /**
     * 根据项目id查询比率
     *
     * @param id
     */
    default List<CashRateBO> findRate(String id) throws SerException {
        return null;
    }

    /**
     * 修改比率
     *
     * @param to
     * @throws SerException
     */
    default void editRate(CashRateListTO to) throws SerException {
        return;
    }

    /**
     * 根据科目查询金额
     *
     * @param projectName
     * @param dto
     * @return
     * @throws SerException
     */
    default Double findCashByProject(String projectName, CashFlowProjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出
     *
     * @param dto
     * @throws SerException
     */
    default byte[] export(CashFlowProjectDTO dto) throws SerException {
        return null;
    }

}