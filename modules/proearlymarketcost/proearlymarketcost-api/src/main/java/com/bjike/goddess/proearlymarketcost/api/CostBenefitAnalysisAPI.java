package com.bjike.goddess.proearlymarketcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.proearlymarketcost.bo.CostBenefitAnalysisBO;
import com.bjike.goddess.proearlymarketcost.bo.SalesCostInfoBO;
import com.bjike.goddess.proearlymarketcost.dto.CostBenefitAnalysisDTO;
import com.bjike.goddess.proearlymarketcost.dto.SalesCostInfoDTO;
import com.bjike.goddess.proearlymarketcost.entity.SalesCostInfo;
import com.bjike.goddess.proearlymarketcost.to.CostBenefitAnalysisTO;
import com.bjike.goddess.proearlymarketcost.to.SalesCostInfoTO;

import java.util.List;

/**
 * 费用效益分析业务接口
 *
 * @Author: [ yewenbo ]
 * @Date: [ 2017-03-23 03:58 ]
 * @Description: [ 费用效益分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostBenefitAnalysisAPI {
    default CostBenefitAnalysisBO save(CostBenefitAnalysisTO costBenefitAnalysisTO) throws SerException {
        return null;
    }

    default List<CostBenefitAnalysisBO> list(CostBenefitAnalysisDTO costBenefitAnalysisDTO) throws SerException {
        return null;
    }

    default void update(CostBenefitAnalysisTO costBenefitAnalysisTO) throws SerException {

    }

    default void remove(String id) throws SerException {

    }

    default List<CostBenefitAnalysisBO> collectArea(CostBenefitAnalysisDTO dto)throws SerException{
        return null;
    }

}