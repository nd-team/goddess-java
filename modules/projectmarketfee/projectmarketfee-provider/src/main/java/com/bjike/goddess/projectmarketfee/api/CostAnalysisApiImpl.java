package com.bjike.goddess.projectmarketfee.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisBO;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.service.CostAnalysisSer;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisTO;
import com.bjike.goddess.projectmarketfee.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 费用效益分析业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("costAnalysisApiImpl")
public class CostAnalysisApiImpl implements CostAnalysisAPI {
    @Autowired
    private CostAnalysisSer costAnalysisSer;

    @Override
    public CostAnalysisBO save(CostAnalysisTO to) throws SerException {
        return costAnalysisSer.save(to);
    }

    @Override
    public void edit(CostAnalysisTO to) throws SerException {
        costAnalysisSer.edit(to);
    }

    @Override
    public List<CostAnalysisBO> list(CostAnalysisDTO dto) throws SerException {
        return costAnalysisSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        costAnalysisSer.delete(id);
    }

    @Override
    public CostAnalysisBO findByID(String id) throws SerException {
        return costAnalysisSer.findByID(id);
    }

    @Override
    public List<CostAnalysisCountBO> arrivalCount(Integer year, Integer month) throws SerException {
        return costAnalysisSer.arrivalCount(year, month);
    }

    @Override
    public List<CostAnalysisCountBO> projectGroupCount(Integer year, Integer month) throws SerException {
        return costAnalysisSer.projectGroupCount(year, month);
    }

    @Override
    public List<CostAnalysisCountBO> projectNameCount(Integer year, Integer month) throws SerException {
        return costAnalysisSer.projectNameCount(year, month);
    }

    @Override
    public List<CostAnalysisBO> findDetail(String id) throws SerException {
        return costAnalysisSer.findDetail(id);
    }

    @Override
    public CostAnalysisBO countNum(CostAnalysisDTO dto) throws SerException {
        return costAnalysisSer.countNum(dto);
    }

    @Override
    public Set<Integer> allYears() throws SerException {
        return costAnalysisSer.allYears();
    }

    @Override
    public Set<Integer> allMonths() throws SerException {
        return costAnalysisSer.allMonths();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return costAnalysisSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return costAnalysisSer.guidePermission(guidePermissionTO);
    }
}