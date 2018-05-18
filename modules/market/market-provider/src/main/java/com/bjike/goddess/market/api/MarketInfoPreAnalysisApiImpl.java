package com.bjike.goddess.market.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.market.bo.MarketInfoPreAnalysisBO;
import com.bjike.goddess.market.dto.MarketInfoPreAnalysisDTO;
import com.bjike.goddess.market.service.MarketInfoPreAnalysisSer;
import com.bjike.goddess.market.to.GuidePermissionTO;
import com.bjike.goddess.market.to.MarketInfoPreAnalysisTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 市场信息记录业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-10-20 01:36 ]
 * @Description: [ 市场信息记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("marketInfoPreAnalysisApiImpl")
public class MarketInfoPreAnalysisApiImpl implements MarketInfoPreAnalysisAPI {
    @Autowired
    private MarketInfoPreAnalysisSer marketInfoPreAnalysisSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return marketInfoPreAnalysisSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return marketInfoPreAnalysisSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countAnalysis(MarketInfoPreAnalysisDTO marketInfoPreAnalysisDTO) throws SerException {
        return marketInfoPreAnalysisSer.countAnalysis(marketInfoPreAnalysisDTO);
    }

    @Override
    public MarketInfoPreAnalysisBO getOne(String id) throws SerException {
        return marketInfoPreAnalysisSer.getOne(id);
    }

    @Override
    public List<MarketInfoPreAnalysisBO> findListAnalysis(MarketInfoPreAnalysisDTO marketInfoRecordDTO) throws SerException {
        return marketInfoPreAnalysisSer.findListAnalysis(marketInfoRecordDTO);
    }

    @Override
    public void analysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        marketInfoPreAnalysisSer.analysis(marketInfoPreAnalysisTO);
    }

    @Override
    public void budgetAnalysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        marketInfoPreAnalysisSer.budgetAnalysis(marketInfoPreAnalysisTO);
    }

    @Override
    public void planAnalysis(MarketInfoPreAnalysisTO marketInfoPreAnalysisTO) throws SerException {
        marketInfoPreAnalysisSer.planAnalysis(marketInfoPreAnalysisTO);
    }

    @Override
    public void removeAnalysis(String id) throws SerException {
        marketInfoPreAnalysisSer.removeAnalysis(id);
    }

    @Override
    public List<MarketInfoPreAnalysisBO> getconversionBuissOpp() throws SerException {
        return marketInfoPreAnalysisSer.getconversionBuissOpp();
    }
}