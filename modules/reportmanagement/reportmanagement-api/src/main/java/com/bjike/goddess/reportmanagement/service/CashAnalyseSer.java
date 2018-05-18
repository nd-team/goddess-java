package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.reportmanagement.bo.CashAnalyse1BO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseCashBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseManaBO;
import com.bjike.goddess.reportmanagement.dto.CashAnalyseDTO;
import com.bjike.goddess.reportmanagement.entity.CashAnalyse;
import com.bjike.goddess.reportmanagement.to.CashAnalyse1TO;

import java.util.List;

/**
 * 现金流量分析业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CashAnalyseSer extends Ser<CashAnalyse, CashAnalyseDTO> {

    /**
     * 经营活动产生的现金流量分析1
     *
     * @return
     * @throws SerException
     */
    default List<CashAnalyseBO> managerAnalyse1(CashAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * 经营活动产生的现金流量分析2
     *
     * @return
     * @throws SerException
     */
    default List<CashAnalyseBO> managerAnalyse2(CashAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * 投资活动产生的现金流量分析1
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CashAnalyseBO> investmentAnalyse1(CashAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * 筹资活动产生的现金流量分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CashAnalyseBO> financingAnalyse(CashAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * （四）现金流量构成分析
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CashAnalyseCashBO> cashAnalyse(CashAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * 经营活动产生的现金流量分析3
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<CashAnalyseManaBO> managerAnalyse3(CashAnalyseDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     * @throws SerException
     */
    default CashAnalyse1BO findByid(String id) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    default void edit(CashAnalyse1TO to) throws SerException {
        return;
    }
}