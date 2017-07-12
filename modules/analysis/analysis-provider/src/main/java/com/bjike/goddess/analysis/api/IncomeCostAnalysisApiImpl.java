package com.bjike.goddess.analysis.api;

import com.bjike.goddess.analysis.bo.CollectBO;
import com.bjike.goddess.analysis.bo.IncomeCostAnalysisBO;
import com.bjike.goddess.analysis.dto.IncomeCostAnalysisDTO;
import com.bjike.goddess.analysis.excel.SonPermissionObject;
import com.bjike.goddess.analysis.service.IncomeCostAnalysisSer;
import com.bjike.goddess.analysis.to.CollectTO;
import com.bjike.goddess.analysis.to.GuidePermissionTO;
import com.bjike.goddess.analysis.to.IncomeCostAnalysisTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收入成本分析业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-31 03:37 ]
 * @Description: [ 收入成本分析业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("incomeCostAnalysisApiImpl")
public class IncomeCostAnalysisApiImpl implements IncomeCostAnalysisAPI {
    @Autowired
    private IncomeCostAnalysisSer incomeCostAnalysisSer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return incomeCostAnalysisSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return incomeCostAnalysisSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countIncomeCostAnalysis(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws SerException {
        return incomeCostAnalysisSer.countIncomeCostAnalysis(incomeCostAnalysisDTO);
    }

    @Override
    public IncomeCostAnalysisBO getOne(String id) throws SerException {
        return incomeCostAnalysisSer.getOne(id);
    }

    @Override
    public List<IncomeCostAnalysisBO> findListIncomeCostAnalysis(IncomeCostAnalysisDTO incomeCostAnalysisDTO) throws SerException {
        return incomeCostAnalysisSer.findListIncomeCostAnalysis(incomeCostAnalysisDTO);
    }

    @Override
    public IncomeCostAnalysisBO insertIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        return incomeCostAnalysisSer.insertIncomeCostAnalysis(incomeCostAnalysisTO);
    }

    @Override
    public IncomeCostAnalysisBO editIncomeCostAnalysis(IncomeCostAnalysisTO incomeCostAnalysisTO) throws SerException {
        return incomeCostAnalysisSer.editIncomeCostAnalysis(incomeCostAnalysisTO);
    }

    @Override
    public void removeIncomeCostAnalysis(String id) throws SerException {
        incomeCostAnalysisSer.removeIncomeCostAnalysis(id);
    }

    @Override
    public List<CollectBO> collect(CollectTO to) throws SerException {
        return incomeCostAnalysisSer.collect(to);
    }

    @Override
    public List<String> getArea() throws SerException {
        return incomeCostAnalysisSer.getArea();
    }

    @Override
    public List<String> getDepartment() throws SerException {
        return incomeCostAnalysisSer.getDepartment();
    }

}