package com.bjike.goddess.eggert.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.eggert.bo.AnalysisBO;
import com.bjike.goddess.eggert.entity.Analysis;
import com.bjike.goddess.eggert.dto.AnalysisDTO;
import com.bjike.goddess.eggert.to.AnalysisTO;

import java.util.List;

/**
 * 分析记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:23 ]
 * @Description: [ 分析记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AnalysisSer extends Ser<Analysis, AnalysisDTO> {
    /**
     * 制作评估结果
     *
     * @param analysisTO 制作评估结果数据to
     * @return class analysisBO
     * @throws SerException
     */
    default AnalysisBO makeAnalysis(AnalysisTO analysisTO) throws SerException {
        return null;
    }
    /**
     * 查看评估结果
     *
     * @param analysisDTO 查看评估结果数据dto
     * @return class analysisBO
     * @throws SerException
     */
    default List<AnalysisBO> getAnalysis(AnalysisDTO analysisDTO) throws SerException {
        return null;
    }

}