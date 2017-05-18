package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisCountDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysisCount;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisCountTO;

/**
 * 费用效益分析业务汇总业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 03:13 ]
 * @Description: [ 费用效益分析业务汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostAnalysisCountSer extends Ser<CostAnalysisCount, CostAnalysisCountDTO> {
    /**
     * 添加
     *
     * @param to to
     * @return class CostAnalysisCountBO
     * @throws SerException
     */
    default CostAnalysisCountBO save(CostAnalysisCountTO to) throws SerException {
        return null;
    }

    /**
     * 通过id查找
     *
     * @param id id
     * @return class CostAnalysisCountBO
     * @throws SerException
     */
    default CostAnalysisCountBO findByID(String id) throws SerException {
        return null;
    }
}