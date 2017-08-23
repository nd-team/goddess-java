package com.bjike.goddess.proearlymarketcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.proearlymarketcost.bo.CostBenefitAnalysisBO;
import com.bjike.goddess.proearlymarketcost.dto.CostBenefitAnalysisDTO;
import com.bjike.goddess.proearlymarketcost.service.CostBenefitAnalysisSer;
import com.bjike.goddess.proearlymarketcost.to.CostBenefitAnalysisTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 费用效益分析业务接口实现
* @Author:			[ yewenbo ]
* @Date:			[  2017-03-23 03:58 ]
* @Description:	[ 费用效益分析业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("costBenefitAnalysisApiImpl")
public class CostBenefitAnalysisApiImpl implements CostBenefitAnalysisAPI  { 
    @Autowired
    private CostBenefitAnalysisSer costBenefitAnalysisSer;
 @Override
 public CostBenefitAnalysisBO save(CostBenefitAnalysisTO costBenefitAnalysisTO)throws SerException{
   return costBenefitAnalysisSer.save(costBenefitAnalysisTO);
 }

 @Override
 public List<CostBenefitAnalysisBO> list(CostBenefitAnalysisDTO costBenefitAnalysisDTO)throws SerException{
  return costBenefitAnalysisSer.list(costBenefitAnalysisDTO);
 }

 @Override
 public void update(CostBenefitAnalysisTO costBenefitAnalysisTO)throws SerException{
     costBenefitAnalysisSer.update(costBenefitAnalysisTO);
 }

 @Override
 public void remove(String id)throws SerException{
  costBenefitAnalysisSer.remove(id);
 }

    @Override
    public List<CostBenefitAnalysisBO> collectArea(CostBenefitAnalysisDTO dto)throws SerException{
        return costBenefitAnalysisSer.collectArea(dto);
 }
 }