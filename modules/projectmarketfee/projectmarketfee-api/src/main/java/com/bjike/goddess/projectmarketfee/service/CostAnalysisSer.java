package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysis;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisTO;

import java.util.List;

/**
 * 费用效益分析业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostAnalysisSer extends Ser<CostAnalysis, CostAnalysisDTO> {
    /**
     * 添加
     *
     * @param to 费用效益分析信息
     * @return class CostAnalysisBO
     * @throws SerException
     */
    default CostAnalysisBO save(CostAnalysisTO to) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param to 费用效益分析信息
     * @throws SerException
     */
    default void edit(CostAnalysisTO to) throws SerException {
    }

    /**
     * 分页查找
     *
     * @param dto 费用效益分页信息
     * @return class CostAnalysisBO
     * @throws SerException
     */
    default List<CostAnalysisBO> list(CostAnalysisDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 费用效益分析id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 通过id查找
     *
     * @param id 费用效益分析id
     * @return class CostAnalysisBO
     * @throws SerException
     */
    default CostAnalysisBO findByID(String id) throws SerException {
        return null;
    }
}